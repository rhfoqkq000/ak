package com.donga.examples.boomin.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.activity.AttendActivity;

import java.util.ArrayList;

public class AttendAdapter extends RecyclerView.Adapter<AttendAdapter.ViewHolder> {

    private ArrayList<com.donga.examples.boomin.adapter.MyData> mDataset;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView dateText, titleText, none_pcnotis_id;
        public CardView cardview;

        public ViewHolder(View view) {
            super(view);
            dateText = view.findViewById(R.id.manage_attend_date);
            titleText = view.findViewById(R.id.manage_attend_title);
            none_pcnotis_id = view.findViewById(R.id.manage_attend_none_pcnotis_id);
            cardview = view.findViewById(R.id.attend_cardview);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AttendAdapter(ArrayList<com.donga.examples.boomin.adapter.MyData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_attend, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.dateText.setText(mDataset.get(position).date);
        holder.titleText.setText(mDataset.get(position).title);
        holder.none_pcnotis_id.setText(mDataset.get(position).pcnotis_id);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AttendActivity.class);
                intent.putExtra("pnotis_id", holder.none_pcnotis_id.getText().toString());
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