package com.example.jamal.pastebin.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class PasteRoom implements Parcelable{

    public static final Creator<PasteRoom> CREATOR = new Creator<PasteRoom>() {
        @Override
        public PasteRoom createFromParcel(Parcel in) {
            return new PasteRoom(in);
        }

        @Override
        public PasteRoom[] newArray(int size) {
            return new PasteRoom[size];
        }
    };


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String key;
    private int date;
    private String title;
    private int size;
    private int expireDate;
    private int pastePrivate;
    private String formatLong;
    private String formatShort;
    private String url;
    private int hits;

    public PasteRoom(String key, int date, String title, int size, int expireDate, int pastePrivate, String formatLong, String formatShort, String url, int hits) {
        this.key = key;
        this.date = date;
        this.title = title;
        this.size = size;
        this.expireDate = expireDate;
        this.pastePrivate = pastePrivate;
        this.formatLong = formatLong;
        this.formatShort = formatShort;
        this.url = url;
        this.hits = hits;
    }

    protected PasteRoom(Parcel in) {
        id = in.readInt();
        key = in.readString();
        date = in.readInt();
        title = in.readString();
        size = in.readInt();
        expireDate = in.readInt();
        pastePrivate = in.readInt();
        formatLong = in.readString();
        formatShort = in.readString();
        url = in.readString();
        hits = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(key);
        dest.writeInt(date);
        dest.writeString(title);
        dest.writeInt(size);
        dest.writeInt(expireDate);
        dest.writeInt(pastePrivate);
        dest.writeString(formatLong);
        dest.writeString(formatShort);
        dest.writeString(url);
        dest.writeInt(hits);
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public int getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public int getExpireDate() {
        return expireDate;
    }

    public int getPastePrivate() {
        return pastePrivate;
    }

    public String getFormatLong() {
        return formatLong;
    }

    public String getFormatShort() {
        return formatShort;
    }

    public String getUrl() {
        return url;
    }

    public int getHits() {
        return hits;
    }

    public void setId(int id) {
        this.id = id;
    }
}