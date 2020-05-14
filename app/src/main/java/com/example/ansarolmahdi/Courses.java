package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.Course;
import com.example.ansarolmahdi.classes.MyDate;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.ansarolmahdi.AddCourse.DATE_FORMAT;

public class Courses extends AppCompatActivity implements MyAdapter.OnItemListener {

    public static final String KEY_COURSE = "COURSENAME";

    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker url;
    private Map<String,String> map;
    private JSONObject resJsonObject;
    private String res;
    private ArrayList<Course> courses;
    private ArrayList<String> titles;

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private FloatingActionButton fabPerson,fabCourse;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        init();
        runToolbar();
        postAction();
        attemptCourses();


        fabPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this,AddPerson.class);
                startActivity(intent);
            }
        });

        fabCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Courses.this, AddCourse.class);
                startActivity(intent);
            }
        });

    }

    private void runToolbar() {
        setSupportActionBar(mToolbar);
        setTitle("دوره ها");
        mToolbar.setTitleTextColor(Color.WHITE);
    }

    private void init() {
        recyclerView = findViewById(R.id.rv_classes);
        fabCourse = findViewById(R.id.fab_course);
        fabPerson = findViewById(R.id.fab_person);
        mToolbar = findViewById(R.id.tb_classes);
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage(getString(R.string.wait));
        progressDialog.show();
        url = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.courses),this);
        map = new HashMap<>();
    }



    public void toast(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    private void attemptCourses() {
        final VolleyHandle vh = new VolleyHandle(url.getURL(),
                getString(R.string.request_tag_string),Courses.this,
                resListener,errorListener,map);
        vh.sendRequest();
    }

    public void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG",response.toString());
                try {
                    progressDialog.dismiss();
                    courses = new ArrayList<>();

                    JSONObject obj = new JSONObject(response);
                    JSONArray ja = obj.getJSONArray("course");
                    titles = new ArrayList<>();
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject temp = ja.getJSONObject(i);
                        Course crs = new Course();
                        crs.setTitle(temp.getString("title"));
                        crs.setCost(temp.getInt("cost"));
                        crs.setNumberOfSessions(temp.getInt("numofsessions"));
                        crs.setStartDate(temp.getString("startdate"));
                        crs.setCourseID(temp.getInt("courseid"));
                        crs.setTime(temp.getString("time"));
                        courses.add(crs);
                        titles.add(crs.getTitle());
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
//                Log.d("response",error.getMessage());
            }
        };
    }

    private void runAdapter(){
        myAdapter = new MyAdapter(Courses.this,titles,Courses.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(Courses.this));
        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(Courses.this, DetailCourse.class);
        intent.putExtra(KEY_COURSE,courses.get(position));
        startActivity(intent);
    }
}
