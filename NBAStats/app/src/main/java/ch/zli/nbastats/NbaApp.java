package ch.zli.nbastats;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class NbaApp extends Application {
    private static Context appContext;
    private static AppCompatActivity currentActivity;

    public static AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }
    public static Context getInstance() {
        return appContext;
    }

    public static FragmentManager getFragmentSupportManager() {
        return currentActivity.getSupportFragmentManager();
    }

    public static void setCurrentActivity(AppCompatActivity currentActivity) {
        NbaApp.currentActivity = currentActivity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }
}
