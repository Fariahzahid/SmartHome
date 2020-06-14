package com.example.smart_home;

import android.app.Application;

public class GlobalVariables extends  Application{

    private String UserIDContactPerson;
    private String UserIDUser;
    private float currentTemperature;

    //WEATHER STRINGS
    private String city;
    private String windspeed;
    private String weather;
    private String humidity;
    private String sunset;
    private String sunrise;
    private String temperature;
    //    SLEEP_MODE_BED ROOM
    private String sleep_mode_bedroom_light;
    private String sleep_mode_bedroom_charging;
    private String sleep_mode_bedroom_windowblind;
    private String sleep_mode_bedroom_nightlamp;
    private String sleep_mode_bedroom_lightintensity;
    private String sleep_mode_bedroom_ac;
    private String sleep_mode_bedroom_heating;
    private String sleep_mode_bedroom_ac_temperature;
    private String sleep_mode_bedroom_heating_temperature;

    //    SLEEP_MODE_KITCHEN
    private String sleep_mode_kitchen_light;
    private String sleep_mode_kitchen_windowblind;
    private String sleep_mode_kitchen_stove;
    private String sleep_mode_kitchen_oven;
    private String sleep_mode_kitchen_refrigrator;
    private String sleep_mode_kitchen_heating;
    private String sleep_mode_kitchen_ac;
    private String sleep_mode_kitchen_ac_temperature;
    private String sleep_mode_kitchen_heating_temperature;
    //    SLEEP_MODE_LIVING ROOM
    private String sleep_mode_livingroom_light;
    private String sleep_mode_livingroom_windowblind;
    private String sleep_mode_livingroom_tablelamp;
    private String sleep_mode_livingroom_television;
    private String sleep_mode_livingroom_lightintensity;
    private String sleep_mode_livingroom_heating;
    private String sleep_mode_livingroom_ac;
    private String sleep_mode_livingroom_ac_temperature;
    private String sleep_mode_livingroom_heating_temperature;
    //SLEEP_MODE_WC
    private String sleep_mode_wc_light;
    private String sleep_mode_wc_fan;
    private String sleep_mode_wc_windowblind;
    private String sleep_mode_wc_heating;
    private String sleep_mode_wc_heating_temperature;
    //SLEEP_MODE_BATH ROOM
    private String sleep_mode_bathroom_light;
    private String sleep_mode_bathroom_fan;
    private String sleep_mode_bathroom_windowblind;
    private String sleep_mode_bathroom_heating;


    //    MOVE_OUT_BED ROOM
    private String moveout_mode_bedroom_light;
    private String moveout_mode_bedroom_charging;
    private String moveout_mode_bedroom_windowblind;
    private String moveout_mode_bedroom_nightlamp;
    private String moveout_mode_bedroom_lightintensity;
    private String moveout_mode_bedroom_ac;
    private String moveout_mode_bedroom_heating;
    private String moveout_mode_bedroom_ac_temperature;
    private String moveout_mode_bedroom_heating_temperature;

    //    KITCHEN
    private String moveout_mode_kitchen_light;
    private String moveout_mode_kitchen_windowblind;
    private String moveout_mode_kitchen_stove;
    private String moveout_mode_kitchen_oven;
    private String moveout_mode_kitchen_refrigrator;
    private String moveout_mode_kitchen_heating;
    private String moveout_mode_kitchen_ac;
    private String moveout_mode_kitchen_ac_temperature;
    private String moveout_mode_kitchen_heating_temperature;
    //    LIVING ROOM
    private String moveout_mode_livingroom_light;
    private String moveout_mode_livingroom_windowblind;
    private String moveout_mode_livingroom_tablelamp;
    private String moveout_mode_livingroom_television;
    private String moveout_mode_livingroom_heating;
    private String moveout_mode_livingroom_lightintensity;
    private String moveout_mode_livingroom_ac;
    private String moveout_mode_livingroom_ac_temperature;
    private String moveout_mode_livingroom_heating_temperature;
    //WC
    private String moveout_mode_wc_light;
    private String moveout_mode_wc_fan;
    private String moveout_mode_wc_windowblind;
    private String moveout_mode_wc_heating;
    private String moveout_mode_wc_heating_temperature;
    //BATH ROOM
    private String moveout_mode_bathroom_light;
    private String moveout_mode_bathroom_fan;
    private String moveout_mode_bathroom_windowblind;
    private String moveout_mode_bathroom_heating;


    //Manual Mode
    //    BED ROOM
    private String manual_mode_bedroom_light;
    private String manual_mode_bedroom_charging;
    private String manual_mode_bedroom_windowblind;
    private String manual_mode_bedroom_nightlamp;
    private String manual_mode_bedroom_lightintensity;
    private String manual_mode_bedroom_ac;
    private String manual_mode_bedroom_heating;
    private String manual_mode_bedroom_ac_temperature;
    private String manual_mode_bedroom_heating_temperature;

    //    KITCHEN
    private String manual_mode_kitchen_light;
    private String manual_mode_kitchen_windowblind;
    private String manual_mode_kitchen_stove;
    private String manual_mode_kitchen_oven;
    private String manual_mode_kitchen_heating;
    private String manual_mode_kitchen_ac;
    private String manual_mode_kitchen_ac_temperature;
    private String manual_mode_kitchen_heating_temperature;
    //    LIVING ROOM
    private String manual_mode_livingroom_light;
    private String manual_mode_livingroom_windowblind;
    private String manual_mode_livingroom_tablelamp;
    private String manual_mode_livingroom_television;
    private String manual_mode_livingroom_lightintensity;
    private String manual_mode_livingroom_heating;
    private String manual_mode_livingroom_ac;
    private String manual_mode_livingroom_ac_temperature;
    private String manual_mode_livingroom_heating_temperature;
    //WC
    private String manual_mode_wc_light;
    private String manual_mode_wc_fan;
    private String manual_mode_wc_windowblind;
    private String manual_mode_wc_heating;
    private String manual_mode_wc_heating_temperature;
    //BATH ROOM
    private String manual_mode_bathroom_light;
    private String manual_mode_bathroom_fan;
    private String manual_mode_bathroom_windowblind;
    private String manual_mode_bathroom_heating;

    //AUTOMATIC Mode
    //    BED ROOM
//    private String automatic_mode_bedroom_light;
//    private String automatic_mode_bedroom_charging;
//    private String automatic_mode_bedroom_windowblind;
//    private String automatic_mode_bedroom_nightlamp;
//    private String automatic_mode_bedroom_lightintensity;
    private String automatic_mode_bedroom_ac;
    private String automatic_mode_bedroom_heating;
    private String automatic_mode_bedroom_ac_temperature;
    private String automatic_mode_bedroom_heating_temperature;

    //    KITCHEN
//    private String automatic_mode_kitchen_light;
//    private String automatic_mode_kitchen_windowblind;
//    private String automatic_mode_kitchen_stove;
//    private String automatic_mode_kitchen_oven;
    private String automatic_mode_kitchen_heating;
    private String automatic_mode_kitchen_ac;
    private String automatic_mode_kitchen_ac_temperature;
    private String automatic_mode_kitchen_heating_temperature;
    //    LIVING ROOM
//    private String automatic_mode_livingroom_light;
//    private String automatic_mode_livingroom_windowblind;
//    private String automatic_mode_livingroom_tablelamp;
//    private String automatic_mode_livingroom_television;
//    private String automatic_mode_livingroom_lightintensity;
    private String automatic_mode_livingroom_heating;
    private String automatic_mode_livingroom_ac;
    private String automatic_mode_livingroom_ac_temperature;
    private String automatic_mode_livingroom_heating_temperature;
    //WC
//    private String automatic_mode_wc_light;
//    private String automatic_mode_wc_fan;
//    private String automatic_mode_wc_windowblind;
    private String automatic_mode_wc_heating;
    private String automatic_mode_wc_heating_temperature;
    //BATH ROOM
//    private String automatic_mode_bathroom_light;
//    private String automatic_mode_bathroom_fan;
    private String automatic_mode_bathroom_windowblind;
    private String automatic_mode_bathroom_heating;
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

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSleep_mode_bedroom_light() {
        return sleep_mode_bedroom_light;
    }

    public void setSleep_mode_bedroom_light(String sleep_mode_bedroom_light) {
        this.sleep_mode_bedroom_light = sleep_mode_bedroom_light;
    }

    public String getSleep_mode_bedroom_charging() {
        return sleep_mode_bedroom_charging;
    }

    public void setSleep_mode_bedroom_charging(String sleep_mode_bedroom_charging) {
        this.sleep_mode_bedroom_charging = sleep_mode_bedroom_charging;
    }

    public String getSleep_mode_bedroom_windowblind() {
        return sleep_mode_bedroom_windowblind;
    }

    public void setSleep_mode_bedroom_windowblind(String sleep_mode_bedroom_windowblind) {
        this.sleep_mode_bedroom_windowblind = sleep_mode_bedroom_windowblind;
    }

    public String getSleep_mode_bedroom_nightlamp() {
        return sleep_mode_bedroom_nightlamp;
    }

    public void setSleep_mode_bedroom_nightlamp(String sleep_mode_bedroom_nightlamp) {
        this.sleep_mode_bedroom_nightlamp = sleep_mode_bedroom_nightlamp;
    }

    public String getSleep_mode_bedroom_lightintensity() {
        return sleep_mode_bedroom_lightintensity;
    }

    public void setSleep_mode_bedroom_lightintensity(String sleep_mode_bedroom_lightintensity) {
        this.sleep_mode_bedroom_lightintensity = sleep_mode_bedroom_lightintensity;
    }

    public String getSleep_mode_bedroom_ac() {
        return sleep_mode_bedroom_ac;
    }

    public void setSleep_mode_bedroom_ac(String sleep_mode_bedroom_ac) {
        this.sleep_mode_bedroom_ac = sleep_mode_bedroom_ac;
    }

    public String getSleep_mode_bedroom_heating() {
        return sleep_mode_bedroom_heating;
    }

    public void setSleep_mode_bedroom_heating(String sleep_mode_bedroom_heating) {
        this.sleep_mode_bedroom_heating = sleep_mode_bedroom_heating;
    }

    public String getSleep_mode_bedroom_ac_temperature() {
        return sleep_mode_bedroom_ac_temperature;
    }

    public void setSleep_mode_bedroom_ac_temperature(String sleep_mode_bedroom_ac_temperature) {
        this.sleep_mode_bedroom_ac_temperature = sleep_mode_bedroom_ac_temperature;
    }

    public String getSleep_mode_bedroom_heating_temperature() {
        return sleep_mode_bedroom_heating_temperature;
    }

    public void setSleep_mode_bedroom_heating_temperature(String sleep_mode_bedroom_heating_temperature) {
        this.sleep_mode_bedroom_heating_temperature = sleep_mode_bedroom_heating_temperature;
    }

    public String getSleep_mode_kitchen_light() {
        return sleep_mode_kitchen_light;
    }

    public void setSleep_mode_kitchen_light(String sleep_mode_kitchen_light) {
        this.sleep_mode_kitchen_light = sleep_mode_kitchen_light;
    }

    public String getSleep_mode_kitchen_windowblind() {
        return sleep_mode_kitchen_windowblind;
    }

    public void setSleep_mode_kitchen_windowblind(String sleep_mode_kitchen_windowblind) {
        this.sleep_mode_kitchen_windowblind = sleep_mode_kitchen_windowblind;
    }

    public String getSleep_mode_kitchen_stove() {
        return sleep_mode_kitchen_stove;
    }

    public void setSleep_mode_kitchen_stove(String sleep_mode_kitchen_stove) {
        this.sleep_mode_kitchen_stove = sleep_mode_kitchen_stove;
    }

    public String getSleep_mode_kitchen_oven() {
        return sleep_mode_kitchen_oven;
    }

    public void setSleep_mode_kitchen_oven(String sleep_mode_kitchen_oven) {
        this.sleep_mode_kitchen_oven = sleep_mode_kitchen_oven;
    }

    public String getSleep_mode_kitchen_refrigrator() {
        return sleep_mode_kitchen_refrigrator;
    }

    public void setSleep_mode_kitchen_refrigrator(String sleep_mode_kitchen_refrigrator) {
        this.sleep_mode_kitchen_refrigrator = sleep_mode_kitchen_refrigrator;
    }

    public String getSleep_mode_kitchen_heating() {
        return sleep_mode_kitchen_heating;
    }

    public void setSleep_mode_kitchen_heating(String sleep_mode_kitchen_heating) {
        this.sleep_mode_kitchen_heating = sleep_mode_kitchen_heating;
    }

    public String getSleep_mode_kitchen_ac() {
        return sleep_mode_kitchen_ac;
    }

    public void setSleep_mode_kitchen_ac(String sleep_mode_kitchen_ac) {
        this.sleep_mode_kitchen_ac = sleep_mode_kitchen_ac;
    }

    public String getSleep_mode_kitchen_ac_temperature() {
        return sleep_mode_kitchen_ac_temperature;
    }

    public void setSleep_mode_kitchen_ac_temperature(String sleep_mode_kitchen_ac_temperature) {
        this.sleep_mode_kitchen_ac_temperature = sleep_mode_kitchen_ac_temperature;
    }

    public String getSleep_mode_kitchen_heating_temperature() {
        return sleep_mode_kitchen_heating_temperature;
    }

    public void setSleep_mode_kitchen_heating_temperature(String sleep_mode_kitchen_heating_temperature) {
        this.sleep_mode_kitchen_heating_temperature = sleep_mode_kitchen_heating_temperature;
    }

    public String getSleep_mode_livingroom_light() {
        return sleep_mode_livingroom_light;
    }

    public void setSleep_mode_livingroom_light(String sleep_mode_livingroom_light) {
        this.sleep_mode_livingroom_light = sleep_mode_livingroom_light;
    }

    public String getSleep_mode_livingroom_windowblind() {
        return sleep_mode_livingroom_windowblind;
    }

    public void setSleep_mode_livingroom_windowblind(String sleep_mode_livingroom_windowblind) {
        this.sleep_mode_livingroom_windowblind = sleep_mode_livingroom_windowblind;
    }

    public String getSleep_mode_livingroom_tablelamp() {
        return sleep_mode_livingroom_tablelamp;
    }

    public void setSleep_mode_livingroom_tablelamp(String sleep_mode_livingroom_tablelamp) {
        this.sleep_mode_livingroom_tablelamp = sleep_mode_livingroom_tablelamp;
    }

    public String getSleep_mode_livingroom_television() {
        return sleep_mode_livingroom_television;
    }

    public void setSleep_mode_livingroom_television(String sleep_mode_livingroom_television) {
        this.sleep_mode_livingroom_television = sleep_mode_livingroom_television;
    }

    public String getSleep_mode_livingroom_lightintensity() {
        return sleep_mode_livingroom_lightintensity;
    }

    public void setSleep_mode_livingroom_lightintensity(String sleep_mode_livingroom_lightintensity) {
        this.sleep_mode_livingroom_lightintensity = sleep_mode_livingroom_lightintensity;
    }

    public String getSleep_mode_livingroom_heating() {
        return sleep_mode_livingroom_heating;
    }

    public void setSleep_mode_livingroom_heating(String sleep_mode_livingroom_heating) {
        this.sleep_mode_livingroom_heating = sleep_mode_livingroom_heating;
    }

    public String getSleep_mode_livingroom_ac() {
        return sleep_mode_livingroom_ac;
    }

    public void setSleep_mode_livingroom_ac(String sleep_mode_livingroom_ac) {
        this.sleep_mode_livingroom_ac = sleep_mode_livingroom_ac;
    }

    public String getSleep_mode_livingroom_ac_temperature() {
        return sleep_mode_livingroom_ac_temperature;
    }

    public void setSleep_mode_livingroom_ac_temperature(String sleep_mode_livingroom_ac_temperature) {
        this.sleep_mode_livingroom_ac_temperature = sleep_mode_livingroom_ac_temperature;
    }

    public String getSleep_mode_livingroom_heating_temperature() {
        return sleep_mode_livingroom_heating_temperature;
    }

    public void setSleep_mode_livingroom_heating_temperature(String sleep_mode_livingroom_heating_temperature) {
        this.sleep_mode_livingroom_heating_temperature = sleep_mode_livingroom_heating_temperature;
    }

    public String getSleep_mode_wc_light() {
        return sleep_mode_wc_light;
    }

    public void setSleep_mode_wc_light(String sleep_mode_wc_light) {
        this.sleep_mode_wc_light = sleep_mode_wc_light;
    }

    public String getSleep_mode_wc_fan() {
        return sleep_mode_wc_fan;
    }

    public void setSleep_mode_wc_fan(String sleep_mode_wc_fan) {
        this.sleep_mode_wc_fan = sleep_mode_wc_fan;
    }

    public String getSleep_mode_wc_windowblind() {
        return sleep_mode_wc_windowblind;
    }

    public void setSleep_mode_wc_windowblind(String sleep_mode_wc_windowblind) {
        this.sleep_mode_wc_windowblind = sleep_mode_wc_windowblind;
    }

    public String getSleep_mode_wc_heating() {
        return sleep_mode_wc_heating;
    }

    public void setSleep_mode_wc_heating(String sleep_mode_wc_heating) {
        this.sleep_mode_wc_heating = sleep_mode_wc_heating;
    }

    public String getSleep_mode_wc_heating_temperature() {
        return sleep_mode_wc_heating_temperature;
    }

    public void setSleep_mode_wc_heating_temperature(String sleep_mode_wc_heating_temperature) {
        this.sleep_mode_wc_heating_temperature = sleep_mode_wc_heating_temperature;
    }

    public String getSleep_mode_bathroom_light() {
        return sleep_mode_bathroom_light;
    }

    public void setSleep_mode_bathroom_light(String sleep_mode_bathroom_light) {
        this.sleep_mode_bathroom_light = sleep_mode_bathroom_light;
    }

    public String getSleep_mode_bathroom_fan() {
        return sleep_mode_bathroom_fan;
    }

    public void setSleep_mode_bathroom_fan(String sleep_mode_bathroom_fan) {
        this.sleep_mode_bathroom_fan = sleep_mode_bathroom_fan;
    }

    public String getSleep_mode_bathroom_windowblind() {
        return sleep_mode_bathroom_windowblind;
    }

    public void setSleep_mode_bathroom_windowblind(String sleep_mode_bathroom_windowblind) {
        this.sleep_mode_bathroom_windowblind = sleep_mode_bathroom_windowblind;
    }

    public String getSleep_mode_bathroom_heating() {
        return sleep_mode_bathroom_heating;
    }

    public void setSleep_mode_bathroom_heating(String sleep_mode_bathroom_heating) {
        this.sleep_mode_bathroom_heating = sleep_mode_bathroom_heating;
    }

    public String getMoveout_mode_bedroom_light() {
        return moveout_mode_bedroom_light;
    }

    public void setMoveout_mode_bedroom_light(String moveout_mode_bedroom_light) {
        this.moveout_mode_bedroom_light = moveout_mode_bedroom_light;
    }

    public String getMoveout_mode_bedroom_charging() {
        return moveout_mode_bedroom_charging;
    }

    public void setMoveout_mode_bedroom_charging(String moveout_mode_bedroom_charging) {
        this.moveout_mode_bedroom_charging = moveout_mode_bedroom_charging;
    }

    public String getMoveout_mode_bedroom_windowblind() {
        return moveout_mode_bedroom_windowblind;
    }

    public void setMoveout_mode_bedroom_windowblind(String moveout_mode_bedroom_windowblind) {
        this.moveout_mode_bedroom_windowblind = moveout_mode_bedroom_windowblind;
    }

    public String getMoveout_mode_bedroom_nightlamp() {
        return moveout_mode_bedroom_nightlamp;
    }

    public void setMoveout_mode_bedroom_nightlamp(String moveout_mode_bedroom_nightlamp) {
        this.moveout_mode_bedroom_nightlamp = moveout_mode_bedroom_nightlamp;
    }

    public String getMoveout_mode_bedroom_lightintensity() {
        return moveout_mode_bedroom_lightintensity;
    }

    public void setMoveout_mode_bedroom_lightintensity(String moveout_mode_bedroom_lightintensity) {
        this.moveout_mode_bedroom_lightintensity = moveout_mode_bedroom_lightintensity;
    }

    public String getMoveout_mode_bedroom_ac() {
        return moveout_mode_bedroom_ac;
    }

    public void setMoveout_mode_bedroom_ac(String moveout_mode_bedroom_ac) {
        this.moveout_mode_bedroom_ac = moveout_mode_bedroom_ac;
    }

    public String getMoveout_mode_bedroom_heating() {
        return moveout_mode_bedroom_heating;
    }

    public void setMoveout_mode_bedroom_heating(String moveout_mode_bedroom_heating) {
        this.moveout_mode_bedroom_heating = moveout_mode_bedroom_heating;
    }

    public String getMoveout_mode_bedroom_ac_temperature() {
        return moveout_mode_bedroom_ac_temperature;
    }

    public void setMoveout_mode_bedroom_ac_temperature(String moveout_mode_bedroom_ac_temperature) {
        this.moveout_mode_bedroom_ac_temperature = moveout_mode_bedroom_ac_temperature;
    }

    public String getMoveout_mode_bedroom_heating_temperature() {
        return moveout_mode_bedroom_heating_temperature;
    }

    public void setMoveout_mode_bedroom_heating_temperature(String moveout_mode_bedroom_heating_temperature) {
        this.moveout_mode_bedroom_heating_temperature = moveout_mode_bedroom_heating_temperature;
    }

    public String getMoveout_mode_kitchen_light() {
        return moveout_mode_kitchen_light;
    }

    public void setMoveout_mode_kitchen_light(String moveout_mode_kitchen_light) {
        this.moveout_mode_kitchen_light = moveout_mode_kitchen_light;
    }

    public String getMoveout_mode_kitchen_windowblind() {
        return moveout_mode_kitchen_windowblind;
    }

    public void setMoveout_mode_kitchen_windowblind(String moveout_mode_kitchen_windowblind) {
        this.moveout_mode_kitchen_windowblind = moveout_mode_kitchen_windowblind;
    }

    public String getMoveout_mode_kitchen_stove() {
        return moveout_mode_kitchen_stove;
    }

    public void setMoveout_mode_kitchen_stove(String moveout_mode_kitchen_stove) {
        this.moveout_mode_kitchen_stove = moveout_mode_kitchen_stove;
    }

    public String getMoveout_mode_kitchen_oven() {
        return moveout_mode_kitchen_oven;
    }

    public void setMoveout_mode_kitchen_oven(String moveout_mode_kitchen_oven) {
        this.moveout_mode_kitchen_oven = moveout_mode_kitchen_oven;
    }

    public String getMoveout_mode_kitchen_refrigrator() {
        return moveout_mode_kitchen_refrigrator;
    }

    public void setMoveout_mode_kitchen_refrigrator(String moveout_mode_kitchen_refrigrator) {
        this.moveout_mode_kitchen_refrigrator = moveout_mode_kitchen_refrigrator;
    }

    public String getMoveout_mode_kitchen_heating() {
        return moveout_mode_kitchen_heating;
    }

    public void setMoveout_mode_kitchen_heating(String moveout_mode_kitchen_heating) {
        this.moveout_mode_kitchen_heating = moveout_mode_kitchen_heating;
    }

    public String getMoveout_mode_kitchen_ac() {
        return moveout_mode_kitchen_ac;
    }

    public void setMoveout_mode_kitchen_ac(String moveout_mode_kitchen_ac) {
        this.moveout_mode_kitchen_ac = moveout_mode_kitchen_ac;
    }

    public String getMoveout_mode_kitchen_ac_temperature() {
        return moveout_mode_kitchen_ac_temperature;
    }

    public void setMoveout_mode_kitchen_ac_temperature(String moveout_mode_kitchen_ac_temperature) {
        this.moveout_mode_kitchen_ac_temperature = moveout_mode_kitchen_ac_temperature;
    }

    public String getMoveout_mode_kitchen_heating_temperature() {
        return moveout_mode_kitchen_heating_temperature;
    }

    public void setMoveout_mode_kitchen_heating_temperature(String moveout_mode_kitchen_heating_temperature) {
        this.moveout_mode_kitchen_heating_temperature = moveout_mode_kitchen_heating_temperature;
    }

    public String getMoveout_mode_livingroom_light() {
        return moveout_mode_livingroom_light;
    }

    public void setMoveout_mode_livingroom_light(String moveout_mode_livingroom_light) {
        this.moveout_mode_livingroom_light = moveout_mode_livingroom_light;
    }

    public String getMoveout_mode_livingroom_windowblind() {
        return moveout_mode_livingroom_windowblind;
    }

    public void setMoveout_mode_livingroom_windowblind(String moveout_mode_livingroom_windowblind) {
        this.moveout_mode_livingroom_windowblind = moveout_mode_livingroom_windowblind;
    }

    public String getMoveout_mode_livingroom_tablelamp() {
        return moveout_mode_livingroom_tablelamp;
    }

    public void setMoveout_mode_livingroom_tablelamp(String moveout_mode_livingroom_tablelamp) {
        this.moveout_mode_livingroom_tablelamp = moveout_mode_livingroom_tablelamp;
    }

    public String getMoveout_mode_livingroom_television() {
        return moveout_mode_livingroom_television;
    }

    public void setMoveout_mode_livingroom_television(String moveout_mode_livingroom_television) {
        this.moveout_mode_livingroom_television = moveout_mode_livingroom_television;
    }

    public String getMoveout_mode_livingroom_heating() {
        return moveout_mode_livingroom_heating;
    }

    public void setMoveout_mode_livingroom_heating(String moveout_mode_livingroom_heating) {
        this.moveout_mode_livingroom_heating = moveout_mode_livingroom_heating;
    }

    public String getMoveout_mode_livingroom_lightintensity() {
        return moveout_mode_livingroom_lightintensity;
    }

    public void setMoveout_mode_livingroom_lightintensity(String moveout_mode_livingroom_lightintensity) {
        this.moveout_mode_livingroom_lightintensity = moveout_mode_livingroom_lightintensity;
    }

    public String getMoveout_mode_livingroom_ac() {
        return moveout_mode_livingroom_ac;
    }

    public void setMoveout_mode_livingroom_ac(String moveout_mode_livingroom_ac) {
        this.moveout_mode_livingroom_ac = moveout_mode_livingroom_ac;
    }

    public String getMoveout_mode_livingroom_ac_temperature() {
        return moveout_mode_livingroom_ac_temperature;
    }

    public void setMoveout_mode_livingroom_ac_temperature(String moveout_mode_livingroom_ac_temperature) {
        this.moveout_mode_livingroom_ac_temperature = moveout_mode_livingroom_ac_temperature;
    }

    public String getMoveout_mode_livingroom_heating_temperature() {
        return moveout_mode_livingroom_heating_temperature;
    }

    public void setMoveout_mode_livingroom_heating_temperature(String moveout_mode_livingroom_heating_temperature) {
        this.moveout_mode_livingroom_heating_temperature = moveout_mode_livingroom_heating_temperature;
    }

    public String getMoveout_mode_wc_light() {
        return moveout_mode_wc_light;
    }

    public void setMoveout_mode_wc_light(String moveout_mode_wc_light) {
        this.moveout_mode_wc_light = moveout_mode_wc_light;
    }

    public String getMoveout_mode_wc_fan() {
        return moveout_mode_wc_fan;
    }

    public void setMoveout_mode_wc_fan(String moveout_mode_wc_fan) {
        this.moveout_mode_wc_fan = moveout_mode_wc_fan;
    }

    public String getMoveout_mode_wc_windowblind() {
        return moveout_mode_wc_windowblind;
    }

    public void setMoveout_mode_wc_windowblind(String moveout_mode_wc_windowblind) {
        this.moveout_mode_wc_windowblind = moveout_mode_wc_windowblind;
    }

    public String getMoveout_mode_wc_heating() {
        return moveout_mode_wc_heating;
    }

    public void setMoveout_mode_wc_heating(String moveout_mode_wc_heating) {
        this.moveout_mode_wc_heating = moveout_mode_wc_heating;
    }

    public String getMoveout_mode_wc_heating_temperature() {
        return moveout_mode_wc_heating_temperature;
    }

    public void setMoveout_mode_wc_heating_temperature(String moveout_mode_wc_heating_temperature) {
        this.moveout_mode_wc_heating_temperature = moveout_mode_wc_heating_temperature;
    }

    public String getMoveout_mode_bathroom_light() {
        return moveout_mode_bathroom_light;
    }

    public void setMoveout_mode_bathroom_light(String moveout_mode_bathroom_light) {
        this.moveout_mode_bathroom_light = moveout_mode_bathroom_light;
    }

    public String getMoveout_mode_bathroom_fan() {
        return moveout_mode_bathroom_fan;
    }

    public void setMoveout_mode_bathroom_fan(String moveout_mode_bathroom_fan) {
        this.moveout_mode_bathroom_fan = moveout_mode_bathroom_fan;
    }

    public String getMoveout_mode_bathroom_windowblind() {
        return moveout_mode_bathroom_windowblind;
    }

    public void setMoveout_mode_bathroom_windowblind(String moveout_mode_bathroom_windowblind) {
        this.moveout_mode_bathroom_windowblind = moveout_mode_bathroom_windowblind;
    }

    public String getMoveout_mode_bathroom_heating() {
        return moveout_mode_bathroom_heating;
    }

    public void setMoveout_mode_bathroom_heating(String moveout_mode_bathroom_heating) {
        this.moveout_mode_bathroom_heating = moveout_mode_bathroom_heating;
    }

    public String getManual_mode_bedroom_light() {
        return manual_mode_bedroom_light;
    }

    public void setManual_mode_bedroom_light(String manual_mode_bedroom_light) {
        this.manual_mode_bedroom_light = manual_mode_bedroom_light;
    }

    public String getManual_mode_bedroom_charging() {
        return manual_mode_bedroom_charging;
    }

    public void setManual_mode_bedroom_charging(String manual_mode_bedroom_charging) {
        this.manual_mode_bedroom_charging = manual_mode_bedroom_charging;
    }

    public String getManual_mode_bedroom_windowblind() {
        return manual_mode_bedroom_windowblind;
    }

    public void setManual_mode_bedroom_windowblind(String manual_mode_bedroom_windowblind) {
        this.manual_mode_bedroom_windowblind = manual_mode_bedroom_windowblind;
    }

    public String getManual_mode_bedroom_nightlamp() {
        return manual_mode_bedroom_nightlamp;
    }

    public void setManual_mode_bedroom_nightlamp(String manual_mode_bedroom_nightlamp) {
        this.manual_mode_bedroom_nightlamp = manual_mode_bedroom_nightlamp;
    }

    public String getManual_mode_bedroom_lightintensity() {
        return manual_mode_bedroom_lightintensity;
    }

    public void setManual_mode_bedroom_lightintensity(String manual_mode_bedroom_lightintensity) {
        this.manual_mode_bedroom_lightintensity = manual_mode_bedroom_lightintensity;
    }

    public String getManual_mode_bedroom_ac() {
        return manual_mode_bedroom_ac;
    }

    public void setManual_mode_bedroom_ac(String manual_mode_bedroom_ac) {
        this.manual_mode_bedroom_ac = manual_mode_bedroom_ac;
    }

    public String getManual_mode_bedroom_heating() {
        return manual_mode_bedroom_heating;
    }

    public void setManual_mode_bedroom_heating(String manual_mode_bedroom_heating) {
        this.manual_mode_bedroom_heating = manual_mode_bedroom_heating;
    }

    public String getManual_mode_bedroom_ac_temperature() {
        return manual_mode_bedroom_ac_temperature;
    }

    public void setManual_mode_bedroom_ac_temperature(String manual_mode_bedroom_ac_temperature) {
        this.manual_mode_bedroom_ac_temperature = manual_mode_bedroom_ac_temperature;
    }

    public String getManual_mode_bedroom_heating_temperature() {
        return manual_mode_bedroom_heating_temperature;
    }

    public void setManual_mode_bedroom_heating_temperature(String manual_mode_bedroom_heating_temperature) {
        this.manual_mode_bedroom_heating_temperature = manual_mode_bedroom_heating_temperature;
    }

    public String getManual_mode_kitchen_light() {
        return manual_mode_kitchen_light;
    }

    public void setManual_mode_kitchen_light(String manual_mode_kitchen_light) {
        this.manual_mode_kitchen_light = manual_mode_kitchen_light;
    }

    public String getManual_mode_kitchen_windowblind() {
        return manual_mode_kitchen_windowblind;
    }

    public void setManual_mode_kitchen_windowblind(String manual_mode_kitchen_windowblind) {
        this.manual_mode_kitchen_windowblind = manual_mode_kitchen_windowblind;
    }

    public String getManual_mode_kitchen_stove() {
        return manual_mode_kitchen_stove;
    }

    public void setManual_mode_kitchen_stove(String manual_mode_kitchen_stove) {
        this.manual_mode_kitchen_stove = manual_mode_kitchen_stove;
    }

    public String getManual_mode_kitchen_oven() {
        return manual_mode_kitchen_oven;
    }

    public void setManual_mode_kitchen_oven(String manual_mode_kitchen_oven) {
        this.manual_mode_kitchen_oven = manual_mode_kitchen_oven;
    }

    public String getManual_mode_kitchen_heating() {
        return manual_mode_kitchen_heating;
    }

    public void setManual_mode_kitchen_heating(String manual_mode_kitchen_heating) {
        this.manual_mode_kitchen_heating = manual_mode_kitchen_heating;
    }

    public String getManual_mode_kitchen_ac() {
        return manual_mode_kitchen_ac;
    }

    public void setManual_mode_kitchen_ac(String manual_mode_kitchen_ac) {
        this.manual_mode_kitchen_ac = manual_mode_kitchen_ac;
    }

    public String getManual_mode_kitchen_ac_temperature() {
        return manual_mode_kitchen_ac_temperature;
    }

    public void setManual_mode_kitchen_ac_temperature(String manual_mode_kitchen_ac_temperature) {
        this.manual_mode_kitchen_ac_temperature = manual_mode_kitchen_ac_temperature;
    }

    public String getManual_mode_kitchen_heating_temperature() {
        return manual_mode_kitchen_heating_temperature;
    }

    public void setManual_mode_kitchen_heating_temperature(String manual_mode_kitchen_heating_temperature) {
        this.manual_mode_kitchen_heating_temperature = manual_mode_kitchen_heating_temperature;
    }

    public String getManual_mode_livingroom_light() {
        return manual_mode_livingroom_light;
    }

    public void setManual_mode_livingroom_light(String manual_mode_livingroom_light) {
        this.manual_mode_livingroom_light = manual_mode_livingroom_light;
    }

    public String getManual_mode_livingroom_windowblind() {
        return manual_mode_livingroom_windowblind;
    }

    public void setManual_mode_livingroom_windowblind(String manual_mode_livingroom_windowblind) {
        this.manual_mode_livingroom_windowblind = manual_mode_livingroom_windowblind;
    }

    public String getManual_mode_livingroom_tablelamp() {
        return manual_mode_livingroom_tablelamp;
    }

    public void setManual_mode_livingroom_tablelamp(String manual_mode_livingroom_tablelamp) {
        this.manual_mode_livingroom_tablelamp = manual_mode_livingroom_tablelamp;
    }

    public String getManual_mode_livingroom_television() {
        return manual_mode_livingroom_television;
    }

    public void setManual_mode_livingroom_television(String manual_mode_livingroom_television) {
        this.manual_mode_livingroom_television = manual_mode_livingroom_television;
    }

    public String getManual_mode_livingroom_lightintensity() {
        return manual_mode_livingroom_lightintensity;
    }

    public void setManual_mode_livingroom_lightintensity(String manual_mode_livingroom_lightintensity) {
        this.manual_mode_livingroom_lightintensity = manual_mode_livingroom_lightintensity;
    }

    public String getManual_mode_livingroom_heating() {
        return manual_mode_livingroom_heating;
    }

    public void setManual_mode_livingroom_heating(String manual_mode_livingroom_heating) {
        this.manual_mode_livingroom_heating = manual_mode_livingroom_heating;
    }

    public String getManual_mode_livingroom_ac() {
        return manual_mode_livingroom_ac;
    }

    public void setManual_mode_livingroom_ac(String manual_mode_livingroom_ac) {
        this.manual_mode_livingroom_ac = manual_mode_livingroom_ac;
    }

    public String getManual_mode_livingroom_ac_temperature() {
        return manual_mode_livingroom_ac_temperature;
    }

    public void setManual_mode_livingroom_ac_temperature(String manual_mode_livingroom_ac_temperature) {
        this.manual_mode_livingroom_ac_temperature = manual_mode_livingroom_ac_temperature;
    }

    public String getManual_mode_livingroom_heating_temperature() {
        return manual_mode_livingroom_heating_temperature;
    }

    public void setManual_mode_livingroom_heating_temperature(String manual_mode_livingroom_heating_temperature) {
        this.manual_mode_livingroom_heating_temperature = manual_mode_livingroom_heating_temperature;
    }

    public String getManual_mode_wc_light() {
        return manual_mode_wc_light;
    }

    public void setManual_mode_wc_light(String manual_mode_wc_light) {
        this.manual_mode_wc_light = manual_mode_wc_light;
    }

    public String getManual_mode_wc_fan() {
        return manual_mode_wc_fan;
    }

    public void setManual_mode_wc_fan(String manual_mode_wc_fan) {
        this.manual_mode_wc_fan = manual_mode_wc_fan;
    }

    public String getManual_mode_wc_windowblind() {
        return manual_mode_wc_windowblind;
    }

    public void setManual_mode_wc_windowblind(String manual_mode_wc_windowblind) {
        this.manual_mode_wc_windowblind = manual_mode_wc_windowblind;
    }

    public String getManual_mode_wc_heating() {
        return manual_mode_wc_heating;
    }

    public void setManual_mode_wc_heating(String manual_mode_wc_heating) {
        this.manual_mode_wc_heating = manual_mode_wc_heating;
    }

    public String getManual_mode_wc_heating_temperature() {
        return manual_mode_wc_heating_temperature;
    }

    public void setManual_mode_wc_heating_temperature(String manual_mode_wc_heating_temperature) {
        this.manual_mode_wc_heating_temperature = manual_mode_wc_heating_temperature;
    }

    public String getManual_mode_bathroom_light() {
        return manual_mode_bathroom_light;
    }

    public void setManual_mode_bathroom_light(String manual_mode_bathroom_light) {
        this.manual_mode_bathroom_light = manual_mode_bathroom_light;
    }

    public String getManual_mode_bathroom_fan() {
        return manual_mode_bathroom_fan;
    }

    public void setManual_mode_bathroom_fan(String manual_mode_bathroom_fan) {
        this.manual_mode_bathroom_fan = manual_mode_bathroom_fan;
    }

    public String getManual_mode_bathroom_windowblind() {
        return manual_mode_bathroom_windowblind;
    }

    public void setManual_mode_bathroom_windowblind(String manual_mode_bathroom_windowblind) {
        this.manual_mode_bathroom_windowblind = manual_mode_bathroom_windowblind;
    }

    public String getManual_mode_bathroom_heating() {
        return manual_mode_bathroom_heating;
    }

    public void setManual_mode_bathroom_heating(String manual_mode_bathroom_heating) {
        this.manual_mode_bathroom_heating = manual_mode_bathroom_heating;
    }

    public String getAutomatic_mode_bedroom_ac() {
        return automatic_mode_bedroom_ac;
    }

    public void setAutomatic_mode_bedroom_ac(String automatic_mode_bedroom_ac) {
        this.automatic_mode_bedroom_ac = automatic_mode_bedroom_ac;
    }

    public String getAutomatic_mode_bedroom_heating() {
        return automatic_mode_bedroom_heating;
    }

    public void setAutomatic_mode_bedroom_heating(String automatic_mode_bedroom_heating) {
        this.automatic_mode_bedroom_heating = automatic_mode_bedroom_heating;
    }

    public String getAutomatic_mode_bedroom_ac_temperature() {
        return automatic_mode_bedroom_ac_temperature;
    }

    public void setAutomatic_mode_bedroom_ac_temperature(String automatic_mode_bedroom_ac_temperature) {
        this.automatic_mode_bedroom_ac_temperature = automatic_mode_bedroom_ac_temperature;
    }

    public String getAutomatic_mode_bedroom_heating_temperature() {
        return automatic_mode_bedroom_heating_temperature;
    }

    public void setAutomatic_mode_bedroom_heating_temperature(String automatic_mode_bedroom_heating_temperature) {
        this.automatic_mode_bedroom_heating_temperature = automatic_mode_bedroom_heating_temperature;
    }

    public String getAutomatic_mode_kitchen_heating() {
        return automatic_mode_kitchen_heating;
    }

    public void setAutomatic_mode_kitchen_heating(String automatic_mode_kitchen_heating) {
        this.automatic_mode_kitchen_heating = automatic_mode_kitchen_heating;
    }

    public String getAutomatic_mode_kitchen_ac() {
        return automatic_mode_kitchen_ac;
    }

    public void setAutomatic_mode_kitchen_ac(String automatic_mode_kitchen_ac) {
        this.automatic_mode_kitchen_ac = automatic_mode_kitchen_ac;
    }

    public String getAutomatic_mode_kitchen_ac_temperature() {
        return automatic_mode_kitchen_ac_temperature;
    }

    public void setAutomatic_mode_kitchen_ac_temperature(String automatic_mode_kitchen_ac_temperature) {
        this.automatic_mode_kitchen_ac_temperature = automatic_mode_kitchen_ac_temperature;
    }

    public String getAutomatic_mode_kitchen_heating_temperature() {
        return automatic_mode_kitchen_heating_temperature;
    }

    public void setAutomatic_mode_kitchen_heating_temperature(String automatic_mode_kitchen_heating_temperature) {
        this.automatic_mode_kitchen_heating_temperature = automatic_mode_kitchen_heating_temperature;
    }

    public String getAutomatic_mode_livingroom_heating() {
        return automatic_mode_livingroom_heating;
    }

    public void setAutomatic_mode_livingroom_heating(String automatic_mode_livingroom_heating) {
        this.automatic_mode_livingroom_heating = automatic_mode_livingroom_heating;
    }

    public String getAutomatic_mode_livingroom_ac() {
        return automatic_mode_livingroom_ac;
    }

    public void setAutomatic_mode_livingroom_ac(String automatic_mode_livingroom_ac) {
        this.automatic_mode_livingroom_ac = automatic_mode_livingroom_ac;
    }

    public String getAutomatic_mode_livingroom_ac_temperature() {
        return automatic_mode_livingroom_ac_temperature;
    }

    public void setAutomatic_mode_livingroom_ac_temperature(String automatic_mode_livingroom_ac_temperature) {
        this.automatic_mode_livingroom_ac_temperature = automatic_mode_livingroom_ac_temperature;
    }

    public String getAutomatic_mode_livingroom_heating_temperature() {
        return automatic_mode_livingroom_heating_temperature;
    }

    public void setAutomatic_mode_livingroom_heating_temperature(String automatic_mode_livingroom_heating_temperature) {
        this.automatic_mode_livingroom_heating_temperature = automatic_mode_livingroom_heating_temperature;
    }

    public String getAutomatic_mode_wc_heating() {
        return automatic_mode_wc_heating;
    }

    public void setAutomatic_mode_wc_heating(String automatic_mode_wc_heating) {
        this.automatic_mode_wc_heating = automatic_mode_wc_heating;
    }

    public String getAutomatic_mode_wc_heating_temperature() {
        return automatic_mode_wc_heating_temperature;
    }

    public void setAutomatic_mode_wc_heating_temperature(String automatic_mode_wc_heating_temperature) {
        this.automatic_mode_wc_heating_temperature = automatic_mode_wc_heating_temperature;
    }

    public String getAutomatic_mode_bathroom_windowblind() {
        return automatic_mode_bathroom_windowblind;
    }

    public void setAutomatic_mode_bathroom_windowblind(String automatic_mode_bathroom_windowblind) {
        this.automatic_mode_bathroom_windowblind = automatic_mode_bathroom_windowblind;
    }

    public String getAutomatic_mode_bathroom_heating() {
        return automatic_mode_bathroom_heating;
    }

    public void setAutomatic_mode_bathroom_heating(String automatic_mode_bathroom_heating) {
        this.automatic_mode_bathroom_heating = automatic_mode_bathroom_heating;
    }
}
