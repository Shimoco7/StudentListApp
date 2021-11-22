package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {

    OnItemClickListener itemClickListener;
    OnCheckBoxClickListener checkBoxClickListener;
    List<Student> studentList;
    StudentListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list_main);

        this.studentList = Model.model.getStudentList();

        RecyclerView list = findViewById(R.id.student_list_rv);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));
        registerListeners();

        adapter = new StudentListAdapter(getLayoutInflater(),itemClickListener,checkBoxClickListener,this.studentList);
        list.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    private void registerListeners() {
        Button addNewStudent = findViewById(R.id.student_list_add_btn);
        addNewStudent.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),AddNewStudentActivity.class);
            startActivity(intent);
        });
        itemClickListener = position -> {
            String uuid = Model.model.getStudentList().get(position).getUuid();
            Intent intent = new Intent(getApplicationContext(),StudentDetailsActivity.class);
            intent.putExtra("uuid",uuid);
            startActivity(intent);
        };
        checkBoxClickListener = position -> {
            Student student = Model.model.getStudentList().get(position);
            student.setChecked(!student.isChecked());
        };
    }
}