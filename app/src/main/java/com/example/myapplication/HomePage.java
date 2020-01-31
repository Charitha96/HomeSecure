package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.NewUser.AddNewUser;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HomePage extends AppCompatActivity {

    //declare variables
    private Button connectorDisconnect;
    private Button addNewUser;
    private Button editRemoveUser;
    private Button watchLive;
    private Button customizeNotifications;
    private Button logout;
    //UI Element
    Button btnUp;
    Button btnDown;
    EditText txtAddress;
    Socket myAppSocket = null;
    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String CMD = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        connectorDisconnect = (Button)findViewById(R.id.connectDisconnectBtn);
        addNewUser = (Button)findViewById(R.id.addUser);
        editRemoveUser = (Button)findViewById(R.id.editRemoveBtn);
        watchLive = (Button)findViewById(R.id.watchLiveBtn);
        customizeNotifications = (Button)findViewById(R.id.notificationBtn);
        logout = (Button)findViewById(R.id.logoutBtn);
        txtAddress = (EditText) findViewById(R.id.ipAddress);

        //button actions
        connectorDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Connected", Toast.LENGTH_SHORT).show();
                getIPandPort();
                CMD = "Connect";
                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                cmd_increase_servo.execute();

//                getIPandPort();
//                CMD = "Down";
//                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
//                cmd_increase_servo.execute();
//                Toast.makeText(getBaseContext(), "Disconnected", Toast.LENGTH_SHORT).show();

            }
        });

        addNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, AddNewUser.class);
                startActivity(intent);
            }
        });

        editRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        watchLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getIPandPort();
                CMD = "Watch";
                Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                cmd_increase_servo.execute();

                Intent intent = new Intent(HomePage.this, WatchLive.class);
                startActivity(intent);
            }
        });
        
        customizeNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(HomePage.this).create();
                alertDialog.setTitle("Logout");
                alertDialog.setMessage("Are you sure you want to logout?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(HomePage.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });

    }

    //get ip and port from textbox
    public void getIPandPort()
    {
        String iPandPort = txtAddress.getText().toString();
        Log.d("MYTEST","IP String: "+ iPandPort);
        String temp[]= iPandPort.split(":");
        wifiModuleIp = temp[0];
        wifiModulePort = Integer.valueOf(temp[1]);
        Log.d("MY TEST","IP:" +wifiModuleIp);
        Log.d("MY TEST","PORT:"+wifiModulePort);
    }
    //connect and send data to rassp through wifi
    public class Socket_AsyncTask extends AsyncTask<Void,Void,Void>
    {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params){
            try{
                InetAddress inetAddress = InetAddress.getByName(HomePage.wifiModuleIp);
                socket = new java.net.Socket(inetAddress,HomePage.wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();
            }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
            return null;
        }
    }
}
