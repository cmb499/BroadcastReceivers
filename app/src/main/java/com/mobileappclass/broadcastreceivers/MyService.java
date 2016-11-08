package com.mobileappclass.broadcastreceivers;

/**
 * Created by Careena on 11/8/16.
 */
import android.*;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Careena on 11/5/16.
 */
public class MyService extends Service {

    String datetime;
    double xCord;
    double yCord;

    //initial set up for service
    @Override
    public void onCreate() {
        System.out.println("399 Service created.....");
        super.onCreate();
    }


    //for start service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        System.out.println("399 Service started .....");
        // create the thread and pass Service id
        Thread thread = new Thread(new MyThreadClass(startId));
        thread.start();
        return START_STICKY;    //constant value of service class
    }


    //will be called before service is destroyed
    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Destroyed .....", Toast.LENGTH_SHORT).show();
        System.out.println("399 Service Destroyed .....");
    }

    //since this is start service, return null (used for bind service)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    final class MyThreadClass implements Runnable {

        int SERVICE_ID;

        public MyThreadClass(int serviceID) {
            this.SERVICE_ID = serviceID;
        }

        @Override
        public void run() {

            int i = 0;

            synchronized (this) {
                while (i < 1000) {
                    try {
                            System.out.println(" 999 DB  Inserted row "  + i);
                        Intent intent = new Intent();
                        intent.putExtra(MainActivity.MY_MSG, i);
                        intent.setAction(MainActivity.MY_FILTER);
                        sendBroadcast(intent);
                        wait(10000);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf(SERVICE_ID); //stop service after 15s
            }
        }

    }




    //location <code></code>


}