package com.example.week3_weekend;

import android.os.Parcel;
import android.os.Parcelable;

public class Repo_Item implements Parcelable {
    private String name;
    private int size;
    private String language;
    private int id;


    public Repo_Item() {
    }

    public Repo_Item(String name, int size, String language, int id){
        this.name = name;
        this.size = size;
        this.language = language;
        this.id = id;
    }

    protected Repo_Item(Parcel in) {
        name = in.readString();
        size = in.readInt();
        language = in.readString();
        id = in.readInt();
    }

    public static final Creator<Repo_Item> CREATOR = new Creator<Repo_Item>() {
        @Override
        public Repo_Item createFromParcel(Parcel in) {
            return new Repo_Item(in);
        }

        @Override
        public Repo_Item[] newArray(int size) {
            return new Repo_Item[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(size);
        parcel.writeString(language);
        parcel.writeInt(id);
    }
}
