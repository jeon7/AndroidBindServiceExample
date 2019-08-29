package ch.teko.bindserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    IBinder myBinder = new MyBinder();

    public MyService() {
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
}
