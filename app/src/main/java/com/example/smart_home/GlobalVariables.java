package com.example.smart_home;

import android.app.Application;

public class GlobalVariables extends  Application{

    private String UserIDContactPerson;
    private String UserIDUser;


        public String getUserIDContactPerson() {

            return UserIDContactPerson;
        }

        public void setUserIDContactPerson(String contactpersonuserid ) {

            UserIDContactPerson = contactpersonuserid;

        }

        public String getUserIDUser() {

            return UserIDUser;
        }

        public void setUserIDUser(String useriduser) {

            UserIDUser = useriduser;
        }

}
