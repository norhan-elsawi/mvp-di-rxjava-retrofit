package com.ibtikar.randomusers.homePage.mvp.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibtikar.randomusers.R;
import com.ibtikar.randomusers.homePage.mvp.adapters.viewHolders.ProgressViewHolder;
import com.ibtikar.randomusers.homePage.mvp.adapters.viewHolders.RecyclerViewHolders;
import com.ibtikar.randomusers.model.pojos.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    protected Context context;
    private List<Result> itemList;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;

    @Inject
    Picasso picasso;

    public RecyclerViewAdapter(Context context, List<Result> itemList, RecyclerView recyclerView) {
        this.itemList = itemList;
        this.context = context;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        loading = true;
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position) != null ? 1 : 0;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder;
        if (viewType == 1) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            viewHolder = new RecyclerViewHolders(layoutView);
        } else {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
            viewHolder = new ProgressViewHolder(layoutView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        } else {
            (holder).textTitle.setText(itemList.get(position).getName().getFirst());
            picasso.with(context).load(itemList.get(position).getPicture().getThumbnail()).into(holder.displayedImage);

        }
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}