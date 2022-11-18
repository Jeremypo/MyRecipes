package com.bignerdranch.android.myrecipes;

public class RecipeModel {
    String recipeName;

    public RecipeModel(String recipeName){
        this.recipeName = recipeName;
    }

    public RecipeModel(){
        recipeName = "Recipe";
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
