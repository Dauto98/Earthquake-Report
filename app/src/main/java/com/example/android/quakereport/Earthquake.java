package com.example.android.quakereport;

/**
 * Created by Bach Lap on 12/3/2016.
 */

public class Earthquake {

    private float mMagitude;
    private String mPlace;
    private String mTime;

    public Earthquake (float Magitude, String Place, String Time){
        mMagitude = Magitude;
        mPlace = Place;
        mTime = Time;
    }

    public float getMagitude() { return mMagitude;}
    public String getPlace() {return  mPlace;}
    public String getTime() {return mTime;}
}
