package com.example.studentapp.model;

import java.util.UUID;

public class Student {
    private final String uuid = UUID.randomUUID().toString();
    private String name;
    private String id;
    private String phoneNumber;
    private String address;
    private boolean checked;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.phoneNumber = "";
        this.address = "";
        this.checked=false;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
