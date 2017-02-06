package com.rupik.newstoday;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by macmin5 on 06/02/17.
 */

public class NewsCategory implements Serializable {
    String categoryName;
    ArrayList<NewsSource> newsSources;

    public ArrayList<NewsSource> getNewsSources() {
        return newsSources;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setNewsSources(ArrayList<NewsSource> newsSources) {
        this.newsSources = newsSources;
    }
}
