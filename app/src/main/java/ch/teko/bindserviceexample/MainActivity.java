package ch.teko.bindserviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MainActivity";
    MyService myService;
    boolean isServiceConnected = false;

    // create ServiceConnection Object in MainActivity to connect to MyService
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, "onServiceConnected() called from ServiceConnection object");
            MyService.MyBinder myBinder = (MyService.MyBinder)service;
            myService = myBinder.getService();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "onServiceDisconnected() called from ServiceConnection object");
            isServiceConnected = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "button1(service start) clicked");
                Intent intent = new Intent(MainActivity.this, MyService.class);
                Log.d(LOG_TAG, "new Intent object created for Service");
                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                Log.d(LOG_TAG, "bindService() called");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "button2(service end) clicked");
                unbindService(connection);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "button3(get data from service) clicked");
                if(!isServiceConnected) {
                    Toast.makeText(getApplicationContext(), "not connected to MyService", Toast.LENGTH_LONG).show();
                    return;
                }
                int num = myService.getRandom();
                Toast.makeText(getApplicationContext(), "Data from MyService: " + num, Toast.LENGTH_LONG).show();
            }
        });
    }
}
