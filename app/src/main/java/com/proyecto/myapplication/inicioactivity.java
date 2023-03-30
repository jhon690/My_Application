package com.proyecto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class inicioactivity extends AppCompatActivity {

    private final List<Productos> listpro = new ArrayList<Productos>();
    ArrayAdapter<Productos> arrayAdapterProductos;
    SearchView busqueda;
    ImageButton imagenperfil, imagennotificacion;
    EditText nombre, marca, ID, lote, date;
    TextView textView;
    ListView productos;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Productos productoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicioactivity);
        //SearchView
        busqueda = findViewById(R.id.busqueda);
        //ImageButton
        imagenperfil = findViewById(R.id.imagenperfil);
        imagennotificacion = findViewById(R.id.imagennotificacion);
        //EditText
        nombre = findViewById(R.id.nombre);
        marca = findViewById(R.id.marca);
        ID = findViewById(R.id.ID);
        lote = findViewById(R.id.lote);
        date = findViewById(R.id.date);
        //TextView
        textView = findViewById(R.id.textView);
        //ListView
        productos = findViewById(R.id.productos);

        inicializarFirebase();
        listarDatos();

        productos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                productoSelected = (Productos) parent.getItemAtPosition(position);
                nombre.setText(productoSelected.getNombre());
                marca.setText(productoSelected.getMarca());
                ID.setText(productoSelected.getId());
                lote.setText(productoSelected.getLote());
                date.setText(productoSelected.getFecha());
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("Productos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listpro.clear();
                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Productos p = objSnapshot.getValue(Productos.class);
                    listpro.add(p);

                    arrayAdapterProductos = new ArrayAdapter<Productos>(inicioactivity.this, android.R.layout.simple_list_item_1, listpro);
                    productos.setAdapter(arrayAdapterProductos);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);

        firebaseDatabase = FirebaseDatabase.getInstance();
        // firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ic, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String nombrep = nombre.getText().toString();
        String marcap = marca.getText().toString();
        String IDp = ID.getText().toString();
        String lotep = lote.getText().toString();
        String datep = date.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add: {
                if (nombrep.equals("") | marcap.equals("") | IDp.equals("") | lotep.equals("") | (datep.equals(""))) {
                    validacion();
                } else {

                    Productos p = new Productos();
                    p.setId(UUID.randomUUID().toString());
                    p.setNombre(nombrep);
                    p.setFecha(datep);
                    p.setMarca(marcap);
                    p.setLote(lotep);
                    databaseReference.child("Productos").child(p.getId()).setValue(p);
                    Toast.makeText(this, "Se agrego el producto", Toast.LENGTH_SHORT).show();
                    Limpiarcajas();

                }
                break;
            }

            case R.id.icon_save: {

                Productos p = new Productos();
                p.setId(productoSelected.getId());
                p.setNombre(nombre.getText().toString().trim());
                p.setFecha(date.getText().toString().trim());
                p.setMarca(marca.getText().toString().trim());
                p.setLote(lote.getText().toString().trim());

                databaseReference.child("Productos").child(p.getId()).setValue(p);

                Toast.makeText(this, "Se guardaron los cambios", Toast.LENGTH_SHORT).show();
                Limpiarcajas();
                break;
            }
            case R.id.icon_delete: {

                Productos p = new Productos();
                p.setId((productoSelected).getId());

                databaseReference.child("Productos").child(p.getId()).removeValue();

                Toast.makeText(this, "Eliminar", Toast.LENGTH_SHORT).show();
                Limpiarcajas();
                break;
            }
            default:
                break;
        }
        return true;
    }

    private void Limpiarcajas() {
        nombre.setText("");
        marca.setText("");
        ID.setText("");
        lote.setText("");
        date.setText("");
    }

    private void validacion() {
        String nombrep = nombre.getText().toString();
        String marcap = marca.getText().toString();
        String IDp = ID.getText().toString();
        String lotep = lote.getText().toString();
        String datep = date.getText().toString();

        if (nombrep.equals("")) {
            nombre.setError("Required");
        }
        if (marcap.equals("")) {
            marca.setError("Required");
        }
        if (IDp.equals("")) {
            ID.setError("Required");
        }
        if (lotep.equals("")) {
            lote.setError("Required");
        }
        if (datep.equals("")) {
            date.setError("Required");
        }
    }

    public void escogerperfil(View view) {
        Intent i = new Intent(this, escogerperfil.class);
        startActivity(i);
    }

    public void notificacion(View view) {
        Intent i = new Intent(this, notificacion.class);
        startActivity(i);
    }

}