package com.proyecto.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class escogerperfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escogerperfil);
    }

    public void iniciarsesionempleado(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar sesion como empleado", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, iniciarsesionempleado.class);
        startActivity(i);
    }
    public void iniciarsesionadministrador(View view) {
        Toast.makeText(getApplicationContext(), "Iniciar sesion como administrador", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, iniciarsesionadministrador.class);
        startActivity(i);
    }

}