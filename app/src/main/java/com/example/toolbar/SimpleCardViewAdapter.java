package com.example.toolbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class SimpleCardViewAdapter extends RecyclerView.Adapter<SimpleCardViewAdapter.ViewHolder> {

    private List<CardViewData> mDataset;

    public SimpleCardViewAdapter(List<CardViewData> dataset) {
        mDataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final CardViewData cardViewData = mDataset.get(i);

        viewHolder.mTitle.setText(cardViewData.getTitle());
        viewHolder.mDescription.setText(cardViewData.getDescription());
        viewHolder.mImage.setImageResource(cardViewData.getImage());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Title: " + cardViewData.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDescription;
        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.row_title);
            mDescription = (TextView) itemView.findViewById(R.id.row_description);
            mImage = (ImageView) itemView.findViewById(R.id.row_image);
        }
    }
}
