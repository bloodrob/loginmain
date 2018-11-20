package com.example.akash.loginmain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ClientMain extends AppCompatActivity {


    Button login, reg;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        login = (Button)findViewById(R.id.cli_login);
        reg = (Button)findViewById(R.id.cli_reg);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() !=  null) {
            Intent intent = new Intent(ClientMain.this, client_info.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientMain.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientMain.this, register.class);
                startActivity(intent);
            }
        });

    }
}
