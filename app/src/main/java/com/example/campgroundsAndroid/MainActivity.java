package com.example.campgroundsAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.campgroundsAndroid.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<Campground> campgroundsList = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageList = {R.drawable.camp1, R.drawable.camp2, R.drawable.camp3, R.drawable.camp4, R.drawable.pizza, R.drawable.burger, R.drawable.fries};

        Campground campground1 = new Campground(
                getString(R.string.campground1_title),
                getString(R.string.campground1_location),
                getString(R.string.campground1_description),
                Integer.parseInt(getString(R.string.campground1_price)),
                R.drawable.camp1
        );

        Campground campground2 = new Campground(
                getString(R.string.campground2_title),
                getString(R.string.campground2_location),
                getString(R.string.campground2_description),
                Integer.parseInt(getString(R.string.campground2_price)),
                R.drawable.camp2
        );

        campgroundsList.add(campground1);
        campgroundsList.add(campground2);

        listAdapter = new ListAdapter(MainActivity.this, campgroundsList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Campground selectedCampground = campgroundsList.get(i);
                Log.e("MY-TAG sel", selectedCampground.toString());
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("title", selectedCampground.title);
                intent.putExtra("price", selectedCampground.price);
                intent.putExtra("description", selectedCampground.description);
                intent.putExtra("location", selectedCampground.location);
                intent.putExtra("image", selectedCampground.image);
                startActivity(intent);
            }
        });
    }
}