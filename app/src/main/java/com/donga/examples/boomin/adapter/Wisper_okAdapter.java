package com.donga.examples.boomin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.activity.Wisper_OkDialogActivity;
import com.donga.examples.boomin.retrofit.retrofitCircleRead.Interface_circleRead;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Wisper_okAdapter extends RecyclerView.Adapter<Wisper_okAdapter.ViewHolder> {
    Context context;
    AppendLog log = new AppendLog();

    private ArrayList<MyData_Wisper> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateText, nameText, titleText, contentText, none_id;
        public RelativeLayout rLayout;
        public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            dateText = (TextView)view.findViewById(R.id.wisper_date);
            nameText = (TextView)view.findViewById(R.id.wisper_name);
            titleText = (TextView)view.findViewById(R.id.wisper_title);
            contentText = (TextView)view.findViewById(R.id.wisper_content);
            none_id = (TextView)view.findViewById(R.id.wisper_none_id);
            cardview = (CardView)view.findViewById(R.id.wisper_cardview);
            rLayout = (RelativeLayout)view.findViewById(R.id.wisper_layout_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public Wisper_okAdapter(ArrayList<MyData_Wisper> myDataset, Context current) {
        mDataset = myDataset;
        this.context = current;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Wisper_okAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        holder.none_id.setText(mDataset.get(position).none_id);

        if(mDataset.get(position).read_check.equals("0")){
            holder.rLayout.setBackground(context.getResources().getDrawable(R.drawable.left_line2));
        }

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //retrofit 통신
                Retrofit client = new Retrofit.Builder().baseUrl(context.getString(R.string.retrofit_url))
                        .addConverterFactory(GsonConverterFactory.create()).build();
                final Interface_circleRead read = client.create(Interface_circleRead.class);
                Call<com.donga.examples.boomin.retrofit.retrofitCircleRead.Master> call = read.readFcm(mDataset.get(position).none_id);
                Log.i("CALLLL", mDataset.get(position).none_id);
                call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitCircleRead.Master>() {
                    @Override
                    public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitCircleRead.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitCircleRead.Master> response) {
                        if(response.body().getResult_code() == 1){
                            holder.rLayout.setBackground(context.getResources().getDrawable(R.drawable.left_line));

                            Intent intent = new Intent(view.getContext(), Wisper_OkDialogActivity.class);
                            Log.i("dddddddddd", holder.contentText.getText().toString());
                            intent.putExtra("content", holder.contentText.getText().toString());
                            intent.putExtra("circle_notis_id", holder.none_id.getText().toString());
                            view.getContext().startActivity(intent);
                        }else{
                            log.appendLog("inWisper_okAdapter code not matched");
                        }
                    }

                    @Override
                    public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitCircleRead.Master> call, Throwable t) {
                        t.printStackTrace();
                        log.appendLog("inWisper_okAdapter failure");
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