package com.example.zikacrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{
    private List<ListService> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ServicesAdapter(List<ListService> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){ return mData.size(); }

    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.activity_ventas, null);
        return new ServicesAdapter.ViewHolder(view);
    }

    @Override
    public  void onBindViewHolder(final ServicesAdapter.ViewHolder holder, final int position){
        holder.binData(mData.get(position));
    }

    public void setItems(List<ListService> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView3);
        }

        void binData(final ListService item){
            name.setText(item.getName());
        }
    }
}
