package com.example.myapplication.NewUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.HomePage;
import com.example.myapplication.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewUser extends AppCompatActivity {

    private TextView userName;
    private TextView userAge;
    private Button addFaceBtn;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_PERMISSION_CODE = 1;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        userName = (TextView)findViewById(R.id.newUserName);
        userAge = (TextView)findViewById(R.id.newUserAge);
        addFaceBtn = (Button)findViewById(R.id.addFaceUser);

        //button actions
        addFaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //myRef = database.getReference(mAuth.getCurrentUser().getUid().toString());

                Log.i("TEST", mAuth.getUid().toString());


                try {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference().child(userName.getText().toString()).child(userAge.getText().toString());
                    myRef.setValue("hey");
                } catch (Exception e) {
                    System.out.println(e.getStackTrace());
                }

//                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
//                startActivityForResult(intent, 0);

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

//
//                Intent intent1 = new Intent(AddNewUser.this, AddNewFace.class);
//                startActivity(intent1);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            Intent intent = new Intent(AddNewUser.this, AddNewFace.class);
            intent.putExtra("image", thumbnail);
            startActivity(intent);
        }
    }

}
