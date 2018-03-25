package com.example.luisfernandorodrigues.roompock.Ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luisfernandorodrigues.roompock.Dao.Entity.News;
import com.example.luisfernandorodrigues.roompock.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by luis on 20/10/16.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListViewHolder>  {
    protected static final String TAG = "livroandroid";
    private final List<News> newsList;
    private final Context context;
    private NewsAdapter infoAdapter = this;





    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getItemCount() {
        return newsList != null ? newsList.size() : 0;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_list_news, viewGroup, false);

        // Cria o ViewHolder
        ListViewHolder holder = new ListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, final int position) {

        News news = newsList.get(position);
        if(!TextUtils.isEmpty(news.getImage())){
            Picasso.get().load(news.getImage()).into(holder.image);
        }
        holder.title.setText(news.getTitle());
        holder.desciption.setText(news.getDescription());
    }


    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desciption;
        public ImageView image;

        public ListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.tv_title);
            desciption = view.findViewById(R.id.tv_short_description);
            image = view.findViewById(R.id.iv_image);

        }
    }


}