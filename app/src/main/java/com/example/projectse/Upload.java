package com.example.projectse;

public class Upload {

    private String mImageurl;
    private String mName;

    public Upload() {

    }

    public Upload(String name, String imageurl){
        if(name.trim().equals("")){
            name =  "No Name";
        }

        mName = name;
        mImageurl = imageurl;

    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public String getImageurl(){
        return mImageurl;
    }

    public void setImageirl(String imageurl){
        mImageurl = imageurl;
    }

}
