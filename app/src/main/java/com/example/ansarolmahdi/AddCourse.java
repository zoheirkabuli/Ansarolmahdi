package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ansarolmahdi.classes.MyDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddCourse extends AppCompatActivity {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("yyyy_MM_dd");

    private MyDate startDate;

    private Toolbar cToolbar;
    private Button selectStartDate,weekDays;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        init();

        setSupportActionBar(cToolbar);
        cToolbar.setTitleTextColor(Color.WHITE);
        setTitle("Add Class");

        datePickerHandler();

        weekDaysHandler();

    }

    private void weekDaysHandler() {
        weekDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAlert();
            }
        });
    }

    private void init(){
        cToolbar = findViewById(R.id.cToolbar);
        selectStartDate = findViewById(R.id.btn_startdate);
        weekDays = findViewById(R.id.btn_weekdays);
    }

    private void datePickerHandler(){
        selectStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int  month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddCourse.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar,
                        onDateSetListener,
                        year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.show();
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int motnth, int day) {
                startDate = new MyDate();
                startDate.setYear(year);
                startDate.setMonth(motnth);
                startDate.setDate(day);

                Log.d("TAGTAG", "onCreate: " + motnth + "Current Date: " +  DATE_FORMAT.format(startDate));
            }
        };
    }

    private void runAlert() {

        String[] nameOfDays = getResources().getStringArray(R.array.name_of_days);

        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.week_days))
                .setCancelable(false)
                .setMultiChoiceItems(nameOfDays,null,null)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AddCourse.this, "Yes Button clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
