package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;
import com.example.studentapp.model.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class StudentEditActivity extends AppCompatActivity {
    Student student;
    String currentId;
    EditText nameEt;
    EditText idEt;
    EditText phoneEt;
    EditText addressEt;
    CheckBox checkBox;
    TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);
        setStudentDetails();
        registerListeners();
    }

    private void registerListeners() {
        findViewById(R.id.edit_student_cancel_btn).setOnClickListener(v->{
            finish();
        });

        findViewById(R.id.edit_student_delete_btn).setOnClickListener(v->{
            Model.model.getStudentList().remove(student);
            finish();
        });

        findViewById(R.id.edit_student_save_btn).setOnClickListener(v->{
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();
            String phone = phoneEt.getText().toString();
            String address = addressEt.getText().toString();
            String validate = Utils.validateNameAndId(name,id,phone,address);
            if(!currentId.equals(id)&&Utils.validateExistenceOfId(Model.model.getStudentList(),id)){
                String error = id + " is already registered in our system";
                errorMsg.setText(error);
                return;
            }
            if(!validate.equals("validated")){
                errorMsg.setText(validate);
                return;
            }
            student.setName(name);
            student.setId(id);
            student.setPhoneNumber(phone);
            student.setAddress(address);
            student.setChecked(checkBox.isChecked());
            finish();
        });

        errorMsg = findViewById(R.id.edit_student_error_msg);
        errorMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TimerTask timerTask =new TimerTask() {
                    @Override
                    public void run() {
                        errorMsg.setText("");
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask,(1000*6));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setStudentDetails() {
        Bundle extras = getIntent().getExtras();
        if(extras==null)
            finish();

        String uuid = extras.getString("uuid");
        student= Model.model.getStudentList()
                .stream()
                .filter(s->s.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);

        if(student==null)
            finish();

        currentId = student.getId();

        nameEt = findViewById(R.id.edit_student_name);
        idEt = findViewById(R.id.edit_student_id);
        phoneEt = findViewById(R.id.edit_student_phone);
        addressEt = findViewById(R.id.edit_student_address);
        checkBox = findViewById(R.id.edit_student_cb);

        nameEt.setText(student.getName());
        idEt.setText(student.getId());
        phoneEt.setText(student.getPhoneNumber());
        addressEt.setText(student.getAddress());
        checkBox.setChecked(student.isChecked());
    }

}