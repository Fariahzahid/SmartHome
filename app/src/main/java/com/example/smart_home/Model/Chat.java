package com.example.smart_home.Model;

public class Chat {

    private String sender;
    private String reciver;
    private String message;
    private String sendername;
    private String recivername;

    public Chat(String sender, String reciver, String message, String sendername, String recivername) {
        this.sender = sender;
        this.reciver = reciver;
        this.message = message;
        this.sendername = sendername;
        this.recivername = recivername;
    }

    public Chat() {

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getRecivername() {
        return recivername;
    }

    public void setRecivername(String recivername) {
        this.recivername = recivername;
    }
}
