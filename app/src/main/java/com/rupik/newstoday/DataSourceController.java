package com.rupik.newstoday;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by macmin5 on 06/02/17.
 */

public class DataSourceController {

//    private static DataSourceController dataSourceController = new DataSourceController( );

    Context mContext;

    /* A private Constructor prevents any other
    * class from instantiating.
    */
//    private DataSourceController() { }

    /* Static 'instance' method */
//    public static DataSourceController getInstance(Context context) {
//        dataSourceController.mContext = context;
//        return dataSourceController;
//    }

    ArrayList<NewsSource> parseNewsSourcesJSON(String jsonString)
    {
        ArrayList <NewsSource> newsSources = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.optJSONArray("sources");
            for(int i=0; i<jsonArray.length(); i++)
            {
                JSONObject newsObj = jsonArray.getJSONObject(i);
                NewsSource newsSource = new NewsSource();
                newsSource.setId(newsObj.optString("id"));
                newsSource.setName(newsObj.optString("name"));
                newsSource.setDescription(newsObj.optString("description"));
                newsSource.setUrl(newsObj.optString("url"));
                newsSource.setCategory(newsObj.optString("category"));
                newsSource.setLanguage(newsObj.optString("language"));
                newsSource.setCountry(newsObj.optString("country"));

                JSONObject logoObj = newsObj.getJSONObject("urlsToLogos");
                newsSource.setLogoUrl(logoObj.optString("small"));

                newsSources.add(newsSource);
            }
        }
        catch (JSONException e)
        {

        }

        return  newsSources;
    }

    ArrayList<NewsItem> parseNewsItem(String jsonString)
    {
        ArrayList<NewsItem> newsItems = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.optJSONArray("articles");
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject newsObj = jsonArray.getJSONObject(i);
                NewsItem newsItem = new NewsItem();
                newsItem.setTitle(newsObj.optString("title"));
                newsItem.setDescription(newsObj.optString("description"));
                newsItem.setAuthor(newsObj.optString("author"));
                newsItem.setUrl(newsObj.optString("url"));
                newsItem.setImageUrl(newsObj.optString("urlToImage"));
                newsItem.setPublishedTime(newsObj.optString("publishedAt"));

                newsItems.add(newsItem);
            }
        }
        catch (JSONException e)
        {

        }

        return newsItems;
    }

}
