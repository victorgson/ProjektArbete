package com.example.projektarbete;

public class Restaurants {

    String name, info;
    int imageId;

    public Restaurants(String name, String info, int imageId) {
        this.name = name;
        this.info = info;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    @Override
    public String toString() {
        return "Restaurants{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public Restaurants() {
        this.imageId = R.drawable.bombay;
    }
}
