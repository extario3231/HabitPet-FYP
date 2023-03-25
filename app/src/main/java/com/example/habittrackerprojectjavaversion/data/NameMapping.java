package com.example.habittrackerprojectjavaversion.data;

import android.net.Uri;

public class NameMapping {

    private String habitname;
    private int mImgResId;
    private Uri mUri;
    private boolean isFavorite;


    public NameMapping(String tohabitname, int imgResId){
        habitname = tohabitname;
        mImgResId = imgResId;
        mUri = null;
        isFavorite = false;
    }

    public NameMapping(String tohabitname, Uri uri){
        habitname = tohabitname;
        mUri = uri;
        mImgResId = 0;
        isFavorite = false;
    }

    public String getHabitname(){
        return habitname;
    }

    public int getImageResId(){
        return mImgResId;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void NFavorite() {
        isFavorite = false;
    }

    public void toggleFavorite() {
        isFavorite = true;
    }

    public Uri getmUri() { return mUri; }

}