package com.example.druga_gr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FiltrActivity extends AppCompatActivity {

    private EditText edtSzukaj;
    private RadioGroup rgSortowanie;
    private RadioButton rbMalejaco;
    private RadioButton rbRosnaco;
    private Button btnWroc;
    private Button btnZastosuj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filtr);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Filtrowanie osób");
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicjuj();
        przyciski();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    private void inicjuj(){
        edtSzukaj = findViewById(R.id.edtSzukaj);
        rgSortowanie = findViewById(R.id.rgSortowanie);
        rbMalejaco = findViewById(R.id.rbMalejaco);
        rbRosnaco = findViewById(R.id.rbRosnaco);
        btnWroc = findViewById(R.id.btnWrocFiltr);
        btnZastosuj = findViewById(R.id.btnZastosuj);
    }
    private void przyciski(){
        btnWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnZastosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParametryFiltrowania parametry = new ParametryFiltrowania();

                parametry.setTekst(edtSzukaj.getText().toString().trim());
                parametry.setSortowanieRosnaco(rgSortowanie.getCheckedRadioButtonId() == R.id.rbRosnaco);

                Intent wynik = new Intent();
                wynik.putExtra("PARAMETRY_FILTROWANIA", parametry);
                setResult(RESULT_OK, wynik);
                finish();
            }
        });
    }
}