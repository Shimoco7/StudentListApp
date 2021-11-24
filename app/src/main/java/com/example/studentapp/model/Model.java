package com.example.studentapp.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public static final Model model = new Model();
    private final List<Student> studentList;

    private Model() {
        studentList = new ArrayList<>();
        studentList.add(new Student("Emil Kollek","201074127"));
        studentList.add(new Student("Shimon Cohen","205723018"));
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

    public void editStudent(String uuid,String name,String id,String phone,String address,Boolean checked){
        Student student = studentList.stream()
                .filter(s->s.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
        if(student!=null){
            student.setId(id);
            student.setName(name);
            student.setAddress(address);
            student.setPhoneNumber(phone);
            student.setChecked(checked);
        }
    }
}
