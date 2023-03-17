package com.example.zikacrm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateUser extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    EditText update_email, update_address, update_phone, update_name, update_Pass;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_user_layout);

        update_name = findViewById(R.id.update_name);
        update_email = findViewById(R.id.update_email);
        update_address = findViewById(R.id.update_user_address);
        update_phone = findViewById(R.id.update_user_phone);
        update_Pass = findViewById(R.id.txt_updatePass);
        update = findViewById(R.id.btn_update_user);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEmail = update_email.getText().toString();
                String newAddress = update_address.getText().toString();
                String newPhone = update_phone.getText().toString();
                String newName = update_name.getText().toString();
                String newPass = update_Pass.getText().toString();

                updateUser(newEmail, newAddress, newPhone, newName, newPass);
            }
        });

    }

    private void updateUser(String newEmail, String newAddress, String newPhone, String newName, String newPass) {
        if (newEmail.isEmpty() && newAddress.isEmpty() && newPhone.isEmpty() && newAddress.isEmpty() && newPass.isEmpty()) {
            Toast.makeText(UpdateUser.this, "Todos los datos son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            FirebaseUser user = mAuth.getCurrentUser();

            user.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    String id = mAuth.getCurrentUser().getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("_id", id);
                    map.put("email", newEmail);
                    map.put("name", newName);
                    map.put("phone", newPhone);
                    map.put("address", newAddress);
                    map.put("password", newPass);

                    db.collection("users").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            finish();
                            Toast.makeText(UpdateUser.this, "Usuario Actualizado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateUser.this, ProfileUser.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateUser.this, "Error al actualizar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateUser.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
