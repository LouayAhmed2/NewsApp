package com.route.newsfri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.route.apis.model.ArticlesItem;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<ArticlesItem> newsList;

    public NewsAdapter(List<ArticlesItem> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_news,parent,false);
        return new ViewHolder(view);
    }

    public void changeData(List<ArticlesItem> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesItem news = newsList.get(position);
        holder.date.setText(news.publishedAt);//format time
        holder.desc.setText(news.description);
        holder.title.setText(news.title);
        Glide.with(holder.itemView)
                .load(news.urlToImage)
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return newsList ==null ? 0 : newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView date;
        protected TextView title;
        protected ImageView image;
        protected TextView desc;

        public ViewHolder(@NonNull View rootView) {
            super(rootView);
            date =  rootView.findViewById(R.id.date);
            title =  rootView.findViewById(R.id.title);
            image =  rootView.findViewById(R.id.image);
            desc =  rootView.findViewById(R.id.desc);
        }

    }
}
