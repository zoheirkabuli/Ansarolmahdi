package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import static com.example.ansarolmahdi.Courses.KEY_COURSE;
import static com.example.ansarolmahdi.DetailCourse.KEY_SESSION;
import static com.example.ansarolmahdi.DetailCourse.KEY_SESSIONNUM;

public class SessionActivity extends AppCompatActivity {


    private TextView homeWorkTitle;
    private Toolbar seToolbar;
    private Intent intent;

//    intent
    private int sessionID;
    private int courseID;
    private String sessionNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        init();
        intentGetter();
        toolbarOption();
        mSetText();
    }

    private void mSetText() {
        homeWorkTitle.setText("تکلیف " + intent.getStringExtra(KEY_SESSIONNUM));
    }

    private void init() {
        seToolbar = findViewById(R.id.seToolbar);
        homeWorkTitle = findViewById(R.id.tv_homeworktitle);
    }

    private void intentGetter(){
        intent = getIntent();
        sessionNum = intent.getStringExtra(KEY_SESSIONNUM);
        sessionID = intent.getIntExtra(KEY_SESSION,0);
        courseID = intent.getIntExtra(KEY_COURSE,0);
    }

    private void toolbarOption() {
        setSupportActionBar(seToolbar);
        setTitle(sessionNum);
        seToolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        seToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
