package com.example.druga_gr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView lvListaOsob;
    private Button btnDodaj;
    private Button btnFiltruj;
    private ArrayAdapter<Osoba> adapterOsob;
    private Osoba osoba;
    private ListaOsob listaOsob;
    private ParametryFiltrowania parametryFiltrowania;
    private ActivityResultLauncher<Intent> filtrLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inicjuj();
        przyciski();
    }
    @Override
    protected void onResume(){
        super.onResume();
        odswiezListe();
    }
    private void odswiezListe(){
        ArrayList<Osoba> wynik = new ArrayList<>();
        String tekst = parametryFiltrowania.getTekst().trim().toLowerCase(Locale.ROOT);

        //filtrowanie
        for(Osoba osoba:listaOsob.pobierzOsoby()){
            String nazwa = (osoba.getImie()+" "+osoba.getNazwisko()).toLowerCase(Locale.ROOT);

            if(nazwa.contains(tekst)){
                wynik.add(osoba);
            }
        }

        //Sortowanie według nazwiska
        Comparator<Osoba> comparator = Comparator.comparing(Osoba::getNazwisko, String.CASE_INSENSITIVE_ORDER);
        if(!parametryFiltrowania.isSortowanieRosnaco()){
            comparator = comparator.reversed();
        }
        wynik.sort(comparator);

        adapterOsob.setNotifyOnChange(false);
        adapterOsob.clear();
        adapterOsob.addAll(wynik);
        adapterOsob.notifyDataSetChanged();
    }
    private void inicjuj(){
        lvListaOsob = findViewById(R.id.listViewOsoby);
        btnDodaj = findViewById(R.id.btnDodaj);
        btnFiltruj = findViewById(R.id.btnFiltruj);
        adapterOsob = new ArrayAdapter<Osoba>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        lvListaOsob.setAdapter(adapterOsob);
        listaOsob = ListaOsob.get();
        parametryFiltrowania = new ParametryFiltrowania();

        filtrLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result->{
                    if(result.getResultCode() == RESULT_OK && result.getData() !=null){
                        Intent dane = result.getData();
                        ParametryFiltrowania noweParametry = IntentCompat.getParcelableExtra(dane, "PARAMETRY_FILTROWANIA", ParametryFiltrowania.class);

                        if(noweParametry != null){
                            parametryFiltrowania = noweParametry;
                            odswiezListe();
                        }
                    }
                }
        );
    }
    private void przyciski(){
        //Dodawanie nowej osoby
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intencja = new Intent(MainActivity.this, DodajOsobaActivity.class);
                startActivity(intencja);
            }
        });
        //filtrowanie listy osób
        btnFiltruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FiltrActivity.class);
                filtrLauncher.launch(intent);
            }
        });
    }
}