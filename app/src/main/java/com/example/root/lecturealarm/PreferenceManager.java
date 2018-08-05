package com.example.root.lecturealarm;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by root on 04/08/18.
 */

public class PreferenceManager {

    Context context;
    SharedPreferences mSharedPreference;
    SharedPreferences.Editor mEditor;
    int PRIVATE_MODE = 0;
    private static final String PrefName = "intro_welcome";
    private static final String IsFirstTimeLaunch = "IFTL";



    public PreferenceManager(Context context){

        this.context = context;
        mSharedPreference = context.getSharedPreferences(PrefName,PRIVATE_MODE);
        mEditor = mSharedPreference.edit();


    }

    public void setFirstTimeLaunch(boolean isFirstTime){

        mEditor.putBoolean(IsFirstTimeLaunch , isFirstTime);
        mEditor.commit();


    }


    public boolean IsirstLaunch(){

        return mSharedPreference.getBoolean(IsFirstTimeLaunch , true);
    }


}
