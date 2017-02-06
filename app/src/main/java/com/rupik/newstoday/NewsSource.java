package com.rupik.newstoday;

import java.io.Serializable;

/**
 * Created by macmin5 on 06/02/17.
 */

public class NewsSource implements Serializable {
    String id;
    String name;
    String description;
    String url;
    String category;
    String language;
    String country;
    String logoUrl;
    String []sortBysAvailable;

    public String getCategory() {
        return category;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String[] getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSortBysAvailable(String[] sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
