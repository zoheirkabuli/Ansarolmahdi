package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class Courses extends AppCompatActivity implements MyAdapter.OnItemListener {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<String> test = new ArrayList<>();
    private FloatingActionButton fabStudent,fabClass,fabTeacher;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        addToArray();

        init();

        myAdapter = new MyAdapter(Courses.this,test,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Courses.this));
        recyclerView.setAdapter(myAdapter);

        setSupportActionBar(mToolbar);
        setTitle("دوره ها");
        mToolbar.setTitleTextColor(Color.WHITE);

        fabTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,AddTeacher.class);
                startActivity(intent);
            }
        });

        fabStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,AddStudent.class);
                startActivity(intent);
            }
        });

        fabClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, AddCourse.class);
                startActivity(intent);
            }
        });

    }

    private void addToArray() {
        test.add("دوره یک");
        test.add("دوره دو");
        test.add("دوره سه");
        test.add("دوره چهار");
        test.add("دوره پنج");
        test.add("دوره شش");
        test.add("دوره هفت");
        test.add("دوره هشت");
        test.add("دوره نه");
        test.add("دوره ده");
    }

    private void init() {
        recyclerView = findViewById(R.id.rv_classes);
        fabClass = findViewById(R.id.fab_class);
        fabStudent = findViewById(R.id.fab_student);
        fabTeacher = findViewById(R.id.fab_teacher);
        mToolbar = findViewById(R.id.tb_classes);
    }



    public void toast(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CourseInfo.class);
        intent.putExtra("COURSENAME",test.get(position));
        startActivity(intent);
    }
}
