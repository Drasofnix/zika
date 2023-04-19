package com.example.zikacrm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zikacrm.Login.Login;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProviderProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    TextView provider_name, provider_email, company, description;
    Button btn_LogOut, services_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileventa);

        provider_name = findViewById(R.id.textView14);
        provider_email = findViewById(R.id.textView15);
        company = findViewById(R.id.textView17);
        description = findViewById(R.id.textView18);
        btn_LogOut = findViewById(R.id.btn_logOut2);
        services_list = findViewById(R.id.btn_service_list);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btn_LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(ProviderProfile.this, Login.class));
            }
        });
        services_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProviderProfile.this, Services.class));
            }
        });
        getUser();
    }

    private void getUser() {
        String id = mAuth.getCurrentUser().getUid();
        db.collection("users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String name = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    String phone = documentSnapshot.getString("phone");
                    String v = documentSnapshot.getString("address");

                    provider_name.setText(name);
                    provider_email.setText(email);
                    description.setText(phone);
                    company.setText(v);
                }
            }
        });
    }
}

