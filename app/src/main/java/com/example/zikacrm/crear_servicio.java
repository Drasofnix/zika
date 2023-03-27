package com.example.zikacrm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zikacrm.Login.Login;
import com.example.zikacrm.SignUp.signup;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class crear_servicio extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    EditText title, description, price;
    Button create_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicio);

        title = findViewById(R.id.service_title);
        description = findViewById(R.id.description_service);
        price = findViewById(R.id.service_price);
        create_service = findViewById(R.id.button_create);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        create_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sTitle = title.getText().toString();
                String sDescription = description.getText().toString();
                Float sPrice = price.getAlpha();

                newService(sTitle, sDescription, sPrice);
            }
        });
    }

    public void newService(String sTitle, String sDescription, Float sPrice){
        String id = mAuth.getCurrentUser().getUid();
        Map<String, Object> service = new HashMap<>();
        service.put("privider_id", id);
        service.put("title", sTitle);
        service.put("description", sDescription);
        service.put("price", sPrice);

        db.collection("services").document("LA")
                .set(service)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        finish();
                        Toast.makeText(crear_servicio.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(crear_servicio.this, ventas.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(crear_servicio.this, "Error al Registrar Servicio", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}