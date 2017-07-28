package com.binaryic.beinsync.models;

/**
 * Created by Binary_Apple on 7/27/17.
 */

public class TagModel {
    private String tag;
    private String title;
    private String id;
    String story_id;

    public String getStory_id() {
        return story_id;
    }

    public void setStory_id(String story_id) {
        this.story_id = story_id;
    }

    boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public TagModel(String tag, String title, String id,String story_id) {
        this.tag = tag;
        this.title = title;
        this.story_id=story_id;
        this.id = id;
    }

    public TagModel() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
