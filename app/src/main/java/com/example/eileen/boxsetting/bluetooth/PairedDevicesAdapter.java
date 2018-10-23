package com.example.eileen.boxsetting.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eileen.boxsetting.BluetoothDisconnectDialog;
import com.example.eileen.boxsetting.R;
import com.example.eileen.boxsetting.ResolutionDialogActivity;
import com.example.eileen.boxsetting.Utils.ActivityId;

import java.util.List;
import java.util.Map;

public class PairedDevicesAdapter extends RecyclerView.Adapter<PairedDevicesAdapter.ViewHolder> {

    private List<Map<String, BluetoothDevice>> mBoundDevicesList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvDeviceName;

        public ViewHolder(View view){
            super(view);
            tvDeviceName = (TextView) view.findViewById(
                    R.id.bluetooth_tv_paired_item_name);

        }
    }

    public PairedDevicesAdapter(List<Map<String, BluetoothDevice>> bluetoothDevicesList){
        this.mBoundDevicesList = bluetoothDevicesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bluetooth_paired_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                BluetoothDevice tmpDevice = mBoundDevicesList.get(position)
                        .get(Constent.ListDeviceInfo);
                Activity activity = (Activity) view.getContext();
                String deviceInfo;
                Intent intent = new Intent(activity, BluetoothDisconnectDialog.class);
                Constent.LOGI(intent + "");
                if (tmpDevice.getName() == null || tmpDevice.getName().equals("")){
                    deviceInfo = tmpDevice.getAddress();
                }else {
                    deviceInfo = tmpDevice.getName();
                }
                Constent.LOGI(deviceInfo);
                Constent.LOGI(position + "");
                intent.putExtra("device_info", deviceInfo);
                intent.putExtra("device_position", position);
                activity.startActivityForResult(intent, ActivityId.BLUETOOTH_ACTIVITY);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        BluetoothDevice device = mBoundDevicesList.get(position).get(Constent.ListDeviceInfo);
        if (device.getName() != null && !device.getName().equals("")){
            holder.tvDeviceName.setText(device.getName());
        }else {
            holder.tvDeviceName.setText(device.getAddress());
        }
    }

    @Override
    public int getItemCount(){
        return mBoundDevicesList.size();
    }

}
