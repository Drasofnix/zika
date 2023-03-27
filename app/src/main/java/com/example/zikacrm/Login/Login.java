package com.example.zikacrm.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.zikacrm.ProfileUser;
import com.example.zikacrm.ProviderProfile;
import com.example.zikacrm.R;
import com.example.zikacrm.SignUp.signup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    EditText txt_email, txt_pass;
    Button btn_login, btn_haveAccount;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        
        txt_email = findViewById(R.id.txt_emailLogin);
        txt_pass = findViewById(R.id.txt_passLogin);
        btn_login = findViewById(R.id.btn_login);
        btn_haveAccount = findViewById(R.id.btn_haveAccount);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_email.getText().toString();
                String password = txt_pass.getText().toString();


                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Login.this, "Los campos no deben estar vacios", Toast.LENGTH_SHORT).show();
                }else {
                    login(email, password);
                }
            }
        });
        
        btn_haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, signup.class));
            }
        });
    }

    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    db.collection("users").document(mAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.get("provider").equals(true)) {
                                finish();
                                Toast.makeText(Login.this, "Sesion iniciada de " + email, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, ProviderProfile.class));
                            }else{
                                finish();
                                Toast.makeText(Login.this, "Sesion iniciada de " + email, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, ProfileUser.class));
                            }
                        }
                    });
                }else {
                    Toast.makeText(Login.this, "Error al iniciar", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(Login.this, ProfileUser.class));
            finish();
        }
    }*/
}
