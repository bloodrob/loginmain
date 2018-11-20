package com.example.akash.loginmain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SP_register extends AppCompatActivity {


    EditText email, password, rPassword;
    Button submit1;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_register);

        auth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.sp_email);
        password = (EditText) findViewById(R.id.sp_password);
        rPassword = (EditText) findViewById(R.id.sp_Rpassword);
        submit1 = (Button) findViewById(R.id.submit_sp);

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sp_email = email.getText().toString().trim();
                String sp_password = password.getText().toString().trim();
                String sp_Rpassword = rPassword.getText().toString().trim();

                if(TextUtils.isEmpty(sp_email)) {
                    Toast.makeText(SP_register.this, "Email required !!!", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(sp_password)) {
                    Toast.makeText(SP_register.this, " Password required !!!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (sp_password.length()<8) {
                    Toast.makeText(SP_register.this, " Lenght must be longer than 8", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(sp_Rpassword)) {
                    Toast.makeText(SP_register.this, " Retype the password ", Toast.LENGTH_LONG).show();
                    return;
                }

                if (! sp_password.equals(sp_Rpassword)) {
                    Toast.makeText(SP_register.this, "Password not match !!!", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(sp_email, sp_password)
                        .addOnCompleteListener(SP_register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete( Task<AuthResult> task) {

                                if(! task.isSuccessful()) {
                                    Toast.makeText(SP_register.this, " Registration failed due to some reason", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                else {

                                    Toast.makeText(SP_register.this, " Completed registration, now log in to your account ", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SP_register.this, sp_login.class);
                                    startActivity(intent);
                                }

                            }
                        });


            }
        });

    }
}
