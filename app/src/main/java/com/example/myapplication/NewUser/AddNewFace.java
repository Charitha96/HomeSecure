package com.example.myapplication.NewUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.HomePage;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;


public class AddNewFace extends AppCompatActivity {

    private Button takeAnotherBtn;
    private Button retakePicBtn;
    private Button doneBtn;
    private ImageView imgView;
    int photoCount = 0;

    private StorageReference mStorageRef;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_face);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();

        imgView = (ImageView)findViewById(R.id.userImg);
        takeAnotherBtn = (Button)findViewById(R.id.takeAnother);
        retakePicBtn = (Button)findViewById(R.id.retakePic);
        doneBtn = (Button)findViewById(R.id.doneBtn);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = (Bitmap) extras.get("image");
            if (image != null) {
                imgView.setImageBitmap(image);
            }
        }

        //button actions
        takeAnotherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoCount++;

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);


//                Uri file = Uri.fromFile(new File("images/logo.jpg"));
//                StorageReference riversRef = mStorageRef.child("images/logo.jpg");
//
//                riversRef.putFile(file)
//                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                // Get a URL to the uploaded content
//                                System.out.println("SUCCESS");
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception exception) {
//                                // Handle unsuccessful uploads
//                                // ...
//                                System.out.println("FAILURE");
//                            }
//                        });

            }
        });

        retakePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoCount--;
//                Intent intent = new Intent(AddNewFace.this, AddNewFace.class);
//                startActivity(intent);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (photoCount>=5){
                    Toast.makeText(getBaseContext(), "Face added Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddNewFace.this, HomePage.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Add at least 5 photos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            System.out.println("SUCCESS");

            Uri file = data.getData();
            StorageReference riversRef = mStorageRef.child("images/logo.jpg");

            riversRef.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            System.out.println("SUCCESS");

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference().child("nimal").child("21");

                            myRef.push();
                            myRef.setValue("logo.jpg");

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            System.out.println("FAILURE");
                        }
                    });

        }
    }
}
