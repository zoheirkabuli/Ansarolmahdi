package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

public class Classes extends AppCompatActivity {

    private FloatingActionButton fabStudent,fabClass,fabTeacher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        init();

        fabTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("Teacher");
            }
        });

        fabStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("Student");
            }
        });

        fabClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("Class");
            }
        });

    }

    private void init() {
        fabClass = findViewById(R.id.fab_class);
        fabStudent = findViewById(R.id.fab_student);
        fabTeacher = findViewById(R.id.fab_teacher);
    }



    public void toast(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }
}
