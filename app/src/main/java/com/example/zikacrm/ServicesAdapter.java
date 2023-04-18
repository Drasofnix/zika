package com.example.zikacrm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ServicesAdapter extends FirestoreRecyclerAdapter<ServicesModel,ServicesAdapter.ViewHolder> {

    public ServicesAdapter(@NonNull FirestoreRecyclerOptions<ServicesModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, int position, @NonNull ServicesModel model) {
        holder.title.setText(model.getTitle());
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_element_service, parent, false);
        return new ViewHolder(v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Name);
        }
    }
}
