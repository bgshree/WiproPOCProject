package com.bhagyashree.wipropocproject.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bhagyashree.wipropocproject.R;
import com.bhagyashree.wipropocproject.model.DetailModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.ViewHolder> {
    private final Context context;
    private List<DetailModel> detailModelList;

    public DetailListAdapter(Context context) {
        this.context = context;
    }

    public void setRowList(List<DetailModel> list) {
        detailModelList = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        DetailModel detailModel = detailModelList.get(position);
        String description = detailModel.getDescription();
        String title = detailModel.getTitle();
        String imageLink = detailModel.getImageHref();

        if (!TextUtils.isEmpty(description)) {
            holder.descriptionTV.setText(description);
        } else {
            holder.descriptionTV.setText(context.getString(R.string.no_description));
        }

        if (!TextUtils.isEmpty(title)) {
            holder.titleTV.setText(title);
        } else {
            holder.titleTV.setText(context.getString(R.string.no_title));
        }


        if (!TextUtils.isEmpty(imageLink)) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.default_thumbnail)
                    .onlyRetrieveFromCache(false)
                    .skipMemoryCache(false)
                    .error(R.drawable.default_thumbnail)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .priority(Priority.HIGH);

            Glide.with(context)
                    .load(imageLink)
                    .apply(options)
                    .into(holder.thumbnailIV);
        }
    }

    @Override
    public int getItemCount() {
        return detailModelList != null ? detailModelList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView titleTV;

        @BindView(R.id.tv_description)
        TextView descriptionTV;

        @BindView(R.id.iv_thumbnail)
        ImageView thumbnailIV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bind(DetailModel model) {

        }
    }
}
