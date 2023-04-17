package com.example.zikacrm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ventas extends AppCompatActivity {

    Button newService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_view_ventas);

        newService = findViewById(R.id.button_to_ns);

        newService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ventas.this, Crear_servicio.class));
            }
        });
    }
}