package com.r2a.touran.data;


import java.util.Arrays;

public class Point {
    String type="Point";
    double[] coordinates;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Point(String type, double[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public Point() {
    }

    public Point(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Point{" +
                "type='" + type + '\'' +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
