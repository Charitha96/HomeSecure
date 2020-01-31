package com.example.myapplication.Notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.EditUser.EditUserPage;
import com.example.myapplication.HomePage;
import com.example.myapplication.R;


public class PushNotification extends AppCompatActivity {

    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        goHome = (Button)findViewById(R.id.goBackBttn);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PushNotification.this, HomePage.class);
                startActivity(intent);
            }
        });


    }
}
