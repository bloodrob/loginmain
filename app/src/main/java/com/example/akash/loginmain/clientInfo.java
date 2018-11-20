package com.example.akash.loginmain;

/**
 * Created by akash on 10/23/2018.
 */

public class clientInfo {

    public String activeId;
    public String client_email;
    public String client_name;
    public String client_address;
    public String client_mobile;

    //default constructor required for calls to DataSnapshot.getValue(clientInfo.class)

    public clientInfo(){

    }

    public clientInfo(String client_email, String client_name, String client_address, String client_mobile) {
        this.client_email = client_email;
        this.client_name = client_name;
        this.client_address = client_address;
        this.client_mobile = client_mobile;

    }
}
