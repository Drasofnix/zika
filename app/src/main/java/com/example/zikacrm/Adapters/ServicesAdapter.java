package com.example.zikacrm.Adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.zikacrm.R;
import com.example.zikacrm.Services;
import com.google.firebase.firestore.QuerySnapshot;

public class ServicesAdapter implements ListAdapter {
    Services services;
    QuerySnapshot result;
    public ServicesAdapter(Services services, QuerySnapshot result) {
        this.services = services;
        this.result = result;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(services);
            convertView = layoutInflater.inflate(R.layout.list_element_service, null);
            TextView title = convertView.findViewById(R.id.title);
            TextView email = convertView.findViewById(R.id.serviceemail);
            title.setText(result.getDocuments().get(position).getString("title"));
            email.setText(result.getDocuments().get(position).getString("email_provider"));
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return result.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
