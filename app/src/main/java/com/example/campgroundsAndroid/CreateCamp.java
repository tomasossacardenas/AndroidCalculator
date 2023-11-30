package com.example.campgroundsAndroid;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class CreateCamp extends AppCompatActivity {
    ImageView imageView;
    ActivityResultLauncher<PickVisualMediaRequest> launcher=registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri o) {
            if (o==null){
                //display default image
            }else{
                Glide.with(getApplicationContext()).load(o).into(imageView);
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_camp);

        imageView=findViewById(R.id.imageView);
        MaterialButton pickImage=findViewById(R.id.pickImage);
        Button createCamp=findViewById(R.id.createCamp);
        pickImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launcher.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        //this can be modified is the action to save the campground
        createCamp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launcher.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });
    }
}