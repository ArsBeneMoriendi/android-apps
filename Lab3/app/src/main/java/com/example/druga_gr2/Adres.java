package com.example.druga_gr2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Adres implements Parcelable {
    private String miasto;
    private String ulica;
    private String numerDomu;
    private String kodPocztowy;

    public Adres(String miasto, String ulica, String numerDomu, String kodPocztowy) {
        this.miasto = miasto;
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(String numerDomu) {
        this.numerDomu = numerDomu;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    protected Adres(Parcel parcel) {
        miasto = parcel.readString();
        ulica = parcel.readString();
        numerDomu = parcel.readString();
        kodPocztowy = parcel.readString();
    }

    public static final Creator<Adres> CREATOR = new Creator<Adres>() {
        @Override
        public Adres createFromParcel(Parcel parcel) {
            return new Adres(parcel);
        }

        @Override
        public Adres[] newArray(int size) {
            return new Adres[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeString(miasto);
        parcel.writeString(ulica);
        parcel.writeString(numerDomu);
        parcel.writeString(kodPocztowy);
    }
}
