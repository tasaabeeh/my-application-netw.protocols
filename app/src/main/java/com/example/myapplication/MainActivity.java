package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.DataOutputStream;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.DataInputStream;

import java.io.IOException;

public class MainActivity<s> extends AppCompatActivity {

    Socket client;
    String ip,msg;
    Button SendButton;
    TextView textField;
    TextInputEditText input;
    Button ConnectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input= (TextInputEditText) findViewById(R.id.input);// from the xml hanekteb fel hetta deeh
        textField = (TextView) findViewById(R.id.textView); //finds it from the xml
        ConnectButton=(Button) findViewById(R.id.button2);
        SendButton=(Button) findViewById(R.id.button); //finds it from the xml
        ConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    ip="192.168.1.15";
                    client= new Socket(ip, 6780); // connect to server
                }
                catch (IOException e) {
                }
            }
        });
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg = textField.getText().toString(); //get the message on the text field
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    DataOutputStream d= new DataOutputStream(client.getOutputStream()); //object benesta3melo 3ashan nehot feeh el output
                    d.writeUTF(input.getText().toString());// dah elly beneb3ato
                    DataInputStream inputStream=new DataInputStream(client.getInputStream()); //to send on
                    String s=inputStream.readUTF();
                    textField.setText(s);
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    }




