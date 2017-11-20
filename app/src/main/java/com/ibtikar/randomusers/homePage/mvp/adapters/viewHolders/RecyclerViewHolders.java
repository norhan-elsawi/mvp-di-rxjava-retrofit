package com.ibtikar.randomusers.homePage.mvp.adapters.viewHolders;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibtikar.randomusers.R;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView displayedImage;
    public TextView textTitle;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        textTitle =  itemView.findViewById(R.id.title_header);
        displayedImage = itemView.findViewById(R.id.icon_image);
    }

    @Override
    public void onClick(View view) {
    }
}