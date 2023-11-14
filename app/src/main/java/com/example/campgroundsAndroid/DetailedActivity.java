package com.example.campgroundsAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.campgroundsAndroid.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {

    ActivityDetailedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null){
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String location = intent.getStringExtra("location");
            int price = intent.getIntExtra("price", R.string.campground1_price);
            int image = intent.getIntExtra("image", R.drawable.camp1);

            binding.detailedTitle.setText(title);
            binding.detailedLocation.setText(location);
            binding.detailedPrice.setText(Integer.toString(price));
            binding.detailedDescription.setText(description);
            binding.detailImage.setImageResource(image);
        }
    }
}