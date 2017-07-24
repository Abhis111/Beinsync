package com.binaryic.beinsync.models;

/**
 * Created by Binary_Apple on 7/20/17.
 */

public class DrawerModel {

    private String id;
    private String title;
    private boolean isOpen = false;
    private boolean isHeader = false;
    private boolean isChild = false;

    public DrawerModel(String id, String title, boolean isOpen, boolean isHeader, boolean isChild) {
        this.id = id;
        this.title = title;
        this.isOpen = isOpen;
        this.isHeader = isHeader;
        this.isChild = isChild;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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
}
