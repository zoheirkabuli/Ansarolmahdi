package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.Attendance;
import com.example.ansarolmahdi.classes.Course;
import com.example.ansarolmahdi.classes.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.ansarolmahdi.Courses.KEY_COURSE;

public class CourseStudent extends AppCompatActivity implements MyAdapter.OnItemListener{

//    server
    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url_students;
    private Map<String,String> map;
    private ProgressDialog progressDialog;

//    variables
    private Toolbar csToolbar;
    private FloatingActionButton fabAdd;
    private ArrayList<String> studentsName = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private Bundle intent;
    private int courseID;
    private RecyclerView rvStudents;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_student);

        init();

        getFromIntent();

        postActionStudents();
        attemptStudents();

        toolbarDo();

    }

    private void getFromIntent() {
        intent = getIntent().getExtras();
        courseID = intent.getInt(KEY_COURSE);
    }

    private void init() {
        fabAdd = findViewById(R.id.fab_add);
        rvStudents = findViewById(R.id.rv_course_student);
        csToolbar = findViewById(R.id.csToolbar);

        url_students = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.coursestudent),CourseStudent.this);
    }


    private void toolbarDo(){

        setSupportActionBar(csToolbar);
        setTitle(intent.getString(KEY_COURSE));
        csToolbar.setTitleTextColor(Color.WHITE);
        csToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void postActionStudents(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG", "onResponse: " + response);
                try {
                    JSONObject resJsonObject = new JSONObject(response);
                    JSONArray ja = resJsonObject.getJSONArray("students");
                    for (int i = 0; i < ja.length(); i++) {
                        Student student = new Student();
                        JSONObject temp = ja.getJSONObject(i);
                        student.setStudentID(temp.getInt("studentid"));
                        student.setfName(temp.getString("firstname"));
                        student.setlName(temp.getString("lastname"));
                        student.seteMail(temp.getString("email"));
                        student.setPassword(temp.getString("password"));
                        student.setPersonID(temp.getInt("personid"));
                        studentsName.add(student.getfName() + " " +student.getlName());
                        students.add(student);
                    }

                    runAdapter();

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

    private void runAdapter() {
        myAdapter = new MyAdapter(CourseStudent.this, studentsName, this);
        rvStudents.setLayoutManager(new LinearLayoutManager(CourseStudent.this));
        rvStudents.setAdapter(myAdapter);
    }

    private void attemptStudents() {
        map = new HashMap<>();
        map.put("courseid",courseID+"");
        final VolleyHandle vh = new VolleyHandle(url_students.getURL(),
                getString(R.string.request_tag_string), CourseStudent.this,
                resListener,errorListener,map);
        vh.sendRequest();


    }

    @Override
    public void onItemClick(int position) {

    }
}

