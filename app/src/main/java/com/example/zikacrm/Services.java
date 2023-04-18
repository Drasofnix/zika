package com.example.zikacrm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Services extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    ServicesAdapter servicesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        db = FirebaseFirestore.getInstance();
        Query query = db.collection("services");

        FirestoreRecyclerOptions<ServicesModel> firestormRecyclerOptions = new FirestoreRecyclerOptions.Builder<ServicesModel>().setQuery(query, ServicesModel.class).build();

        servicesAdapter = new ServicesAdapter(firestormRecyclerOptions);
        servicesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(servicesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        servicesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        servicesAdapter.stopListening();
    }
/*private void EventChangeListener() {

        final String[] title={(String) getTitle()};
        db.collection("services").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        title[0]=(String) document.get("title");
                        serviceArrayList.add(title);
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
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