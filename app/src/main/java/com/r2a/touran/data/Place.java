package com.r2a.touran.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {

    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
    private double startingprice;
    private String type;
    private Point location;
    private double rate;
    private String imglink;

    protected Place(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        startingprice = in.readDouble();
        type = in.readString();
        rate = in.readDouble();
        imglink = in.readString();
        description = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(name);
        if (longitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(longitude);
        }
        if (latitude == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(latitude);
        }
        parcel.writeDouble(startingprice);
        parcel.writeString(type);
        parcel.writeDouble(rate);
        parcel.writeString(imglink);
        parcel.writeString(description);
    }

    public enum PlaceType {
        CULTURE, NOURRITURE, DIVERTISSEMENT, NATURE, SHOPPING
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
