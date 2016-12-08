package com.example.android.quakereport;

/**
 * Created by Bach Lap on 12/3/2016.
 */

public class Earthquake {

    private double mMagitude;
    private String mPlace;
    private String mTime;
    private String mURL;

    public Earthquake(double Magitude, String Place, String Time, String url) {
        mMagitude = Magitude;
        mPlace = Place;
        mTime = Time;
        mURL = url;
    }

    public double getMagitude() { return mMagitude;}
    public String getPlace() {return  mPlace;}
    public String getTime() {return mTime;}

    public String getURL() {
        return mURL;
    }
}
