package com.example.campgroundsAndroid;

import android.net.Uri;
import android.widget.ImageView;

public class Campground {
    String title, location, description;
    int price;
    String image;
    public Campground(String title, String location, String description, int price, String image) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.price = price;
        this.image = image;
    }
}
