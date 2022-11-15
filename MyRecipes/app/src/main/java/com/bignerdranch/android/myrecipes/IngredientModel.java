package com.bignerdranch.android.myrecipes;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientModel implements Parcelable {
    String inName;
    String inQuantity;
    String inUnits;

    //defaults
    private final String DEF_EMPTY = "";
    private final String DEF_QUANTITY = "1.0";

    public IngredientModel(String inName, String inQuantity, String inUnits) {
        this.inName = inName;
        this.inQuantity = inQuantity;
        this.inUnits = inUnits;
    }

    public IngredientModel(){
        this.inName = DEF_EMPTY;
        this.inQuantity = DEF_QUANTITY;
        this.inUnits = DEF_EMPTY;
    }

    protected IngredientModel(Parcel in) {
        this.inName = in.readString();
        if (in.readByte() == 0) {
            this.inQuantity = DEF_QUANTITY;
        } else {
            this.inQuantity = in.readString();
        }
        this.inUnits = in.readString();
    }

    public static final Creator<IngredientModel> CREATOR = new Creator<IngredientModel>() {
        @Override
        public IngredientModel createFromParcel(Parcel in) {
            return new IngredientModel(in);
        }

        @Override
        public IngredientModel[] newArray(int size) {
            return new IngredientModel[size];
        }
    };

    public void configureDeleteButton(){

    }

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getInQuantity() {
        return inQuantity;
    }

    public void setInQuantity(String inQuantity) {
        this.inQuantity = inQuantity;
    }

    public String getInUnits() {
        return inUnits;
    }

    public void setInUnits(String inUnits) {
        this.inUnits = inUnits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(inName);
        if(inQuantity.equals("."))
            parcel.writeString(DEF_QUANTITY);
        else
            parcel.writeString(inQuantity);
        parcel.writeString(inUnits);
    }
}
