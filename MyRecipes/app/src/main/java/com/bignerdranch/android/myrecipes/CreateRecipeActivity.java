package com.bignerdranch.android.myrecipes;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateRecipeActivity extends AppCompatActivity {
    private EditText inputRecipeName;
    private ArrayList<IngredientModel> ingredientModels = new ArrayList<>();
    private HashMap<String, ArrayList<IngredientModel>> saveData = new HashMap<>();
    private RecyclerView recyclerView;
    private IngredientRecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        loadSaveData();

        recyclerView = findViewById(R.id.ingredient_list_view);

        configureBackToMainButton();
        configureSaveButton();

        Button addIngredientButton = findViewById(R.id.add_ingredient_button);
        addIngredientButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addIngredient();
            }
        });

        configureRecycler();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putParcelableArrayList("ingredientModels", ingredientModels);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        ingredientModels = savedInstanceState.getParcelableArrayList("ingredientModels");

        recyclerView = findViewById(R.id.ingredient_list_view);
        configureRecycler();
    }

    private void configureRecycler(){
        inputRecipeName = findViewById(R.id.inputRecipeName);

        adapter = new IngredientRecyclerViewAdapter(this,
                ingredientModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    private void configureBackToMainButton() {
        Button mainMenuButton = findViewById(R.id.back_to_main_create);
        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(ingredientModels.size() > 0 || inputRecipeName.length() > 0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CreateRecipeActivity.this);

                    builder.setCancelable(true);
                    builder.setTitle("Warning!");
                    builder.setMessage("Are you sure you want to exit?");

                    builder.setNegativeButton("Don't Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.show();
                }

                else
                    finish();
            }
        });
    }

    private void configureSaveButton(){
        Button createSaveRecipeButton = findViewById(R.id.save_recipe_create);
        createSaveRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                saveRecipe();
            }
        });
    }

    private void addIngredient(){
        ingredientModels.add(new IngredientModel());
        configureRecycler();
    }

    private void loadSaveData(){
        File file = new File(getDir("data", MODE_PRIVATE), "recipes");

        if(!file.exists())
            System.out.println("file DNE");
        else {
            ObjectInputStream inputStream = null;
            try {
                inputStream = new ObjectInputStream(new FileInputStream(file));
                saveData = (HashMap<String, ArrayList<IngredientModel>>)inputStream.readObject();
                inputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveRecipe(){
        File file = new File(getDir("data", MODE_PRIVATE), "recipes");

        String key = (inputRecipeName.getText().toString().trim().length() == 0) ? "Nameless Recipe" : inputRecipeName.getText().toString();

        if(!saveData.containsKey(key)) {
            saveData.put(key, adapter.getIngredients());
            makeToast("\"" + key + "\" saved.");
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateRecipeActivity.this);

            builder.setCancelable(true);
            builder.setTitle("Warning!");
            builder.setMessage("Recipe already exists! Saving will overwrite old recipe data!");

            builder.setNegativeButton("Continue Editing", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });

            builder.setPositiveButton("Save Anyway", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    saveData.put(key, adapter.getIngredients());
                    makeToast("\"" + key + "\" overwritten.");
                    //dialogInterface.cancel();
                }
            });
            builder.show();
        }

        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(saveData);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Keys: " + saveData.size());
        for (String keys: saveData.keySet()){
            System.out.println(keys);
        }
    }

    private void makeToast(String msg){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, msg, duration);
        toast.show();
    }

}