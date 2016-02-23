package andro.jf;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      BluetoothDevice appareil = null;
	if (action.equals(BluetoothDevice.ACTION_FOUND))
        appareil = (BluetoothDevice)intent.getParcelableExtra(
          BluetoothDevice.EXTRA_DEVICE);
    }
}
