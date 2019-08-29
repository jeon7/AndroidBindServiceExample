package ch.teko.bindserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {

    private static final String LOG_TAG = "MyService";
    IBinder myBinder = new MyBinder();

    public MyService() {
        Log.d(LOG_TAG, "MyService() created");
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "onCreate() called");
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind() called");
        return myBinder;
    }


    int getRandom() {
        return new Random().nextInt();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand() called"); // never called
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy() called");
        super.onDestroy();
    }

//
//     inner Class
//
    class MyBinder extends Binder {
        private static final String LOG_TAG = "MyBinder in MyService";

        public MyBinder() {
            Log.d(LOG_TAG, "MyBinder() created");
        }

        MyService getService() {
            Log.d(LOG_TAG, "getService() called");
            return MyService.this;
        }
    }
}
