package com.example.smart_home;

import android.app.Application;

public class GlobalVariables extends  Application{

    private String UserIDContactPerson;
    private String UserIDUser;
    private String ON;
    private String OFF;

    //    BED ROOM
    private String bedroom_light;
    private String bedroom_fan;
    private String bedroom_windowblind;
    private String bedroom_nightlamp;
    private String bedroom_ac;
    private String bedroom_heating;

    //    KITCHEN
    private String kitchen_light;
    private String kitchen_fan;
    private String kitchen_windowblind;
    private String kitchen_stove;
    private String kitchen_oven;
    private String kitchen_heating;
    private String kitchen_ac;
    //    LIVING ROOM
    private String livingroom_light;
    private String livingroom_fan;
    private String livingroom_windowblind;
    private String livingroom_tablelamp;
    private String livingroom_television;
    private String livingroom_heating;
    private String livingroom_ac;
    //WC
    private String wc_light;
    private String wc_fan;
    private String wc_windowblind;
    private String wc_heating;
    //BATH ROOM
    private String bathroom_light;
    private String bathroom_fan;
    private String bathroom_windowblind;
    private String bathroom_heating;


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

    public String getBedroom_light() {
        return bedroom_light;
    }

    public void setBedroom_light(String bedroom_light) {
        this.bedroom_light = bedroom_light;
    }

    public String getBedroom_fan() {
        return bedroom_fan;
    }

    public void setBedroom_fan(String bedroom_fan) {
        this.bedroom_fan = bedroom_fan;
    }

    public String getBedroom_windowblind() {
        return bedroom_windowblind;
    }

    public void setBedroom_windowblind(String bedroom_windowblind) {
        this.bedroom_windowblind = bedroom_windowblind;
    }

    public String getBedroom_nightlamp() {
        return bedroom_nightlamp;
    }

    public void setBedroom_nightlamp(String bedroom_nightlamp) {
        this.bedroom_nightlamp = bedroom_nightlamp;
    }

    public String getBedroom_ac() {
        return bedroom_ac;
    }

    public void setBedroom_ac(String bedroom_ac) {
        this.bedroom_ac = bedroom_ac;
    }

    public String getBedroom_heating() {
        return bedroom_heating;
    }

    public void setBedroom_heating(String bedroom_heating) {
        this.bedroom_heating = bedroom_heating;
    }

    public String getKitchen_light() {
        return kitchen_light;
    }

    public void setKitchen_light(String kitchen_light) {
        this.kitchen_light = kitchen_light;
    }

    public String getKitchen_fan() {
        return kitchen_fan;
    }

    public void setKitchen_fan(String kitchen_fan) {
        this.kitchen_fan = kitchen_fan;
    }

    public String getKitchen_windowblind() {
        return kitchen_windowblind;
    }

    public void setKitchen_windowblind(String kitchen_windowblind) {
        this.kitchen_windowblind = kitchen_windowblind;
    }

    public String getKitchen_stove() {
        return kitchen_stove;
    }

    public void setKitchen_stove(String kitchen_stove) {
        this.kitchen_stove = kitchen_stove;
    }

    public String getKitchen_oven() {
        return kitchen_oven;
    }

    public void setKitchen_oven(String kitchen_oven) {
        this.kitchen_oven = kitchen_oven;
    }

    public String getKitchen_heating() {
        return kitchen_heating;
    }

    public void setKitchen_heating(String kitchen_heating) {
        this.kitchen_heating = kitchen_heating;
    }

    public String getKitchen_ac() {
        return kitchen_ac;
    }

    public void setKitchen_ac(String kitchen_ac) {
        this.kitchen_ac = kitchen_ac;
    }

    public String getLivingroom_light() {
        return livingroom_light;
    }

    public void setLivingroom_light(String livingroom_light) {
        this.livingroom_light = livingroom_light;
    }

    public String getLivingroom_fan() {
        return livingroom_fan;
    }

    public void setLivingroom_fan(String livingroom_fan) {
        this.livingroom_fan = livingroom_fan;
    }

    public String getLivingroom_windowblind() {
        return livingroom_windowblind;
    }

    public void setLivingroom_windowblind(String livingroom_windowblind) {
        this.livingroom_windowblind = livingroom_windowblind;
    }

    public String getLivingroom_tablelamp() {
        return livingroom_tablelamp;
    }

    public void setLivingroom_tablelamp(String livingroom_tablelamp) {
        this.livingroom_tablelamp = livingroom_tablelamp;
    }

    public String getLivingroom_television() {
        return livingroom_television;
    }

    public void setLivingroom_television(String livingroom_television) {
        this.livingroom_television = livingroom_television;
    }

    public String getLivingroom_heating() {
        return livingroom_heating;
    }

    public void setLivingroom_heating(String livingroom_heating) {
        this.livingroom_heating = livingroom_heating;
    }

    public String getLivingroom_ac() {
        return livingroom_ac;
    }

    public void setLivingroom_ac(String livingroom_ac) {
        this.livingroom_ac = livingroom_ac;
    }

    public String getWc_light() {
        return wc_light;
    }

    public void setWc_light(String wc_light) {
        this.wc_light = wc_light;
    }

    public String getWc_fan() {
        return wc_fan;
    }

    public void setWc_fan(String wc_fan) {
        this.wc_fan = wc_fan;
    }

    public String getWc_windowblind() {
        return wc_windowblind;
    }

    public void setWc_windowblind(String wc_windowblind) {
        this.wc_windowblind = wc_windowblind;
    }

    public String getWc_heating() {
        return wc_heating;
    }

    public void setWc_heating(String wc_heating) {
        this.wc_heating = wc_heating;
    }

    public String getBathroom_light() {
        return bathroom_light;
    }

    public void setBathroom_light(String bathroom_light) {
        this.bathroom_light = bathroom_light;
    }

    public String getBathroom_fan() {
        return bathroom_fan;
    }

    public void setBathroom_fan(String bathroom_fan) {
        this.bathroom_fan = bathroom_fan;
    }

    public String getBathroom_windowblind() {
        return bathroom_windowblind;
    }

    public void setBathroom_windowblind(String bathroom_windowblind) {
        this.bathroom_windowblind = bathroom_windowblind;
    }

    public String getBathroom_heating() {
        return bathroom_heating;
    }

    public void setBathroom_heating(String bathroom_heating) {
        this.bathroom_heating = bathroom_heating;
    }





}
