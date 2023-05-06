package com.zaidmansuri.mahendidesign;

public class model {
    String image,title;

    public model(String image,String title) {
        this.image = image;
        this.title=title;

    }

    public model() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
