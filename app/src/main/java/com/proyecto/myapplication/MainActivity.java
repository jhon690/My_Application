package com.proyecto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void iniciarsesion(View view) {
        Intent i = new Intent(this, iniciarsesionactivity.class);
        startActivity(i);
    }

    public void registrarusuario(View view) {
        Intent i = new Intent(this, registrarmeactivity.class);
        startActivity(i);
    }

}