package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class ClassInfo extends AppCompatActivity implements MyAdapter.OnItemListener {

    private Toolbar ciToolbar;
    private Button info,sessions,students,attendance,homeWorks,exams;
    private RecyclerView rvSessions;
    private MyAdapter myAdapter;
    private ArrayList<String> test = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);
        init();

        test.add("Test1");
        test.add("Test2");
        test.add("Test3");
        test.add("Test4");
        test.add("Test5");
        test.add("Test6");
        test.add("Test7");
        test.add("Test8");
        test.add("Test9");
        test.add("Test10");


        myAdapter = new MyAdapter(ClassInfo.this,test,this);
        rvSessions.setLayoutManager(new LinearLayoutManager(ClassInfo.this));
        rvSessions.setAdapter(myAdapter);

        setSupportActionBar(ciToolbar);
        setTitle("دوره آزمایشی");
        ciToolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ciToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void init(){
//        info = findViewById(R.id.btn_info);
//        sessions = findViewById(R.id.btn_sessions);
        ciToolbar = findViewById(R.id.ciToolbar);
        rvSessions = findViewById(R.id.rv_sessions);
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

//        ViewGroup viewGroup = findViewById(android.R.id.content);
//
//
//        new AlertDialog.Builder(this)
//                .setView(LayoutInflater.from(this).inflate(R.layout.info_dialog
//                        ,viewGroup
//                        ,false))
//                .create()
//                .show();

    }

    @Override
    public void onItemClick(int position) {

    }
}
