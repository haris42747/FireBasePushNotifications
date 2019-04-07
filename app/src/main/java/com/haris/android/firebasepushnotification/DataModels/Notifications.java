package com.haris.android.firebasepushnotification.DataModels;

public class Notifications {

    private String from;
    private String message;

    public Notifications(String from, String message) {
        this.from = from;
        this.message = message;
    }

    public Notifications() {
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
