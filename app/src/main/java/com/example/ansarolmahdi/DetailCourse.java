package com.example.ansarolmahdi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.ansarolmahdi.classes.Course;
import com.example.ansarolmahdi.classes.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.ansarolmahdi.Courses.KEY_COURSE;

public class DetailCourse extends AppCompatActivity implements MyAdapter.OnItemListener {

    public static final String KEY_SESSION = "SESSIONID";
    public static final String KEY_SESSIONNUM = "SESSIONNUM";
//      Views
    private Toolbar dcToolbar;
    private TextView mTime,mTimeClock,mCost,mCostNumber,mExamTime,mExamTimeClock;
    private Button mStudentsList;
    private RecyclerView mSessions;

//    fields

    private MyAdapter myAdapter;
    private Bundle intent;
    private Course course;
    private Session session;
    private ArrayList<Session> sessions;
    private ArrayList<String> sessionNumbers;

//    server fields
    private Response.Listener<String> resListener;
    private Response.ErrorListener errorListener;
    private URLMaker urlCourseSessions;
    private Map<String,String> map;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_course);

        init();
        postAction();
        attemptSessions();


    }

    private void setAdapter() {
        myAdapter = new MyAdapter(DetailCourse.this,sessionNumbers,this);
        mSessions.setLayoutManager(new LinearLayoutManager(DetailCourse.this));
        mSessions.setAdapter(myAdapter);
    }

    private void init() {
        mTime = findViewById(R.id.tv_time);
        mTimeClock = findViewById(R.id.tv_clock);
        mCost = findViewById(R.id.tv_tuition_text);
        mCostNumber = findViewById(R.id.tv_tuition);
        mExamTime = findViewById(R.id.tv_exam);
        mExamTimeClock = findViewById(R.id.tv_exam_time);
        mSessions = findViewById(R.id.rv_sessions);
//        fields
        intent = getIntent().getExtras();
        course = new Course();
        course = intent.getParcelable(KEY_COURSE);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.wait));

//         server
        urlCourseSessions = new URLMaker(getString(R.string.tetabyte),getString(R.string.ansar),
                getString(R.string.coursesessions),DetailCourse.this);
        map = new HashMap<>();
    }

    private void attemptSessions(){
        progressDialog.show();
        map.put("courseid",course.getCourseID()+"");
        final VolleyHandle vh = new VolleyHandle(urlCourseSessions.getURL(),
                getString(R.string.request_tag_string),DetailCourse.this,
                resListener,errorListener,map);
        vh.sendRequest();
    }

    private void postAction(){
        resListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAGTAG", "onResponse: " + response.toString());

                try {
                    progressDialog.dismiss();

                    session = new Session();
                    sessions = new ArrayList<>();
                    sessionNumbers = new ArrayList<>();

                    JSONObject object = new JSONObject(response);
                    JSONArray ja = object.getJSONArray("sessions");

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject temp = ja.getJSONObject(i);

                        session.setCourseID(course.getCourseID());
                        session.setSessionID(temp.getInt("sessionid"));
                        session.setSessionNum(temp.getInt("sessionnum"));
                        session.setSessionDate(temp.getString("sessiondate"));
                        session.setWeekDay(temp.getString("weekday"));
                        sessionNumbers.add( "جلسه " + session.getSessionNum());
                        sessions.add(session);

                    }

                    Log.d("TAGTAG", "onResponse: " + sessionNumbers.size());
                    setAdapter();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
    }

    @Override
    public void onItemClick(int position) {
        Intent dIntent = new Intent(DetailCourse.this,SessionActivity.class);
        dIntent.putExtra(KEY_COURSE,sessions.get(position).getCourseID());
        dIntent.putExtra(KEY_SESSION,sessions.get(position).getSessionID());
        dIntent.putExtra(KEY_SESSIONNUM,sessionNumbers.get(position));
        startActivity(dIntent);

    }
}
