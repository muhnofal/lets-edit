package com.example.projectse;

public class Data_Editor2 {

    private int job2;
    private String tempat;

    public Data_Editor2() {

    }

    public Data_Editor2(int job2, String tempat) {
        this.job2 = job2;
        this.tempat = tempat;
    }

    //getter
    public int getJob2() {
        return job2;
    }

    public String getTempat() {
        return tempat;
    }

    //setter
    public void setJob2(int job2) {
        this.job2 = job2;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }
}
