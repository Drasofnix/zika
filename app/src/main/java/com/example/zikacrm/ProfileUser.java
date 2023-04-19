package com.example.zikacrm;

import android.annotation.SuppressLint;
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

public class ProfileUser extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    TextView user_phone, user_address, email_user, name_user;
    Button btn_update, btn_logOut, ventasList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_user);

        name_user = findViewById(R.id.txt_name_user);
        email_user = findViewById(R.id.txt_email_user);
        user_address = findViewById(R.id.txt_user_addres);
        user_phone = findViewById(R.id.txt_phone_user);
        btn_logOut = findViewById(R.id.btn_logOut);
        btn_update = findViewById(R.id.btn_update);
        ventasList = findViewById(R.id.btn_ventas_list);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUser.this, UpdateUser.class));
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(ProfileUser.this, Login.class));
            }
        });

        ventasList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUser.this, Ventas.class));
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
                    String address = documentSnapshot.getString("address");

                    name_user.setText(name);
                    email_user.setText(email);
                    user_phone.setText(phone);
                    user_address.setText(address);
                }
            }
        });
    }


}

