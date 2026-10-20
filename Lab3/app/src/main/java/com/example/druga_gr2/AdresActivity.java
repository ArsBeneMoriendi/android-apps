package com.example.druga_gr2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdresActivity extends AppCompatActivity {
    public static final String EXTRA_ADRES = "ADRES";

    private EditText edtMiasto;
    private EditText edtUlica;
    private EditText edtNumerDomu;
    private EditText edtKodPocztowy;
    private Button btnWroc;
    private Button btnZapiszAdres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adres);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicjuj();
        przyciski();
    }

    private void inicjuj() {
        edtMiasto = findViewById(R.id.edtMiasto);
        edtUlica = findViewById(R.id.edtUlica);
        edtNumerDomu = findViewById(R.id.edtNumerDomu);
        edtKodPocztowy = findViewById(R.id.edtKodPocztowy);
        btnWroc = findViewById(R.id.btnWrocAdres);
        btnZapiszAdres = findViewById(R.id.btnZapiszAdres);
    }

    private void przyciski() {
        btnWroc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnZapiszAdres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adres adres = new Adres(
                        edtMiasto.getText().toString(),
                        edtUlica.getText().toString(),
                        edtNumerDomu.getText().toString(),
                        edtKodPocztowy.getText().toString()
                );
                Intent wynik = new Intent();
                wynik.putExtra(EXTRA_ADRES, adres);
                setResult(RESULT_OK, wynik);
                finish();
            }
        });
    }
}
