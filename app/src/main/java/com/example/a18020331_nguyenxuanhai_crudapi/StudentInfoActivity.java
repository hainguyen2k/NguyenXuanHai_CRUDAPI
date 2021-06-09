package com.example.a18020331_nguyenxuanhai_crudapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class StudentInfoActivity extends AppCompatActivity {
    LinkedList<Student> linkedList = new LinkedList<>();
    RecycleViewAdapter adapter;
    RecyclerView recyclerView;
    Button btn_back;
    String url = "https://60ade70f80a61f0017331e38.mockapi.io/Student";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        recyclerView = findViewById(R.id.recyclerview);
        btn_back = findViewById(R.id.btn_return);
        getDataFromJsonAPI();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentInfoActivity.this, ManagerActivity.class));
            }
        });
    }

    private void getDataFromJsonAPI() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        int id = object.getInt("id");
                        String st_name = object.getString("st_name");
                        String class_student = object.getString("class");
                        String status = object.getString("status");
                        String working_at = object.getString("working_at");
                        linkedList.add(new Student(id, st_name, class_student, status,working_at ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new RecycleViewAdapter(linkedList, StudentInfoActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(StudentInfoActivity.this, 1));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentInfoActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}