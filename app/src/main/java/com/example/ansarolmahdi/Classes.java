package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class Classes extends AppCompatActivity {

    private FloatingActionButton fabStudent,fabClass,fabTeacher;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        init();

        setSupportActionBar(mToolbar);
        setTitle("Classes");
        mToolbar.setTitleTextColor(Color.WHITE);

        fabTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Classes.this,AddTeacher.class);
                startActivity(intent);
            }
        });

        fabStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Classes.this,AddStudent.class);
                startActivity(intent);
            }
        });

        fabClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Classes.this,AddClass.class);
                startActivity(intent);
            }
        });

    }

    private void init() {
        fabClass = findViewById(R.id.fab_class);
        fabStudent = findViewById(R.id.fab_student);
        fabTeacher = findViewById(R.id.fab_teacher);
        mToolbar = findViewById(R.id.tb_classes);
    }



    public void toast(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
