package com.example.a18020331_nguyenxuanhai_crudapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>  {

    private LinkedList<Student> linkedList;
    private LayoutInflater inflater;
    private Context context;
    public RecycleViewAdapter(LinkedList<Student> linkedList, Context context) {
        this.linkedList = linkedList;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.student, parent, false);
        return new RecycleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        Student student = linkedList.get(position);
        holder.tv_id.setText(String.valueOf(student.getId()));
        holder.tv_stname.setText(student.getSt_name());
        holder.tv_class.setText(student.getStudent_class());
        holder.tv_status.setText(student.getStatus());
        holder.tv_workingat.setText(student.getWorking_at());
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {
        private RecycleViewAdapter adapter;
        public TextView tv_id, tv_stname, tv_class, tv_status, tv_workingat;
        public RecycleViewHolder(@NonNull View view, RecycleViewAdapter recycleViewAdapter) {
            super(view);
            tv_id = view.findViewById(R.id.tv_id);
            tv_stname = view.findViewById(R.id.tv_stname);
            tv_class = view.findViewById(R.id.tv_class);
            tv_status = view.findViewById(R.id.tv_status);
            tv_workingat = view.findViewById(R.id.tv_workingat);
            this.adapter = adapter;

            Button btn_del = view.findViewById(R.id.btn_del);
            Button btn_update = view.findViewById(R.id.btn_update);
            String url = "https://60ade70f80a61f0017331e38.mockapi.io/Student";

            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Student element = linkedList.get(position);
                    DeleteDataToJsonAPI(url, element.getId());
                }
            });
            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    Student element = linkedList.get(position);
                    Intent intent = new Intent(context, AddStudentActivity.class);

                    intent.putExtra("id", element.getId());
                    intent.putExtra("st_name", element.getSt_name());
                    intent.putExtra("class", element.getStudent_class());
                    intent.putExtra("status", element.getStatus());
                    intent.putExtra("working_at", element.getWorking_at());
                    context.startActivity(intent);
                }
            });
        }
        private void DeleteDataToJsonAPI(String url, int id) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    linkedList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }
}
