package com.r2a.touran.Data;

import android.graphics.Point;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Place {
    private String name;
    private Point location;
    private double startingprice;
    private PlaceType type;
    private double rank;
    private String imglink;
    private String description;

    public enum PlaceType {
        CULTURE, NOURRITURE, DIVERTISSEMENT, NATURE, SHOPPING
    }


}
