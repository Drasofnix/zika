package com.example.zikacrm;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Services extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ServicesAdapter servicesAdapter;
    ArrayList<ListService> serviceArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();
        serviceArrayList =new ArrayList<ListService>();
        servicesAdapter = new ServicesAdapter(Services.this,serviceArrayList);

        recyclerView.setAdapter(servicesAdapter);
        EventChangeListener();
    }

    private void EventChangeListener() {
        Toast.makeText(Services.this, "Iniciado Correctamente", Toast.LENGTH_SHORT).show();
        db.collection("services").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error);
                    return;
                }
                Toast.makeText(Services.this, "hola", Toast.LENGTH_SHORT).show();
                for (DocumentChange dc : value.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            serviceArrayList.add((ListService) dc.getDocument().getData());
                            Toast.makeText(Services.this, dc.getDocument().getData().toString(), Toast.LENGTH_SHORT).show();
                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                    }
                }
                /*for (DocumentSnapshot dc : value.getDocuments()) {
                    serviceArrayList.add(dc.getData());
                    Toast.makeText(Services.this, serviceArrayList.toString(), Toast.LENGTH_SHORT).show();
                    servicesAdapter.notifyDataSetChanged();
                }*/
            }
        });
    }


    /*public void init() {
        elements = new ArrayList<>();
        db.collection("services")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                elements.add(document);
                                ServicesAdapter listService = new ServicesAdapter(elements, this);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }*/
}