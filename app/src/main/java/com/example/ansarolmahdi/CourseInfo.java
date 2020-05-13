package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ansarolmahdi.classes.Course;

import java.util.ArrayList;

import static com.example.ansarolmahdi.Courses.KEY_COURSE;

public class CourseInfo extends AppCompatActivity implements MyAdapter.OnItemListener {

    private Toolbar ciToolbar;
    private Button info,sessions,students,attendance,homeWorks,exams;
    private RecyclerView rvSessions;
    private MyAdapter myAdapter;
    private ArrayList<String> test = new ArrayList<>();
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        init();

        intentHandler();

        addToArray();

        setAdapter();

        toolbarOption();

    }

    private void toolbarOption() {

        setSupportActionBar(ciToolbar);
        setTitle(course.getTitle());
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
        ciToolbar = findViewById(R.id.ciToolbar);
        rvSessions = findViewById(R.id.rv_sessions);
    }

    private void intentHandler(){

        Bundle intent = getIntent().getExtras();
        course = new Course();
        course = intent.getParcelable(KEY_COURSE);

    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(CourseInfo.this,SessionActivity.class);
        intent.putExtra("TESTTEST",test.get(position));
        startActivity(intent);

    }
}
