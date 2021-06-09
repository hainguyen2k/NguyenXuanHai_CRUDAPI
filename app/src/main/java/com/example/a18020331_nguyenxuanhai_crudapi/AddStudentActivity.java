package com.example.a18020331_nguyenxuanhai_crudapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {
    Button btn_create, btn_return;
    EditText edt_stname, edt_class, edt_status, edt_workingat;
    String url = "https://60ade70f80a61f0017331e38.mockapi.io/Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        btn_create = findViewById(R.id.btn_create);
        btn_return = findViewById(R.id.btn_return);
        edt_stname = findViewById(R.id.edit_name);
        edt_class = findViewById(R.id.edit_class);
        edt_status = findViewById(R.id.edit_status);
        edt_workingat = findViewById(R.id.edit_workingat);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String st_name = intent.getStringExtra("st_name");
        String student_class = intent.getStringExtra("class");
        String status = intent.getStringExtra("status");
        String working_at = intent.getStringExtra("working_at");

        if (id != 0) {
            edt_stname.setText(st_name);
            edt_class.setText(student_class);
            edt_status.setText(status);
            edt_workingat.setText(working_at);
        }
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id != 0) {
                    putDataToJsonAPI(url, id);
                } else
                    postDataToJsonAPI(url);
            }
        });
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddStudentActivity.this, StudentInfoActivity.class));
            }
        });
    }

    private void postDataToJsonAPI(String url) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddStudentActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudentActivity.this, "Lỗi khi thêm!", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("st_name", edt_stname.getText().toString() + "");
                params.put("class", edt_class.getText().toString() + "");
                params.put("status", edt_status.getText().toString() + "");
                params.put("working_at", edt_workingat.getText().toString() + "");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void putDataToJsonAPI(String url, int id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT, url + '/' + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(AddStudentActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddStudentActivity.this, "Lỗi!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("st_name", edt_stname.getText().toString() + "");
                params.put("class", edt_class.getText().toString() + "");
                params.put("status", edt_status.getText().toString() + "");
                params.put("working_at", edt_workingat.getText().toString() + "");

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}