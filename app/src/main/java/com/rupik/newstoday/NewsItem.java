package com.rupik.newstoday;

/**
 * Created by macmin5 on 06/02/17.
 */

public class NewsItem {
    String title;
    String description;
    String author;
    String url;
    String imageUrl;
    String publishedTime;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
