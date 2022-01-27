package com.r2a.touran.data;





public class Place {
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

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
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

    private String name;
    private Double longitude;
    private Double latitude;
    private double startingprice;
    private PlaceType type;
    private double rank;
    private String imglink;

    public Place(String name, Double longitude, Double latitude, double startingprice, PlaceType type, double rank, String imglink, String description) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startingprice = startingprice;
        this.type = type;
        this.rank = rank;
        this.imglink = imglink;
        this.description = description;
    }

    private String description;

    public enum PlaceType {
        CULTURE, NOURRITURE, DIVERTISSEMENT, NATURE, SHOPPING
    }


}
