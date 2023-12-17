package com.example.campgroundsAndroid;

import static com.example.campgroundsAndroid.MainActivity.getInstance;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class CreateCamp extends AppCompatActivity {
    ImageView imageView;
    private Uri selectedImageId = null;
    ActivityResultLauncher<PickVisualMediaRequest> launcher=registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri o) {
            if (o==null){
                //display default image
            }else{
                Glide.with(getApplicationContext()).load(o).into(imageView);
                selectedImageId = o;
                System.out.println("IMAGEID:"+selectedImageId);
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("IMAGEID:"+selectedImageId);
        setContentView(R.layout.activity_create_camp);

        imageView=findViewById(R.id.campImage);

        MaterialButton pickImage=findViewById(R.id.pickImage);
        pickImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                launcher.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });

        //this can be modified is the action to save the campground
        Button buttonCreateCamp = (Button)findViewById(R.id.createCamp);

        buttonCreateCamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.editTextTitle);
                EditText location = findViewById(R.id.editTextLocation);
                EditText description = findViewById(R.id.editTextDesc);
                EditText price = findViewById(R.id.editTextPrice);
                //ImageView image = findViewById(R.id.campImage);

                //System.out.printf("IMAGEVIEW==="+imageView.getTag().toString());
                ((MainActivity)getInstance()).insertItem(title.getText().toString(),
                        location.getText().toString(),
                        description.getText().toString(),
                        Integer.parseInt(price.getText().toString()),
                        selectedImageId.toString());

                ((MainActivity)getInstance()).saveData();
            }
        });
    }
}