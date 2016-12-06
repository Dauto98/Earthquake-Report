package com.example.android.quakereport;

/**
 * Created by Bach Lap on 12/3/2016.
 */

public class Earthquake {

    private double mMagitude;
    private String mPlace;
    private String mTime;

    public Earthquake (double Magitude, String Place, String Time){
        mMagitude = Magitude;
        mPlace = Place;
        mTime = Time;
    }

    public double getMagitude() { return mMagitude;}
    public String getPlace() {return  mPlace;}
    public String getTime() {return mTime;}
}
