package com.doski.moski.tictactoe;

import android.app.Application;

/**
 * Created by SamerGigaByte on 4/8/2017.
 */

public class App extends Application {

    static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static Application getApplication() {
        return mApplication;
    }
}
