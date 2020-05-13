package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CourseInfo extends AppCompatActivity implements MyAdapter.OnItemListener {

    private Toolbar ciToolbar;
    private Button info,sessions,students,attendance,homeWorks,exams;
    private RecyclerView rvSessions;
    private MyAdapter myAdapter;
    private ArrayList<String> test = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        init();

        addToArray();

        setAdapter();

        toolbarOption();



    }

    private void toolbarOption() {
        Intent intent = getIntent();
        setSupportActionBar(ciToolbar);
        setTitle(intent.getStringExtra("COURSENAME"));
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

    private void setAdapter() {
        myAdapter = new MyAdapter(CourseInfo.this,test,this);
        rvSessions.setLayoutManager(new LinearLayoutManager(CourseInfo.this));
        rvSessions.setAdapter(myAdapter);
    }

    private void addToArray() {
        test.add("جلسه اول");
        test.add("جلسه دوم");
        test.add("جلسه سوم");
        test.add("جلسه پنجم");
        test.add("جلسه ششم");
        test.add("جلسه هفتم");
        test.add("جلسه هشتم");
        test.add("جلسه نهم");
        test.add("جلسه دهم");
        test.add("جلسه یازدهم");
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

        Intent intent = new Intent(CourseInfo.this,SessionActivity.class);
        intent.putExtra("TESTTEST",test.get(position));
        startActivity(intent);

    }
}
