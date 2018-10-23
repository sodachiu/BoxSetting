package com.example.eileen.boxsetting.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eileen.boxsetting.R;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bluetooth_paired_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
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
