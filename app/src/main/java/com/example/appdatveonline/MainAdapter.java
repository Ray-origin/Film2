package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.OnClickListener;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {
    List<Movies> movies;
    private Context context;
    private ItemClickListener itemClickListener;

    public MainAdapter(List<Movies> movies, Context context, ItemClickListener itemClickListener) {
        this.movies = movies;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.movie_list,parent,false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        holder.tv_title.setText(movies.get(position).getTitle());
        holder.tv_price.setText(movies.get(position).getPrice());
        holder.tv_description.setText(movies.get(position).getDescription());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_baseline_movie_filter_24);
        requestOptions.error(R.drawable.ic_baseline_movie_filter_24);

        Glide.with(context)
                .load(movies.get(position).getImage())
                .apply(requestOptions)
                .into(holder.iv_list_image);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder implements OnClickListener {
         TextView tv_title,tv_price,tv_description;
         ImageView iv_list_image;
         RelativeLayout item_layout;
         ItemClickListener itemClickListener;
        RecyclerViewAdapter(View itemView,ItemClickListener itemClickListener){
            super(itemView);

            tv_title=(TextView) itemView.findViewById(R.id.title);
            tv_price=(TextView) itemView.findViewById(R.id.price);
            tv_description=(TextView) itemView.findViewById(R.id.description);
            iv_list_image=(ImageView) itemView.findViewById(R.id.list_image);

            item_layout= (RelativeLayout) itemView.findViewById(R.id.main_relative_layout);

            this.itemClickListener=itemClickListener;
            item_layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    public interface  ItemClickListener{
        void onItemClick(View view, int position);
    }
}
