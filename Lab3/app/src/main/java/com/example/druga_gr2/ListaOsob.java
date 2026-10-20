package com.example.druga_gr2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListaOsob {
    private static ListaOsob mLista;
    private List<Osoba> mOsoby;

    private ListaOsob(){
        mOsoby = new ArrayList<>();
    }
    public static ListaOsob get(){
        if(mLista == null){
            mLista = new ListaOsob();
        }
        return  mLista;
    }
    public void dodajOsobe(Osoba mOsoba){
        mOsoby.add(mOsoba);
    }
    public List<Osoba> pobierzOsoby()
    {
        return mOsoby;
    }
    public Osoba pobierzOsobe(String idOsoby){
        for(Osoba osoba:mOsoby){
            if(osoba.getmIdOsoba().equals(idOsoby)){
                return osoba;
            }
        }
        return null;
    }
}
