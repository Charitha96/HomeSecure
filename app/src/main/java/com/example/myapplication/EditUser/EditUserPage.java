package com.example.myapplication.EditUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.HomePage;
import com.example.myapplication.NewUser.AddNewFace;
import com.example.myapplication.R;

public class EditUserPage extends AppCompatActivity {

    private ListView usersList;
    private Button removeUserBtn;
    private Button goHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_page);

        removeUserBtn = (Button)findViewById(R.id.removeUser);
        goHome = (Button)findViewById(R.id.goBack);

        usersList = (ListView) findViewById(R.id.listView);


        //button actions
        removeUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "User Removed Successfully", Toast.LENGTH_SHORT).show();

            }
        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUserPage.this, HomePage.class);
                startActivity(intent);
            }
        });

    }
}
