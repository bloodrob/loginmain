package com.example.akash.loginmain;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class entry extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Button Act1btn = (Button)findViewById(R.id.service);
        Button Act2btn = (Button)findViewById(R.id.client);
        Button Act3btn = (Button)findViewById(R.id.search);


            Act3btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(entry.this, ResultSearch.class);
                    startActivity(intent);
                }
            });

            Act1btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(entry.this, ServiceProvMain.class);
                    startActivity(intent);
                }
            });
            Act2btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(entry.this, ClientMain.class);
                    startActivity(intent);
                }
            });

    }
}
