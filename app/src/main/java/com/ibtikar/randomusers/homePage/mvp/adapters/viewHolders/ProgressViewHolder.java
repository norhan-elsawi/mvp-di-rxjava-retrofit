package com.ibtikar.randomusers.homePage.mvp.adapters.viewHolders;

import android.view.View;
import android.widget.ProgressBar;

import com.ibtikar.randomusers.R;

public class ProgressViewHolder extends RecyclerViewHolders {
    public ProgressBar progressBar;

    public ProgressViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progressBar);
    }
}
