package com.example.smart_home.Contact_Person_Screen;

import android.widget.ImageView;

public class User {

    private String UserID;
    private String Name;
    private String Email;
    private String PhoneNo;
    private String Password;
    private String Address;
    private String Gender;


    public User() {
    }

    public User(String UserID,String name, String email, String phoneno, String address,String gender,String password) {
        this.Name = name;
        this.Email = email;
        this.PhoneNo = phoneno;
        this.Address = address;
        this.Gender = gender;
        this.Password = password;
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneno) {
        this.PhoneNo = phoneno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        this.Gender = gender;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userName) {
        this.UserID = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
