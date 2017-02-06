package com.rupik.newstoday;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmin5 on 03/02/17.
 */

public class FlipAdapter extends BaseAdapter implements View.OnClickListener {

    public interface Callback{
        public void onPageRequested(int page);
    }

    ArrayList<NewsItem> newsDataSource;
    Context mContext;

    static class Item {
        static long id = 0;

        long mId;

        public Item() {
            mId = id++;
        }

        long getId(){
            return mId;
        }
    }

    private LayoutInflater inflater;
    private Callback callback;
    private List<Item> items = new ArrayList<Item>();

    public FlipAdapter(Context context,  ArrayList<NewsItem> newsDataSource) {
        inflater = LayoutInflater.from(context);

        mContext = context;
        this.newsDataSource = newsDataSource;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.first_page:
                if(callback != null){
                    callback.onPageRequested(0);
                }
                break;
            case R.id.last_page:
                if(callback != null){
                    callback.onPageRequested(getCount()-1);
                }
                break;
        }
    }

    @Override
    public int getCount() {
        return newsDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        NewsItem newsItem = newsDataSource.get(i);
        return newsItem;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        TextView descTV;
        ImageView newsImageView;
        Button firstPage;
        Button lastPage;
        public MyViewHolder(View v) {
            super(v);
            newsImageView = (ImageView)v.findViewById(R.id.news2ImageView);
            descTV = (TextView)v.findViewById(R.id.news2Desc);
            text = (TextView) v.findViewById(R.id.news2Title);
            firstPage = (Button) v.findViewById(R.id.first_page);
            lastPage = (Button) v.findViewById(R.id.last_page);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

            view = inflater.inflate(R.layout.page, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(view);

            holder.firstPage.setOnClickListener(this);
            holder.lastPage.setOnClickListener(this);


        NewsItem item = newsDataSource.get(i);
        holder.text.setText(item.getTitle());
        holder.descTV.setText(item.getDescription());
        String imageURL = item.getImageUrl();

        Picasso.with(mContext)
                .load(imageURL)
                .into(holder.newsImageView);

        return view;
    }

    public void addItems(int amount) {
        for(int i = 0 ; i<amount ; i++){
            items.add(new Item());
        }
        notifyDataSetChanged();
    }

    public void addItemsBefore(int amount) {
        for(int i = 0 ; i<amount ; i++){
            items.add(0, new Item());
        }
        notifyDataSetChanged();
    }
}
