package com.example.campgroundsAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.campgroundsAndroid.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<Campground> campgroundsList;
    ListData listData;

    public static MainActivity getInstance() {
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        instance = this;

        int[] imageList = {R.drawable.camp1, R.drawable.camp2, R.drawable.camp3, R.drawable.camp4, R.drawable.pizza, R.drawable.burger, R.drawable.fries};

        loadData();
//        Campground campground1 = new Campground(
//                getString(R.string.campground1_title),
//                getString(R.string.campground1_location),
//                getString(R.string.campground1_description),
//                Integer.parseInt(getString(R.string.campground1_price)),
//                R.drawable.camp1
//        );
//
//        Campground campground2 = new Campground(
//                getString(R.string.campground2_title),
//                getString(R.string.campground2_location),
//                getString(R.string.campground2_description),
//                Integer.parseInt(getString(R.string.campground2_price)),
//                R.drawable.camp2
//        );
//
//        campgroundsList.add(campground1);
//        campgroundsList.add(campground2);

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

        Button buttonCreateCamp = (Button)findViewById(R.id.buttonCreateCamp);

        buttonCreateCamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, CreateCamp.class));

                }
            });
    }



    public void insertItem(String title, String location, String description, int price, String image) {
        campgroundsList.add(new Campground(title, location, description, price, image));
    }
    public void saveData(){
        SharedPreferences sharedPreferences=getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson= new Gson();
        String json= gson.toJson(campgroundsList);
        editor.putString("task list2", json);
        editor.apply();
        Log.d("myTag", "saving data: "+campgroundsList.toString());
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list2", null);
        Type type = new TypeToken<ArrayList<Campground>>() {}.getType();
        campgroundsList = gson.fromJson(json, type);

        if (campgroundsList == null) {
            campgroundsList = new ArrayList<>();
            Log.d("myTag", "initializing data: "+campgroundsList.toString());
        }
        Log.d("myTag", "loading data: "+campgroundsList.toString());
    }
}


