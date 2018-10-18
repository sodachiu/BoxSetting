package com.example.eileen.boxsetting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import java.util.List;

public class ResolutionAdapter extends RecyclerView.Adapter<ResolutionAdapter.ViewHolder>{

    private List<Resolution> mResolutionList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        RadioButton radioButton;

        public ViewHolder(View view){
            super(view);
            radioButton = (RadioButton) view.findViewById(R.id.resolution_radio_button);
        }
    }

    public ResolutionAdapter(List<Resolution> resolutionList){
        this.mResolutionList = resolutionList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resolution_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Resolution resolution = mResolutionList.get(position);
        holder.radioButton.setText(resolution.getName());
        holder.radioButton.setId(resolution.getId());
    }

    @Override
    public int getItemCount(){
        return mResolutionList.size();
    }
}
