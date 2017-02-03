package com.rupik.newstoday;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macmin5 on 03/02/17.
 */

public class FlipAdapter extends BaseAdapter implements View.OnClickListener {

    public interface Callback{
        public void onPageRequested(int page);
    }

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

    public FlipAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        for(int i = 0 ; i<10 ; i++){
            items.add(new Item());
        }
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
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).getId();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        Button firstPage;
        Button lastPage;
        public MyViewHolder(View v) {
            super(v);
            text = (TextView) v.findViewById(R.id.text);
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



        //TODO set a text with the id as well
        holder.text.setText(items.get(i).getId()+":"+i);

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
