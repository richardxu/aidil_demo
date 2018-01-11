package com.richard.servicedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by richardxu on 2018/1/11.
 */

public class Person implements Parcelable{
    private String name;
    private String telNumber;
    private int age;

    public Person() {
    }

    protected Person(Parcel in) {
        name = in.readString();
        telNumber = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(telNumber);
        parcel.writeInt(age);
    }

//    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){
//
//        @Override
//        public Person createFromParcel(Parcel source) {
//            return new Person(source);
//        }
//
//        @Override
//        public Person[] newArray(int size) {
//            return new Person[size];
//        }
//    };
}
