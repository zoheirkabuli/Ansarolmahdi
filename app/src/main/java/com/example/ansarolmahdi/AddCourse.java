package com.example.ansarolmahdi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.MyDate;
import com.example.ansarolmahdi.classes.Teacher;
import com.example.ansarolmahdi.classes.TimePickerFragment;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCourse extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url,urlTeachers;
    private Map<String,String> map;
    private JSONObject resJsonObject;
    private String res;
    private static int roleID;
    private ProgressDialog progressDialog;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat ("yyyy-MM-dd");

    private TextInputEditText mTitle,mTuition,mNomOfSessions;
    private Button btnSelectStartDate,btnWeekDays,btnTime,addCourse;
    private Spinner spinnerSelectTeacher;

    private MyDate startDate;
    private ArrayList<String> checkedDays = new ArrayList<>();
    private String timeDays;
    private String selectedTeacher;
    private boolean[] selectedDays;


    private String[] nameOfDays;
    private boolean[] checkedNameOfDays;


    private Toolbar cToolbar;
    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private String[] teachersName;
    private ArrayList<Teacher> mTeachers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        init();

        postActionTeachers();
        attemptTeachers();

        toolbarHandler();

        datePickerHandler();

        weekDaysHandler();

        timePickerHandler();

        postAction();


        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptAddCourse();
            }
        });


    }

    private void spinnerHandler() {
        ArrayAdapter<String> teacherNameAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, teachersName);
        teacherNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSelectTeacher.setAdapter(teacherNameAdapter);

        spinnerSelectTeacher.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedTeacher = mTeachers.get(spinnerSelectTeacher.getSelectedItemPosition()).geteMail();
                if (selectedTeacher.equals("استاد را انتخاب کنید")){
                    Log.d("TAGTAG", "onItemSelected: " + "استاد را انتخاب کنید");;
                    toast("استاد را انتخاب کنید");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void toolbarHandler() {
        setSupportActionBar(cToolbar);
        cToolbar.setTitleTextColor(Color.WHITE);
        setTitle(getString(R.string.add_course));
        cToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void weekDaysHandler() {
        btnWeekDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runAlert();
            }
        });
    }

    private void init(){
        addCourse = findViewById(R.id.btn_addcourse);
        cToolbar = findViewById(R.id.cToolbar);
        btnTime =findViewById(R.id.btn_times);
        spinnerSelectTeacher = findViewById(R.id.spinner_selectteacher);
        btnSelectStartDate = findViewById(R.id.btn_startdate);
        btnWeekDays = findViewById(R.id.btn_weekdays);

        mTitle = findViewById(R.id.et_title);
        mNomOfSessions = findViewById(R.id.et_nosessions);
        mTuition = findViewById(R.id.et_cost);

        progressDialog = new ProgressDialog(this);

        urlTeachers = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.teachers),this);
        url = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.addcourse),this);
        map = new HashMap<>();
    }

    private void datePickerHandler(){
        btnSelectStartDate.setOnClickListener(new View.OnClickListener() {
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

    private void timePickerHandler(){
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"TIMEPICKER");
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        if (hour < 10 && minute < 10){
            timeDays = "0"+hour+":"+"0"+minute+":00";
        }else if (hour < 10){
            timeDays = "0"+hour+":"+minute+":00";
        }else if (minute < 10){
            timeDays = hour+":"+"0"+minute+":00";
        }else {
            timeDays = hour+":"+minute+":00";
        }

        Log.d("TAGTAG", "onTimeSet: " + timeDays);
    }

    private void runAlert() {

        nameOfDays = getResources().getStringArray(R.array.name_of_days);
        checkedNameOfDays =new boolean[] {false,false,false,false,false,false,false};
        final List<String> nameOfDaysList = Arrays.asList(nameOfDays);


        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.week_days))
                .setCancelable(false)
                .setMultiChoiceItems(nameOfDays, checkedNameOfDays, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {

                        checkedNameOfDays[which] = isChecked;

                        String currentItem = nameOfDaysList.get(which);

                        Log.d("TAGTAG", "onClick: " + currentItem + " " + isChecked);


                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedDays = new boolean[checkedNameOfDays.length];
                        for (int j = 0; j < checkedNameOfDays.length; j++) {

                            if (checkedNameOfDays[j]){
                                selectedDays[j] = checkedNameOfDays[j];
                                Log.d("TAGTAG", "onClick: " + "size is : "  + j + selectedDays[j]);
                            }
                        }

                    }
                })
                .create()
                .show();


    }

    public void toast(String msg){
        Toast.makeText(AddCourse.this, msg, Toast.LENGTH_LONG).show();
    }

    private void attemptTeachers() {
        map = new HashMap<>();
        final VolleyHandle vh = new VolleyHandle(urlTeachers.getURL(),
                getString(R.string.request_tag_string),this,
                resListener,errorListener,map);
        vh.sendRequest();
    }

    private void attemptAddCourse() {

        if(mTitle.length() == 0 || mTuition.length() == 0 || mNomOfSessions.length() == 0 || startDate == null
                ||  timeDays.length() == 0 || selectedTeacher.length() == 0
                || !noneSelected(selectedDays)){
            toast("تمامی فیلدها اجباری هستند");
        }else{


                progressDialog.show();
                String title = mTitle.getText().toString();
                String tuition = mTuition.getText().toString();
                String nomOfSessions = mNomOfSessions.getText().toString();
                String teacherEmail = selectedTeacher;


                map.put("title",title);
                map.put("cost",tuition);
                map.put("numofsessions",nomOfSessions);
                map.put("teacheremail",teacherEmail);
                map.put("first",DATE_FORMAT.format(startDate));
                map.put("time",timeDays);
                map.put("sat",booleanToInt(selectedDays[0])+"");
                map.put("sun",booleanToInt(selectedDays[1])+"");
                map.put("mon",booleanToInt(selectedDays[2])+"");
                map.put("tue",booleanToInt(selectedDays[3])+"");
                map.put("wed",booleanToInt(selectedDays[4])+"");
                map.put("thu",booleanToInt(selectedDays[5])+"");
                map.put("fri",booleanToInt(selectedDays[6])+"");


                final VolleyHandle vh = new VolleyHandle(url.getURL(),
                        getString(R.string.request_tag_string),AddCourse.this,
                        resListener,errorListener,map);
                vh.sendRequest();


        }
    }

    public void postActionTeachers(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response",response.toString());
                try {
                    mTeachers = new ArrayList<>();
                    JSONObject obj = new JSONObject(response);
                    JSONArray ja = obj.getJSONArray("teachers");
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject temp = ja.getJSONObject(i);
                        Teacher teacher = new Teacher();
                        teacher.setfName(temp.getString("firstname"));
                        teacher.setlName(temp.getString("lastname"));
                        teacher.seteMail(temp.getString("email"));
                        teacher.setTeacherID(temp.getInt("personid"));
                        teacher.setPassword(temp.getString("password"));
                        mTeachers.add(teacher);
                    }

                    Log.d("TAGTAG", "onResponse: " + mTeachers.get(0).geteMail());

                    teachersName = new String[mTeachers.size()];

                    for (int i = 0; i < mTeachers.size(); i++) {

                        teachersName[i] = mTeachers.get(i).getfName() +" "+ mTeachers.get(i).getlName();

                    }
                    spinnerHandler();
                    Log.d("TAGTAG", "onResponse: " + teachersName[0]);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.getMessage());
            }
        };
    }

    public void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("responseAdd",response.toString());
                    progressDialog.dismiss();
                    resJsonObject = new JSONObject(response);
                    res = resJsonObject.getString("response");
                    Log.d("responseAdd",res);
                    if(res.equals(getString(R.string.yes))){
                        toast("شخص اضافه شد");
                    }else{
                        toast(getString(R.string.wrong_login));
                        res = resJsonObject.getString("msg");
                        Log.d("responseAdd",res);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("response",error.getMessage());
                toast(getString(R.string.add_person_unsuccessful));
            }
        };
    }


    private int booleanToInt(boolean bln){
        if (bln){
            return 1;
        }else {
            return 0;
        }
    }

    private boolean noneSelected(boolean[] blns){
        for (int i = 0; i < blns.length; i++) {
            if (blns[i] == true){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
}
