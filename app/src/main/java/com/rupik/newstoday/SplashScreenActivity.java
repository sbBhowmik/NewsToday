package com.rupik.newstoday;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;


public class SplashScreenActivity extends AppCompatActivity {

    ProgressWheel mProgressWheel;
    static  String JSON_URL = "https://newsapi.org/v1/sources";

    ArrayList<NewsCategory> newsCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mProgressWheel = (ProgressWheel)findViewById(R.id.progress_wheel);
        mProgressWheel.setBarColor(Color.BLUE);
        mProgressWheel.spin();

        fetchNewsSources();
    }

    private void fetchNewsSources(){

        StringRequest stringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //parse JSON Here ...
                DataSourceController dataSourceController = new DataSourceController();
                ArrayList<NewsSource> newsSources = dataSourceController.parseNewsSourcesJSON(response);
                prepareCategories(newsSources);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashScreenActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                mProgressWheel.stopSpinning();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private  void  prepareCategories(ArrayList<NewsSource> newsSources)
    {
        newsCategories = new ArrayList<>();

        for(int i=0;i<newsSources.size(); i++)
        {
            NewsSource newsSource = newsSources.get(i);
            String catString = newsSource.getCategory();
            boolean isCategoryPresent = false;
            for(int j=0;j<newsCategories.size();j++)
            {
                NewsCategory category = newsCategories.get(j);
                if(category.getCategoryName().contains(catString))
                {
                    isCategoryPresent = true;
                    //insert The NewsSource in the arrayList of the NewsCategory obj
                    ArrayList<NewsSource> categoryNewsSources = category.getNewsSources();
                    if(newsSource.getCountry().contains("in"))
                    {
                        categoryNewsSources.add(0,newsSource);
                    }
                    else {
                        categoryNewsSources.add(newsSource);
                    }
                    if(newsSource.getLanguage().contains("en")) {
                        category.setNewsSources(categoryNewsSources);
                    }
                }
            }

            if(!isCategoryPresent)
            {
                //add a new catgory
                NewsCategory category = new NewsCategory();
                category.setCategoryName(newsSource.getCategory());

                ArrayList<NewsSource> newsSourceArrayList = new ArrayList<>();
                newsSourceArrayList.add(newsSource);
                category.setNewsSources(newsSourceArrayList);

                newsCategories.add(category);
            }
        }

        mProgressWheel.stopSpinning();
        mProgressWheel.setVisibility(View.GONE);

        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("newsCategories", newsCategories);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
