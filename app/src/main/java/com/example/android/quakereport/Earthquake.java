package com.example.android.quakereport;

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;
    private String mTime;

    public Earthquake(String magnitude , String location , String date , String time){
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mTime = time;
    }



    public String getMagnitude(){return mMagnitude;}

    public String getLocation(){return mLocation;}

    public String getDate(){return mDate;}

    public String getTime(){return mTime;}

}
