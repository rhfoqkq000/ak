package com.donga.examples.boomin.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.activity.SendDialogActivity;

import java.util.ArrayList;

/**
 * Created by rhfoq on 2017-02-21.
 */
public class SendAdapter extends RecyclerView.Adapter<SendAdapter.ViewHolder> {

    private ArrayList<MyData_Send> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateText, titleText, contentText;
        public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            dateText = (TextView)view.findViewById(R.id.manage_send_date);
            titleText = (TextView)view.findViewById(R.id.manage_send_title);
            contentText = (TextView)view.findViewById(R.id.manage_send_content);
            cardview = (CardView)view.findViewById(R.id.send_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SendAdapter(ArrayList<MyData_Send> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_send, parent, false);
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
        holder.titleText.setText(mDataset.get(position).title);
        holder.contentText.setText(mDataset.get(position).content);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SendDialogActivity.class);
                intent.putExtra("date", holder.dateText.getText().toString());
                intent.putExtra("title", holder.titleText.getText().toString());
                intent.putExtra("content", holder.contentText.getText().toString());
                view.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}