package andro.jf;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Toast;

public class Main extends Activity {
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Réseau
    ConnectivityManager manager = 
       (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
    NetworkInfo net = manager.getActiveNetworkInfo();
    Toast.makeText(getApplicationContext(), "" + net.getType(), 
          Toast.LENGTH_LONG).show();

    // Wifi
    WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
    if (!wifi.isWifiEnabled())
      wifi.setWifiEnabled(true);
    manager.setNetworkPreference(ConnectivityManager.TYPE_WIFI);

    // Bluetooth
    BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    if (!bluetooth.isEnabled()){
      Intent launchBluetooth = 
        new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
      startActivity(launchBluetooth);
    }

    Intent discoveryMode =
      new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    discoveryMode.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 60);
    startActivity(discoveryMode);

    Set<BluetoothDevice> s = bluetooth.getBondedDevices();
    for (BluetoothDevice  ap : s)
      Toast.makeText(getApplicationContext(), "" + ap.getName(),
            Toast.LENGTH_LONG).show();

  }
}
