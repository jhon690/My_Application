package com.proyecto.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class correorecuperarcontrasena extends AppCompatActivity {

    private EditText correorecuperacontrasena;
    private Button recuperar;
    private ProgressDialog progress;
    private String correo;

    FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correorecuperarcontrasena);
        //Titulo centrado de la app Action Bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        /*  getSupportActionBar().setCustomView(Rlayoutactivity_correorecuperarcontrasena); */

        correorecuperacontrasena = findViewById(R.id.correorecuperacontrasena);
        recuperar = findViewById(R.id.recuperar);

        auth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        getRecuperar();
    }

    private void getRecuperar() {
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = correorecuperacontrasena.getText().toString().trim();

                if(!correo.isEmpty()){
                    progress.setMessage("Espera un momento..");
                    progress.setCanceledOnTouchOutside(false);
                    progress.show();
                    getEnviarCorre();
                }else{
                    Toast.makeText(getApplicationContext(), "El correo no se pudo enviar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void getEnviarCorre(){
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Por favor revise su correo para restaurar contraseña", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),  MainActivity.class);
                    startActivity(i);
                    finish();
                }else
                {
                    Toast.makeText(getApplicationContext(), "El correo no se pudo Envia", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}