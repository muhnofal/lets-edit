package com.example.projectse;

public class Data_User {

    String nama, email, gender;

    public Data_User() {
    }

    public Data_User(String nama, String email, String gender) {
        this.nama = nama;
        this.email = email;
        this.gender = gender;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
