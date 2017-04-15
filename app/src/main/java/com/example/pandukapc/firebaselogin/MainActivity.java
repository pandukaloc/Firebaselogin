package com.example.pandukapc.firebaselogin;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextpassword;
    private EditText editTextEmailAddress;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editTextEmailAddress = (EditText)findViewById(R.id.editTextEmaiAdrress);
        editTextpassword = (EditText)findViewById(R.id.editTextpassword);
        textViewSignin = (TextView)findViewById(R.id.textviewSignin);
    }

    public void registerUser(){
        String email = editTextEmailAddress.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "please Enter the EMAil Address", Toast.LENGTH_SHORT).show();
            return;}

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Plaease enter password", Toast.LENGTH_SHORT).show();
            return;}

        progressDialog.setMessage("Registring User......");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Registred Successfully",Toast.LENGTH_SHORT).show();}
                else {
                    Toast.makeText(MainActivity.this, "try again",Toast.LENGTH_SHORT).show();}

            }
        });
    }

    @Override
    public void onClick(View view){
        if (view == buttonRegister){
            registerUser();
            Toast.makeText(MainActivity.this, "try again",Toast.LENGTH_SHORT).show();
        }
        if(view == textViewSignin){
            //to open loginin activity

        }

    }

}
