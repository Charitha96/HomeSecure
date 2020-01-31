package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SIgnInPage extends AppCompatActivity {

    //declare variables
    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private EditText RetypePassword;
    private Button SignUP;
    private Button gotoLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        //setting up
        FirstName = (EditText)findViewById(R.id.firstName);
        LastName = (EditText)findViewById(R.id.lastName);
        Email = (EditText)findViewById(R.id.emailText);
        Password = (EditText)findViewById(R.id.passwordText);
        RetypePassword = (EditText)findViewById(R.id.retypePasswordText);

        SignUP = (Button)findViewById(R.id.signUpBtn);
        gotoLogin = (Button)findViewById(R.id.goLoginBtn);


        //login up redirect
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SIgnInPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Sign up redirect
        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }

    //Sign up validation
    private void validate(){

        //validate email
        String email = Email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String pass = Password.getText().toString();

        if(TextUtils.isEmpty(FirstName.getText().toString())) {
            FirstName.setError("First Name can't be left Blank");
            return;
        }else if (TextUtils.isEmpty(LastName.getText().toString())){
            LastName.setError("Last Name can't be left Blank");
            return;
        }else if (TextUtils.isEmpty(Email.getText().toString())) {
            Email.setError("Email can't be left Blank");
            return;
        }else if (!(email.matches(emailPattern)))
        {
            Email.setError("Invalid email address");
            return;

        }else if (TextUtils.isEmpty(Password.getText().toString())){
            Password.setError("Password can't be left Blank");
            return;
        }else if (TextUtils.isEmpty(RetypePassword.getText().toString())){
            RetypePassword.setError("Retype password can't be left Blank");
            return;

            //not working matching passwords
        }else if (RetypePassword.getText().equals(pass)){
            RetypePassword.setError("Passwords doesn't match");
            return;
        } else{
            //redirect to login page
            Intent intent = new Intent(SIgnInPage.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
