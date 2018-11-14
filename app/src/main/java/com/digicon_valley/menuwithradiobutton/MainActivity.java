package com.digicon_valley.menuwithradiobutton;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    BluetoothAdapter mBluetoothAdapter;
    ConnectivityManager connectivityManager;

    int item_selection=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        wifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public void NetwokSelection(View view) {

        registerForContextMenu(view);
        openContextMenu(view);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.network_menu,menu);
        MenuItem item_wifi=menu.findItem(R.id.id_wifi);
        MenuItem item_bluetooth=menu.findItem(R.id.id_bluetooth);
        MenuItem item_mobile_data=menu.findItem(R.id.id_mobile_data);
        MenuItem item_none=menu.findItem(R.id.id_none);
        if (item_selection==1){
            item_none.setChecked(true);
        }
        else if (item_selection==2){

            item_wifi.setChecked(true);
        }
        else if (item_selection==3){

            item_bluetooth.setChecked(true);
        }
        else if (item_selection==4){
            item_mobile_data.setChecked(true);
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.id_none:
                wifiManager.setWifiEnabled(false);
                mBluetoothAdapter.disable();
                //setMobileDataEnabled(getApplicationContext(),false);
                Toast.makeText(getApplicationContext(),"None",Toast.LENGTH_SHORT).show();
                item_selection=1;
                return true;

            case R.id.id_wifi:
                wifiManager.setWifiEnabled(true);
                Toast.makeText(getApplicationContext(),"Wifi Selected",Toast.LENGTH_SHORT).show();
                item_selection=2;
                return true;
            case R.id.id_bluetooth:

                mBluetoothAdapter.enable();
                Toast.makeText(getApplicationContext(),"Bluetooth Selected",Toast.LENGTH_SHORT).show();
                item_selection=3;
                return true;
            case R.id.id_mobile_data:
                item_selection=4;

                //setMobileDataEnabled(getApplicationContext(),true);
                Toast.makeText(getApplicationContext(),"Mobile Data Network Selected",Toast.LENGTH_SHORT).show();
                return true;
        }


        return super.onContextItemSelected(item);
    }

   /* //the method below enables/disables mobile data depending on the Boolean 'enabled' parameter.
    private void setMobileDataEnabled(Context context, boolean enabled) {
        final ConnectivityManager conman = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Class conmanClass = null;
        try {
            conmanClass = Class.forName(conman.getClass().getName());
            final Field iConnectivityManagerField = conmanClass.getDeclaredField("mService");
            iConnectivityManagerField.setAccessible(true);
            final Object iConnectivityManager = iConnectivityManagerField.get(conman);
            final Class iConnectivityManagerClass = Class.forName(iConnectivityManager.getClass().getName());
            final Method setMobileDataEnabledMethod = iConnectivityManagerClass.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
            setMobileDataEnabledMethod.setAccessible(true);
            setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    // below method returns true if mobile data is on and vice versa
    private boolean mobileDataEnabled(Context context){
        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean)method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API
            // TODO do whatever error handling you want here
        }
        return mobileDataEnabled;
    }*/
}
