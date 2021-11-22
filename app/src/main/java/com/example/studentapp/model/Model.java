package com.example.studentapp.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public static final Model model = new Model();
    private List<Student> studentList;

    private Model() {
        studentList = new ArrayList<>();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public void deleteStudent(Student student){
        studentList.remove(student);
    }

    public void editStudent(String id,Student newStudentDetails){
        Student student = studentList.stream()
                .filter(s->s.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(student!=null){
            student.setId(newStudentDetails.getId());
            student.setName(newStudentDetails.getName());
            student.setAddress(newStudentDetails.getAddress());
            student.setPhoneNumber(newStudentDetails.getPhoneNumber());
            student.setChecked(newStudentDetails.isChecked());
        }
    }
}
