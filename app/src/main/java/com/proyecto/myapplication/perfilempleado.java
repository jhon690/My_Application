package com.proyecto.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class perfilempleado extends AppCompatActivity {

    private TextView cerrarsesion;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfilempleado);

        /*mAuth = FirebaseAuth.getInstance();
        cerrarsesion = (TextView) findViewById(R.id.cerrarsesion);

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                starActivity(new Intent(iniciarsesionactivity.this, iniciarsesionactivity.class));
            mAuth.signOut();
                Toast.makeText(getApplicationContext(), "Sesion cerrada con exito.", Toast.LENGTH_SHORT).show();
            starActivity(new Intent(.this, iniciarsesionactivity.class));
            finish();
            }
        });*/

    }

    /*private void starActivity(Intent intent) {
    }*/

    public void cerrarsesion(View view){
        Toast.makeText(getApplicationContext(), "Sesion cerrada con exito.", Toast.LENGTH_SHORT).show();
        Intent i= new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void agregarproducto(View view){
        Intent i= new Intent(this, inicioactivity.class);
        startActivity(i);
    }

    public void cambiarcontrasena(View view){
        Intent i= new Intent(this, correorecuperarcontrasena.class);
        startActivity(i);
    }

}