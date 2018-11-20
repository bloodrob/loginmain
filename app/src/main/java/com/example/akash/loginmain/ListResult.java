package com.example.akash.loginmain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class ListResult extends AppCompatActivity {

    TextView name, email, address, mobile;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_result);

        name = (TextView)findViewById(R.id.client_name);
        email = (TextView) findViewById(R.id.client_email);
        address = (TextView) findViewById(R.id.client_address);
        mobile = (TextView) findViewById(R.id.client_mobile);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Client_info");

        final List<ArrayList<String>> Reslist0 = new ArrayList<>();
        final List<ArrayList<String>> Reslist1 = new ArrayList<>();
        final List<ArrayList<String>> Reslist2 = new ArrayList<>();
        final List<ArrayList<String>> Reslist3 = new ArrayList<>();
        //final List<ArrayList<ListRsc>> Reslist4 = new ArrayList<>();


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               // for (DataSnapshot list1: dataSnapshot.getChildren()) {
                  //  ListRsc src = list1.getValue(ListRsc.class);
                ListRsc src = dataSnapshot.getValue(ListRsc.class);

                    ArrayList<String> client_name1 = src.getClient_name();

                 //   Reslist4.add(src.name.size());
                          //  Reslist0.add(src.client_name1);

                                for (int i =0; i<client_name1.size(); i++) {
                                    name.setText(name.getText() + src.client_name.get(i) + " , ");
                                }
                        /*    Reslist1.add(src.client_email);
                                for (int i =0; i<src.client_email.size(); i++) {
                                    email.setText(email.getText() + src.client_email.get(i) + " , ");
                                }
                            Reslist2.add(src.client_address);
                                for (int i =0; i<src.client_address.size(); i++) {
                                    address.setText(address.getText() + src.client_address.get(i) + " , ");
                                }
                            Reslist3.add(src.client_mobile);
                                for (int i =0; i<src.client_mobile.size(); i++) {
                                    mobile.setText(mobile.getText() + src.client_mobile.get(i) + " , ");
                                }  */

                //   Log.d("Result is :", src.client_name + " " + src.client_email +  " " + src.client_address + " " + src.client_mobile );
               // }
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
                Toast.makeText(ListResult.this, "Database Error", Toast.LENGTH_LONG).show();

            }
        });
    }
}
