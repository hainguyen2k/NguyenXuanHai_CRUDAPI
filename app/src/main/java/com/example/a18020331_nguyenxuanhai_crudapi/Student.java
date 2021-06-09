package com.example.a18020331_nguyenxuanhai_crudapi;

public class Student {
    private int id;
    private String st_name;
    private String student_class;
    private String status;
    private String working_at;
    public Student(int id, String st_name, String student_class, String status, String working_at) {
        this.id = id;
        this.st_name = st_name;
        this.student_class = student_class;
        this.status = status;
        this.working_at = working_at;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSt_name() {
        return st_name;
    }
    public void setSt_name(String st_name) {
        this.st_name = st_name;
    }
    public String getStudent_class() {
        return student_class;
    }
    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getWorking_at() {
        return working_at;
    }
    public void setWorking_at(String working_at) {
        this.working_at = working_at;
    }
}
