package com.proyecto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class perfiladministrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiladministrador);
    }

    public void cambiarcontrasena(View view) {
        Intent i = new Intent(this, correorecuperarcontrasena.class);
        startActivity(i);
    }

    public void agregarproducto(View view) {
        Intent i = new Intent(this, inicioactivity.class);
        startActivity(i);
    }

    public void registrarempleado(View view) {
        Intent i = new Intent(this, registrarmeactivity.class);
        startActivity(i);
    }

    public void cerrarsesion(View view) {
        Toast.makeText(getApplicationContext(), "Sesion cerrada con exito.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}