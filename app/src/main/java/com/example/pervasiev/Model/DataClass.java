package com.example.pervasiev.Model;

public class DataClass {
    String description, imageUrl, title, url,key;

    DataClass(){}
    public DataClass(String description, String imageUrl, String title, String url) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.title = title;
        this.url = url;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}