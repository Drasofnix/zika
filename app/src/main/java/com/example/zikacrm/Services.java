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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Services extends AppCompatActivity {

    FirebaseFirestore db;
    ListView listView;
    ServicesAdapter servicesAdapter;

    Button newService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listServicios);

        db = FirebaseFirestore.getInstance();

        newService = findViewById(R.id.button_to_ns);

        newService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services.this, Crear_servicio.class));
            }
        });

        EventChangeListener();
    }
    private void EventChangeListener() {

        db.collection("services").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().size() != 0){
                        servicesAdapter = new ServicesAdapter(Services.this, task.getResult());
                        listView.setAdapter(servicesAdapter);
                    }
                } else {

                }
            }
        });
    }

               /* .addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w(TAG, "Listen failed.", error);
                    return;
                }
                Toast.makeText(Services.this, "hola", Toast.LENGTH_SHORT).show();
                for (DocumentSnapshot dc : value) {
                    switch (dc.getType()) {
                        case ADDED:
                            serviceArrayList.add((ListService) dc.getDocument().getData());
                            Toast.makeText(Services.this, value.getData(), Toast.LENGTH_SHORT).show();
                            break;
                        case MODIFIED:
                            break;
                        case REMOVED:
                            break;
                    }
                }
                for (DocumentSnapshot dc : value.getDocuments()) {
                    serviceArrayList.add(dc.getData());
                    Toast.makeText(Services.this, serviceArrayList.toString(), Toast.LENGTH_SHORT).show();
                    servicesAdapter.notifyDataSetChanged();
                }
            }
        });
    }


    public void init() {
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