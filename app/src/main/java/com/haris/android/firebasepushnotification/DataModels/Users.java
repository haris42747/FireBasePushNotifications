package com.haris.android.firebasepushnotification.DataModels;

public class Users extends UserId{

    private String name, image;

    public Users() {
    }

    public Users(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
