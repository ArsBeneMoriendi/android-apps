package com.example.druga_gr2;

import java.util.UUID;

public class Osoba {
    private String imie;
    private String nazwisko;
    private String mIdOsoba;
    private Adres adres;

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

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getmIdOsoba() {
        return mIdOsoba;
    }
    @Override
    public String toString(){
        return imie+" "+nazwisko;
    }
}

