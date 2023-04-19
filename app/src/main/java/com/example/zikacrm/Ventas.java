package com.example.zikacrm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zikacrm.Adapters.ServicesAdapter;
import com.example.zikacrm.Adapters.VentasAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Ventas extends AppCompatActivity {

    FirebaseFirestore db;
    ListView listView;
    VentasAdapter ventasAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view_ventas);

        listView = (ListView) findViewById(R.id.listViewVentas);
        db = FirebaseFirestore.getInstance();

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("services").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().size() != 0){
                        ventasAdapter = new VentasAdapter(Ventas.this, task.getResult());
                        listView.setAdapter(ventasAdapter);
                    }
                } else {

                }
            }
        });
    }
}