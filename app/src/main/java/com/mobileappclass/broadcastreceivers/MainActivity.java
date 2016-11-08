package com.mobileappclass.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final String MY_FILTER = "broadcastReceiver";
    public static final String MY_MSG = "_message";
    IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Start this service now!!");
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        filter = new IntentFilter();
        filter.addAction(MY_FILTER);
        registerReceiver(myReceiver, filter);
    }


    //remember changes in Manifest
    

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                int iVal = bundle.getInt(MY_MSG);
                System.out.println("Broadcast i main " + iVal);

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        unregisterReceiver(myReceiver);
    }

}
