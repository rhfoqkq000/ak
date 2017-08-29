package com.donga.examples.boomin.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ChangeSingleton;
import com.donga.examples.boomin.Singleton.NoticeSingleton;
import com.donga.examples.boomin.activity.Wisper_NoticeDialogActivity;
import com.donga.examples.boomin.retrofit.retrofitNormalRead.Interface_normalRead;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WisperAdapter extends RecyclerView.Adapter<WisperAdapter.ViewHolder> {
    Context context;
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;

    private ArrayList<MyData_Wisper> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateText, nameText, titleText, contentText, read_check, none_id;
        public RelativeLayout rLayout;
        public CheckBox checkBox;
        public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            dateText = (TextView)view.findViewById(R.id.wisper_date);
            nameText = (TextView)view.findViewById(R.id.wisper_name);
            titleText = (TextView)view.findViewById(R.id.wisper_title);
            contentText = (TextView)view.findViewById(R.id.wisper_content);
            read_check = (TextView)view.findViewById(R.id.wisper_read_check);
            none_id = (TextView)view.findViewById(R.id.wisper_none_id);
            cardview = (CardView)view.findViewById(R.id.wisper_cardview);
            checkBox = (CheckBox)view.findViewById(R.id.wisper_ok_checkbox);
            rLayout = (RelativeLayout)view.findViewById(R.id.wisper_layout_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WisperAdapter(ArrayList<MyData_Wisper> myDataset, Context current) {
        mDataset = myDataset;
        this.context = current;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WisperAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_wisper, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        MyData_Wisper myDataWisper = mDataset.get(position);
        holder.dateText.setText(myDataWisper.date);
        holder.nameText.setText(myDataWisper.name);
        holder.titleText.setText(myDataWisper.title);
        holder.contentText.setText(myDataWisper.content);
        holder.read_check.setText(myDataWisper.read_check);
        holder.none_id.setText(myDataWisper.none_id);
        holder.checkBox.setChecked(false);

        final String getReadCheck = mDataset.get(position).read_check;

        if(getReadCheck.equals("0")){
            holder.rLayout.setBackground(context.getResources().getDrawable(R.drawable.left_line2));
        }

        final ArrayList<String> wisperAdapterArray = ChangeSingleton.getInstance().getWisperAdapterArray();
        for(int i = 0; i<wisperAdapterArray.size(); i++){
            if(Integer.parseInt(wisperAdapterArray.get(i))==(position)){
                holder.checkBox.setChecked(true);
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    wisperAdapterArray.add(String.valueOf(position));
                }else{
                    wisperAdapterArray.remove(String.valueOf(position));
                }
                ChangeSingleton.getInstance().setWisperAdapterArray(wisperAdapterArray);
                Log.i("inWisperAdapter", wisperAdapterArray.toString());
            }
        });

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ArrayList<String> noticeIdArray = NoticeSingleton.getInstance().getNoticeIdArray();
                if(!isChecked){
                    noticeIdArray.remove(holder.none_id.getText().toString());
                }else {
                    noticeIdArray.add(holder.none_id.getText().toString());
                }
                NoticeSingleton.getInstance().setNoticeIdArray(noticeIdArray);
            }
        });

        final SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showProgressDialog();
                if(getReadCheck.equals("0")){
                    int pushCount = sharedPreferences.getInt("pushCount", 0);
                    pushCount--;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("pushCount", pushCount);
                    editor.apply();
                    ShortcutBadger.applyCount(context, pushCount);
                }
                Logger.d("체크인지 노말인지 좀 보자~~~~~");
                //retrofit 통신
                Retrofit client = new Retrofit.Builder().baseUrl(context.getString(R.string.retrofit_url))
                        .addConverterFactory(GsonConverterFactory.create()).build();
                final Interface_normalRead read = client.create(Interface_normalRead.class);
                Call<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master> call = read.readFcm(mDataset.get(position).none_id);
                call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master>() {
                    @Override
                    public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master> response) {
                        if(response.body().getResult_code() == 1){
                            holder.rLayout.setBackground(context.getResources().getDrawable(R.drawable.left_line));

                            Intent intent = new Intent(view.getContext(), Wisper_NoticeDialogActivity.class);
                            intent.putExtra("content", holder.contentText.getText().toString());
                            intent.putExtra("title", holder.titleText.getText().toString());
                            intent.putExtra("name", holder.nameText.getText().toString());
                            hideProgressDialog();
                            view.getContext().startActivity(intent);
                        }else{
                            hideProgressDialog();
                            log.appendLog("inWisperAdapter code not matched");
                            Toasty.error(context, "불러오기 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master> call, Throwable t) {
                        t.printStackTrace();
                        log.appendLog("inWisperAdapter failure");
                        hideProgressDialog();
                        Toasty.error(context, "불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(context.getResources().getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
        }
    }
}