package com.project.petmanagement.model;

public class ProfileHorizontalItem {
    private int image;
    private String title;
    private int colorBackgroud;
    private Class<?> cls;
    public ProfileHorizontalItem(int image, String title, int colorBackgroud, Class<?> cls) {
        this.image = image;
        this.title = title;
        this.colorBackgroud = colorBackgroud;
        this.cls = cls;
    }

    public ProfileHorizontalItem() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColorBackgroud() {
        return colorBackgroud;
    }

    public void setColorBackgroud(int colorBackgroud) {
        this.colorBackgroud = colorBackgroud;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
