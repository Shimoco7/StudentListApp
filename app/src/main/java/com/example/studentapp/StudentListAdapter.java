package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.model.Student;

import java.util.List;

public class StudentListAdapter  extends RecyclerView.Adapter<StudentListViewHolder> {
    LayoutInflater layoutInflater;
    OnItemClickListener itemClickListener;
    OnCheckBoxClickListener checkBoxClickListener;
    List<Student> studentData;

    public StudentListAdapter(LayoutInflater layoutInflater, OnItemClickListener itemClickListener, OnCheckBoxClickListener checkBoxClickListener, List<Student> studentData) {
        this.layoutInflater = layoutInflater;
        this.itemClickListener = itemClickListener;
        this.checkBoxClickListener = checkBoxClickListener;
        this.studentData = studentData;
    }


    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.student_list_row,parent,false);
        return new StudentListViewHolder(view,itemClickListener,checkBoxClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int position) {
        assert studentData!=null;
        Student student = studentData.get(position);
        holder.nameTv.setText(student.getName());
        holder.idTv.setText(student.getId());
        holder.cb.setChecked(student.isChecked());
    }

    @Override
    public int getItemCount() {
        assert studentData!=null;
        return studentData.size();
    }
}
