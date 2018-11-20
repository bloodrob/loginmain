package com.example.akash.loginmain;

import java.util.ArrayList;
import java.lang.String;

/**
 * Created by R19 on 11/8/2018.
 */

public class ListRsc {

      /*  public String client_name;
        public String client_address;
        public String client_email;
        public String client_mobile; */
        ArrayList<String> client_name = new ArrayList<String>();
        ArrayList<String> client_email = new ArrayList<String>();
        ArrayList<String> client_address = new ArrayList<String>();
        ArrayList<String> client_mobile = new ArrayList<String>();
      // public String name;


        public ListRsc() {

        }

    public ArrayList<String> getClient_name() {
        return client_name;
    }

    public ArrayList<String> getClient_email() {
        return client_email;
    }

    public ArrayList<String> getClient_address() {
        return client_address;
    }

    public ArrayList<String> getClient_mobile() {
        return client_mobile;
    }
    /*  public ListRsc(String name) {
                this.name = name; }  */

       /* public ListRsc(String client_email, String client_name, String client_address, String client_mobile) {
            this.client_email = client_email;
            this.client_name = client_name;
            this.client_address = client_address;
            this.client_mobile = client_mobile;

            }  */
}
