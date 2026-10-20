package com.example.druga_gr2;

import java.util.UUID;

public class Osoba {
    private String imie;
    private String nazwisko;
    private String mIdOsoba;

    public Osoba(){
        mIdOsoba = UUID.randomUUID().toString();
        imie = "brak";
        nazwisko = "brak";
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getmIdOsoba() {
        return mIdOsoba;
    }
    @Override
    public String toString(){
        return imie+" "+nazwisko;
    }
}

