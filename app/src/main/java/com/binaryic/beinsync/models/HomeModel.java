package com.binaryic.beinsync.models;

/**
 * Created by Binary_Apple on 7/20/17.
 */

public class HomeModel {

    private String id;
    private String title;
    private String content;
    private String image;
    private String url;
    private String title_Category;


    public String getTitle_Category() {
        return title_Category;
    }

    public void setTitle_Category(String title_Category) {
        this.title_Category = title_Category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
