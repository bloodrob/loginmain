package com.example.akash.loginmain;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ResetPassword extends AppCompatActivity {

    EditText email1;
    Button back1, res_pass1;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email1 = (EditText)findViewById(R.id.res_email);
        back1 = (Button)findViewById(R.id.back_login);
        res_pass1 = (Button)findViewById(R.id.res_pass);

        auth = FirebaseAuth.getInstance();

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, sp_login.class);
                startActivity(intent);
            }
        });

        res_pass1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String res_email = email1.getText().toString().trim();

                auth.sendPasswordResetEmail(res_email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(ResetPassword.this, " You have been sent an Email to reset your password, check your email", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(ResetPassword.this, sp_login.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(ResetPassword.this, "Failed to sent the message", Toast.LENGTH_LONG).show();
                                    return;
                                }

                            }
                        });
            }
        });


    }
}
