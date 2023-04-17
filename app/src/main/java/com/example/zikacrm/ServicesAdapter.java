package com.example.zikacrm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{

    Context context;
    ArrayList<ListService> mData;
    public ServicesAdapter(Context context, ArrayList<ListService> mData) {

        this.context = context;
        this.mData = mData;
    }

    @Override
    public int getItemCount(){ return mData.size(); }

    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.activity_main,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public  void onBindViewHolder(final ServicesAdapter.ViewHolder holder, int position){
        ListService service = mData.get(position);

        holder.title.setText(service.getTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Name);
        }

        void binData(final ListService item){
            title.setText(item.getTitle());
        }
    }
}
