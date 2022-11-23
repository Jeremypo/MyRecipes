package com.bignerdranch.android.myrecipes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewUneditableActivity extends AppCompatActivity {
    private String recipeName;
    private ArrayList<IngredientModel> list;
    private HashMap<String, ArrayList<IngredientModel>> saveData;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uneditable);

        Bundle extras = getIntent().getExtras();
        recipeName = extras.getString("recipeName");

        System.out.println("set name:" + recipeName);
        loadSaveData();
        list = saveData.get(recipeName);
        context = this;

        configureHeading();

        RecyclerView recyclerView = findViewById(R.id.ingredient_list_view_uneditable);
        configureRecycler(recyclerView);

        configureBackButton();
        configureDeleteButton();
        configureEditButton();
    }

    private void configureHeading() {
        TextView heading = findViewById(R.id.recipe_name_uneditable);
        heading.setText(recipeName);
    }

    private void configureRecycler(RecyclerView recyclerView){
        UneditableRecyclerViewAdapter adapter =
                new UneditableRecyclerViewAdapter(this, list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureBackButton(){
        Button back = findViewById(R.id.back_uneditable);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }

    private void configureDeleteButton(){
        Button delete = findViewById(R.id.delete_uneditable);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setCancelable(true);
                builder.setTitle("Warning!");
                builder.setMessage("Are you sure you want to delete \""
                        + recipeName + "\" ?");

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String msg = ("\"" + recipeName + "\" deleted.");
                        final int DUR = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, msg, DUR);
                        toast.show();

                        removeData(recipeName);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }

    private void configureEditButton(){
        Button edit = findViewById(R.id.edit_uneditable);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditExistingRecipeActivity.class);
                i.putExtra("recipeName", recipeName);
                context.startActivity(i);
            }
        });
    }

    private void loadSaveData() {
        File file = new File(getApplicationContext().getDir("data", context.MODE_PRIVATE), "recipes");
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

    private void removeData(String name){
        saveData.remove(name);

        File file = new File(context.getDir("data", context.MODE_PRIVATE), "recipes");
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(saveData);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
