package com.example.mykisanlabh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {


    private static final String TAG = "LoginAct";
    EditText email,pass;
    Button reg;

    private FirebaseAuth mAuth;

    private void init(){
        email=findViewById(R.id.email);
        pass= findViewById(R.id.pass);
        reg=findViewById(R.id.signup);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e,p;
                e = email.getText().toString();
                p = pass.getText().toString();
                if (TextUtils.isEmpty(e)){
                    email.setError("Please enter email");
                    return;
                }
                if (TextUtils.isEmpty(p)||p.length()<8){
                    pass.setError("Please enter valid password");
                    return;
                }
                registerUser(e,p);
            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
    }
    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }

                });
    }

    private void updateUI(FirebaseUser user) {
        if (user==null){
            Toast.makeText(this,"Please Signup",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent=new Intent(SignUpActivity.this,SellerActivity.class);
        startActivity(intent);
        finish();

    }
}
