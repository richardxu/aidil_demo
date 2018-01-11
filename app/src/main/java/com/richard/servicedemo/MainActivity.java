package com.richard.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import static android.content.Context.BIND_AUTO_CREATE;
import static com.richard.servicedemo.R.id.bind;
import static com.richard.servicedemo.R.id.text;
//import com.richard.servicedemo.MyService.MyBinder;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
//    private iService binder = null;
    private iService binder = null;

    MyService myService = null;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "============ onServiceConnected =======");

            // 获取中间人对象
            binder = (iService) iService.Stub.asInterface(iBinder);
            try {
                binder.sayHello();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            try {
                String color =  binder.getColor();
                double weight = binder.getWeight(200);
                Log.d("Richard", " ========= Message: " + color + " and weight is " + weight);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


            Person person = new Person();
            person.setName("richard");
            person.setAge(20);
            person.setTelNumber("11111111");

            try {
                binder.savePersonInfo(person);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            List<Person> list = null;
            try {
                list = binder.getAllPerson();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if(list != null){
                StringBuilder stringBuilder = new StringBuilder();
                for(Person person1 : list){
                    stringBuilder.append(person.getName());
                    stringBuilder.append(person.getAge());
                    stringBuilder.append(person.getTelNumber());
                }

                Log.d(TAG, " ====== Person:  " + stringBuilder);
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "============ onServiceDisconnected =======");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = (Button) findViewById(R.id.start);
        final Intent intent = new Intent(this, com.richard.servicedemo.MyService.class);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
            }
        });
        Button btn_stop = (Button) findViewById(R.id.stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
            }
        });

        Button btn_bind = (Button) findViewById(bind);
        btn_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setPackage("com.richard.servicedemo");
                intent1.setAction("android.intent.action.richard.service");
                bindService(intent1, serviceConnection, BIND_AUTO_CREATE);
            }
        });

        Button btn_unbind = (Button) findViewById(R.id.unbind);
        btn_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(binder!= null) {
//                    unbindService(serviceConnection);
//                    binder = null;
//                }
            }
        });


    }
}
