package com.r2a.touran.data;





public class Place {

    private String name;
    private Double longitude;
    private Double latitude;
    private double startingprice;
    private PlaceType type;
    private double rate;
    private String imglink;

    public Place(String name, Double longitude, Double latitude, double rate, String imglink, String description,PlaceType type) {
        this.name = name;
        this.type=type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rate = rate;
        this.imglink = imglink;
        this.description = description;
    }

    public Place(String name, PlaceType placeType, double latitude, double longitude) {
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
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
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

    public PlaceType getType() {
        return type;
    }

    public void setType(PlaceType type) {
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


    public Place(String name, Double longitude, Double latitude, double startingprice, PlaceType type, double rank, String imglink, String description) {
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


}
