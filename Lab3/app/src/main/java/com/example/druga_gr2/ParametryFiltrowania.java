package com.example.druga_gr2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ParametryFiltrowania implements Parcelable {
    private String tekst;
    private boolean sortowanieRosnaco;

    public ParametryFiltrowania(){
        tekst = "";
        sortowanieRosnaco = true;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public boolean isSortowanieRosnaco() {
        return sortowanieRosnaco;
    }

    public void setSortowanieRosnaco(boolean sortowanieRosnaco) {
        this.sortowanieRosnaco = sortowanieRosnaco;
    }

    protected ParametryFiltrowania(Parcel in) {
        tekst = in.readString();
        sortowanieRosnaco = in.readByte() !=0;
    }

    public static final Creator<ParametryFiltrowania> CREATOR = new Creator<ParametryFiltrowania>() {
        @Override
        public ParametryFiltrowania createFromParcel(Parcel in) {
            return new ParametryFiltrowania(in);
        }

        @Override
        public ParametryFiltrowania[] newArray(int size) {
            return new ParametryFiltrowania[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(tekst);
        parcel.writeByte((byte)(sortowanieRosnaco?1:0));
    }
}
