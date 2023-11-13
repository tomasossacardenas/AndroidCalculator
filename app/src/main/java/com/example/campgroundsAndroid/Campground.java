package com.example.campgroundsAndroid;

import android.widget.ImageView;

public class Campground {
    String title, location, description;
    int price;
    int image;
    public Campground(String title, String location, String description, int price, int image) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
