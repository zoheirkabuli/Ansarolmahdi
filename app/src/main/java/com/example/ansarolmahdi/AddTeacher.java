package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AddTeacher extends AppCompatActivity {

    private Toolbar tToolbar;
    private Button selectClasses;
    String[] items = {"class1", "class2", "class3", "class4"};
    boolean[] checkItems = {false,false,false,false};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        selectClasses = findViewById(R.id.btn_selectclasses);
        tToolbar = findViewById(R.id.atToolbar);


        setSupportActionBar(tToolbar);
        setTitle("Add Teacher");
        tToolbar.setTitleTextColor(Color.WHITE);

        selectClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAlert();
            }
        });


    }

    private void runAlert() {

//        new AlertDialog.Builder(this)
//                .setTitle("Select")
//                .setCancelable(false)
//                .setMultiChoiceItems(items,null,null)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(AddTeacher.this, "Yes Button clicked", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .create()
//                .show();

        ViewGroup viewGroup = findViewById(android.R.id.content);

        new AlertDialog.Builder(this)
                .setView(LayoutInflater.from(this).inflate(R.layout.info_dialog
                        ,viewGroup
                        ,false))
                .create()
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
