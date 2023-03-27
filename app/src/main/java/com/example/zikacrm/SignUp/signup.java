package com.example.zikacrm.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zikacrm.Login.Login;
import com.example.zikacrm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity{

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    EditText txt_name, txt_email, txt_pass, txt_conPass;
    Button btn_signup, btn_alreadyAccount;
    CheckBox isProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        txt_name = findViewById(R.id.rgstr_name);
        txt_email = findViewById(R.id.rgstr_email);
        txt_pass = findViewById(R.id.rgstr_pass);
        txt_conPass = findViewById(R.id.rgstr_conPass);
        btn_signup = findViewById(R.id.btn_signup);
        btn_alreadyAccount = findViewById(R.id.btn_account);
        isProvider = findViewById(R.id.checkBox);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String email = txt_email.getText().toString().trim();
                String password = txt_pass.getText().toString().trim();
                String name = txt_name.getText().toString().trim();
                String confirmPass = txt_conPass.getText().toString().trim();
                boolean provider = isProvider.isChecked();

                if (!password.equals(confirmPass) ) {
                    Toast.makeText(signup.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                }else {
                    SignUp(email, password, name, provider);
                }
            };
        });

        btn_alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, Login.class));
            }
        });
    }

    private void SignUp(String email, String password, String name, boolean provider) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String, Object> map = new HashMap<>();
                map.put("_id", id);
                map.put("email", email);
                map.put("name", name);
                map.put("password", password);
                map.put("provider", provider);

                db.collection("users").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        finish();
                        Toast.makeText(signup.this, "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, Login.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signup.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
