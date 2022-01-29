package com.r2a.touran.data;





public class Place {

    private String name;
    private Double longitude;
    private Double latitude;
    private double startingprice;
    private String type;
    private Point location;
    private double rate;
    private String imglink;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Place(String name, Double longitude, Double latitude, double rate, String imglink, String description, String type) {
        this.name = name;
        this.type=type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rate = rate;
        this.imglink = imglink;
        this.description = description;
    }

    public Place(String name, String placeType, double latitude, double longitude) {
        this.name= name;
        this.type = placeType;
        this.latitude=latitude;
        this.longitude= longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return location.coordinates[0];
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return location.coordinates[1];
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getStartingprice() {
        return startingprice;
    }

    public void setStartingprice(double startingprice) {
        this.startingprice = startingprice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Place(String name, Double longitude, Double latitude, double startingprice, String type, double rank, String imglink, String description) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startingprice = startingprice;
        this.type = type;
        this.rate = rank;
        this.imglink = imglink;
        this.description = description;
    }

    private String description;

    public enum PlaceType {
        CULTURE, NOURRITURE, DIVERTISSEMENT, NATURE, SHOPPING
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", startingprice=" + startingprice +
                ", type='" + type + '\'' +
                ", location=" + location +
                ", rate=" + rate +
                ", imglink='" + imglink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
