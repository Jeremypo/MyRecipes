package com.bignerdranch.android.myrecipes;

public class IngredientModel {
    String inName;
    Double inQuantity;
    String inUnits;

    //defaults
    private final String DEF_NAME = "ingredient name";
    private final Double DEF_QUANTITY = 1.0;
    private final String DEF_UNIT = "units";

    public IngredientModel(String inName, Double inQuantity, String inUnits) {
        this.inName = inName;
        this.inQuantity = inQuantity;
        this.inUnits = inUnits;
    }

    public IngredientModel(){
        this.inName = DEF_NAME;
        this.inQuantity = DEF_QUANTITY;
        this.inUnits = DEF_UNIT;
    }

    public void configureDeleteButton(){

    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public Double getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(Double inQuantity) {
        this.inQuantity = inQuantity;
    }

    public String getInUnits() {
        return inUnits;
    }

    public void setInUnits(String inUnits) {
        this.inUnits = inUnits;
    }
}
