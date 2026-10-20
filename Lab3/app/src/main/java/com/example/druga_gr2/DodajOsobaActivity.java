package com.example.druga_gr2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DodajOsobaActivity extends AppCompatActivity {
    private Button btnWroc;
    private Button btnDodajOsobe;
    private EditText edtImie;
    private EditText edtNazwisko;
    private ListaOsob listaOsob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dodaj_osoba);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicjuj();
        przyciski();
    }
    private void inicjuj(){
        btnDodajOsobe = findViewById(R.id.btnDodajOsobe);
        btnWroc = findViewById(R.id.btnWroc);
        edtImie = findViewById(R.id.edtImie);
        edtNazwisko = findViewById(R.id.edtNazwisko);
        listaOsob = ListaOsob.get();

    }
    private void przyciski(){
        btnWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDodajOsobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Osoba osoba = new Osoba();
                osoba.setImie(edtImie.getText().toString());
                osoba.setNazwisko(edtNazwisko.getText().toString());
                listaOsob.dodajOsobe(osoba);
            }
        });
    }
}