package com.example.akash.loginmain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ResultSearch extends AppCompatActivity {

    TextView name, address, mobile, email;
    EditText aName;
    Button submit1, submit2;
    public static final String TAG = ResultSearch.class.getSimpleName() ;

    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);

        email = (TextView)findViewById(R.id.client_email);
        name = (TextView) findViewById(R.id.client_name);
        address = (TextView) findViewById(R.id.client_address);
        mobile = (TextView)findViewById(R.id.client_mobile);
        aName = (EditText)findViewById(R.id.a_name);
        submit1 = (Button)findViewById(R.id.submit);
        submit2 = (Button)findViewById(R.id.listSrc);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("client_info");

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultSearch.this, ListResult.class);
                startActivity(intent);
            }
        });

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(ResultSearch.this, ResultSearch.class);
             //   startActivity(intent);

                //    }
                //});

                final String a_name = aName.getText().toString().trim();


               final List<ResultRsc> ResultList = new ArrayList<ResultRsc>();
                ref.addChildEventListener(new ChildEventListener() {


                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                           //this is the object of the other model class , where variable declared and initiated

                           ResultRsc src = dataSnapshot.getValue(ResultRsc.class);
                           ResultList.add(src);
                           //  ResultRsc list1 = ResultList.get(ResultList.size());



                               if (src.client_name.equals(a_name)) {

                           name.setText(src.client_name);
                           address.setText(src.client_address);
                           email.setText(src.client_email);
                           mobile.setText(src.client_mobile);

                              }
                     /*   else {
                            Toast.makeText(ResultSearch.this, " Failed to show", Toast.LENGTH_LONG).show();
                        }*/

                           //    Log.i(TAG, "Name of all client is = " + src.client_name);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                       Toast.makeText(ResultSearch.this,"Failed to read the data", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Failed to read the user ", databaseError.toException());

                    }
                });

            }
        });
    }
}
