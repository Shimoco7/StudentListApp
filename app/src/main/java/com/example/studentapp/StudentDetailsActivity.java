package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        setStudentDetails();
        registerListeners();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setStudentDetails(); //update
    }


    private void registerListeners() {
        Button editBtn = findViewById(R.id.student_details_edit_btn);
        editBtn.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(),StudentEditActivity.class);
            intent.putExtra("uuid",student.getUuid());
            startActivity(intent);
        });
    }

    private void setStudentDetails() {
        Bundle extras = getIntent().getExtras();
        if(extras==null)
            finish();

        String uuid = extras.getString("uuid");
        student=Model.model.getStudentList()
                .stream()
                .filter(s->s.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        if(student==null)
        {
            finish();
        }
        else{
            TextView nameTv= findViewById(R.id.student_details_name);
            TextView idTv= findViewById(R.id.student_details_id);
            TextView phoneTv= findViewById(R.id.student_details_phone);
            TextView addressTv= findViewById(R.id.student_details_address);
            CheckBox checkBox = findViewById(R.id.student_details_cb);

            nameTv.setText(student.getName());
            idTv.setText(student.getId());
            phoneTv.setText(student.getPhoneNumber());
            addressTv.setText(student.getAddress());
            checkBox.setChecked(student.isChecked());
        }
    }

}