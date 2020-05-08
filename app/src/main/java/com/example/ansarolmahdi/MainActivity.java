package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button login;
    private RadioButton rbManager,rbStudent,rbParent,rbTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        checkListener();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Classes.class);
                startActivity(intent);

                finish();
            }
        });




    }

    private void init(){
        login = findViewById(R.id.btn_login);
        rbManager = findViewById(R.id.rb_manager);
        rbParent = findViewById(R.id.rb_parent);
        rbStudent = findViewById(R.id.rb_student);
        rbTeacher = findViewById(R.id.rb_teacher);
    }



    public void checkListener(){
        rbManager.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbStudent.isChecked()) {
                    rbStudent.setChecked(false);
                }
                if (rbParent.isChecked()){
                    rbParent.setChecked(false);
                }
                if (rbTeacher.isChecked()){
                    rbTeacher.setChecked(false);
                }


            }
        });

        rbTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbStudent.isChecked()) {
                    rbStudent.setChecked(false);
                }
                if (rbParent.isChecked()){
                    rbParent.setChecked(false);
                }
                if (rbManager.isChecked()){
                    rbManager.setChecked(false);
                }
            }
        });

        rbStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbManager.isChecked()) {
                    rbManager.setChecked(false);
                }
                if (rbTeacher.isChecked()){
                    rbTeacher.setChecked(false);
                }
                if (rbParent.isChecked()){
                    rbParent.setChecked(false);
                }
            }
        });

        rbParent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbManager.isChecked()){
                    rbManager.setChecked(false);
                }
                if (rbTeacher.isChecked()){
                    rbTeacher.setChecked(false);
                }

                if (rbStudent.isChecked()){
                    rbStudent.setChecked(false);
                }
            }
        });
    }
}
