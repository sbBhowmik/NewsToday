package com.rupik.newstoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import se.emilsjolander.flipview.FlipView;
import se.emilsjolander.flipview.OverFlipMode;

/**
 * Created by macmin5 on 03/02/17.
 */

public class SuperAwesomeCardFragment extends Fragment{
    private static final String ARG_POSITION = "position";

    private int position;

    private FlipView mFlipView;
    private FlipAdapter mAdapter;

    static ArrayList<NewsCategory> mNewsCategories;;

    View baseView;

    public static SuperAwesomeCardFragment newInstance(int position, ArrayList<NewsCategory> newsCategory) {
        SuperAwesomeCardFragment f = new SuperAwesomeCardFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        mNewsCategories = newsCategory;

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_item,container,false);
        ViewCompat.setElevation(rootView, 50);
        TextView textView = (TextView) rootView.findViewById(R.id.textView);
        textView.setText("CARD " + position);
//Flip View

        baseView = rootView;



        fetchAllNewsInCategory(position);

        return rootView;
    }

    ArrayList<NewsItem> newsDataSource;

    void fetchAllNewsInCategory(int cPosition)
    {
        NewsCategory newsCategory = mNewsCategories.get(cPosition);
        for(int i=0;i<newsCategory.getNewsSources().size();i++)
        {
            final NewsSource newsSource = newsCategory.getNewsSources().get(i);



            String urlString = "https://newsapi.org/v1/articles?source=" + newsSource.getId() + "&apiKey=b12b2839c5b84643b117acc78af8c8ba";
            StringRequest stringRequest = new StringRequest(urlString, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //parse JSON Here ...
                    DataSourceController dataSourceController = new DataSourceController();
                    ArrayList<NewsItem> newsItems = dataSourceController.parseNewsItem(response);

                    if(newsDataSource==null)
                    {
                        newsDataSource = new ArrayList<>();
                    }
                    newsDataSource.addAll(newsItems);

                    if(newsSource.getCountry().contains("in"))
                    {
                        String urlString = "https://newsapi.org/v1/articles?source=" + newsSource.getId() + "&apiKey=b12b2839c5b84643b117acc78af8c8ba&sortBy=latest";
                        fetchTopNews(urlString);
                    }
                    else {
                        displayNewsItems();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_LONG).show();
//                mProgressWheel.stopSpinning();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }
    }

    void fetchTopNews(String urlString)
    {
        StringRequest stringRequest = new StringRequest(urlString, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //parse JSON Here ...
                DataSourceController dataSourceController = new DataSourceController();
                ArrayList<NewsItem> newsItems = dataSourceController.parseNewsItem(response);

                if(newsDataSource==null)
                {
                    newsDataSource = new ArrayList<>();
                }
                newsDataSource.addAll(newsItems);

                displayNewsItems();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_LONG).show();
//                mProgressWheel.stopSpinning();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    void displayNewsItems()
    {
        mFlipView = (FlipView) baseView.findViewById(R.id.flip_view);
        mAdapter = new FlipAdapter(getActivity(), newsDataSource);
//        mAdapter.setCallback(getActivity());
        mFlipView.setAdapter(mAdapter);
//        mFlipView.setOnFlipListener(this);
        mFlipView.peakNext(true);
        mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
        mFlipView.setEmptyView(baseView.findViewById(R.id.empty_view));
//        mFlipView.setOnOverFlipListener(this);
    }
}
