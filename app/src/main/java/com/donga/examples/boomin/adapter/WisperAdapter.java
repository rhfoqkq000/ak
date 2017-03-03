package com.donga.examples.boomin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.activity.Wisper_NoticeDialogActivity;
import com.donga.examples.boomin.retrofit.retrofitNormalRead.Interface_normalRead;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WisperAdapter extends RecyclerView.Adapter<WisperAdapter.ViewHolder> {
    Context context;
    AppendLog log = new AppendLog();

    private ArrayList<MyData_Wisper> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateText, nameText, titleText, contentText, read_check;
        public RelativeLayout rLayout;
        public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            dateText = (TextView)view.findViewById(R.id.wisper_date);
            nameText = (TextView)view.findViewById(R.id.wisper_name);
            titleText = (TextView)view.findViewById(R.id.wisper_title);
            contentText = (TextView)view.findViewById(R.id.wisper_content);
            read_check = (TextView)view.findViewById(R.id.wisper_read_check);
            cardview = (CardView)view.findViewById(R.id.wisper_cardview);
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
        holder.dateText.setText(mDataset.get(position).date);
        holder.nameText.setText(mDataset.get(position).name);
        holder.titleText.setText(mDataset.get(position).title);
        holder.contentText.setText(mDataset.get(position).content);
        holder.read_check.setText(mDataset.get(position).read_check);

        if(mDataset.get(position).read_check.equals("0")){
            holder.rLayout.setBackground(context.getResources().getDrawable(R.drawable.left_line2));
        }

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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
                            view.getContext().startActivity(intent);
                        }else{
                            log.appendLog("inWisperAdapter code not matched");
                        }
                    }

                    @Override
                    public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitNormalRead.Master> call, Throwable t) {
                        t.printStackTrace();
                        log.appendLog("inWisperAdapter failure");
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
}