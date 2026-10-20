package com.example.druga_gr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DodajOsobaActivity extends AppCompatActivity {
    private Button btnWroc;
    private Button btnDodajOsobe;
    private Button btnDodajAdres;
    private EditText edtImie;
    private EditText edtNazwisko;
    private ListaOsob listaOsob;
    private TextView txtAdres;
    private Adres adres;
    private ActivityResultLauncher<Intent> adresLauncher;

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
        if(savedInstanceState != null){
            adres = savedInstanceState.getParcelable(AdresActivity.EXTRA_ADRES, Adres.class);
            wyswietlAdres();
        }
        przyciski();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(AdresActivity.EXTRA_ADRES, adres);
    }

    private void inicjuj(){
        btnDodajOsobe = findViewById(R.id.btnDodajOsobe);
        btnWroc = findViewById(R.id.btnWroc);
        edtImie = findViewById(R.id.edtImie);
        edtNazwisko = findViewById(R.id.edtNazwisko);
        listaOsob = ListaOsob.get();
        btnDodajAdres = findViewById(R.id.btnDodajAdres);
        txtAdres = findViewById(R.id.txtAdres);

        adresLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Adres nowyAdres = IntentCompat.getParcelableExtra(
                                result.getData(), AdresActivity.EXTRA_ADRES, Adres.class);
                        if(nowyAdres != null){
                            adres = nowyAdres;
                            wyswietlAdres();
                        }
                    }
                }
        );
    }

    private void wyswietlAdres(){
        if(adres != null){
            txtAdres.setText(getString(R.string.podsumowanie_adresu,
                    adres.getUlica(), adres.getNumerDomu(),
                    adres.getKodPocztowy(), adres.getMiasto()));
        } else {
            txtAdres.setText(R.string.brak_adresu);
        }
    }
    private void przyciski(){
        btnDodajAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajOsobaActivity.this, AdresActivity.class);
                adresLauncher.launch(intent);
            }
        });

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
                osoba.setAdres(adres);
                listaOsob.dodajOsobe(osoba);
            }
        });
    }
}
