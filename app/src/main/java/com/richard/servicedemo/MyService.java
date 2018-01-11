package com.richard.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"============ onStartCommand ============= ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"============ onUnbind ============= ");
        return super.onUnbind(intent);
    }


//    @Override
//    public void onDestroy() {
//        Log.d(TAG,"============ onDestroy ============= ");
//        super.onDestroy();
//    }

    @Override
    public void onCreate() {
        Log.d(TAG,"============ onCreate ============= ");
        super.onCreate();
    }

//    public void sayHello()
//    {
//        Log.d(TAG, " ============ Hello, My Service here ======= ");
//        Toast.makeText(getApplicationContext()," ============ Hello, My Service here ======= ",Toast.LENGTH_LONG).show();
//    }

    private class MyBinder extends iService.Stub
    {
        @Override
        public String getColor() throws RemoteException {
            return "get from remote service";
        }

        @Override
        public void sayHello()
        {
            Log.d(TAG, " ============ Hello, My Service here ======= ");
        }

        @Override
        public double getWeight(int value) throws RemoteException {
            if(value > 100) {
                return 999.999;
            } else
            {
                return 0;
            }
        }

//        @Override
//        public IBinder asBinder()
//        {
//            return (IBinder) Stub.asInterface();
//        }
//
//        @Override
//        public void sayHello() {
//            Log.d(TAG, " ============ Hello, My Service here ======= ");
//            Toast.makeText(getApplicationContext()," ============ Hello, My Service here ======= ",Toast.LENGTH_LONG).show();
//        }
    }




    @Override
    public MyBinder onBind(Intent intent) {

        Log.d(TAG,"============ onBind ============= ");
        MyBinder myBinder = (MyBinder) new MyBinder();
        return myBinder;
    }
}
