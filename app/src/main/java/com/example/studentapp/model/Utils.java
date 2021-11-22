package com.example.studentapp.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String validateNameAndId(String name,String id,String phone,String address) {
        String regex = "^[a-z ,.'-]+$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if(name.length()>=20||!matcher.find()){
            return "Name is mandatory and must only contain letters, valid symbols and be shorter than 20 characters";
        }
        regex= "^[0-9]{8,9}$";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(id);
        if(!matcher.find()){
            return "ID is mandatory and must contain 8-9 digits only";
        }
        if(phone.length()>0){
            regex= "^05[023489]\\d{7}$";
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(phone);
            if(!matcher.find()){
                return "Phone Number is not an Israeli valid number";
            }
        }
        if(address.length()>0){
            if(address.length()<5||address.length()>30){
                return "Address length must contain 5-30 characters";
            }
            regex= "^[A-za-z0-9',.\\s]*$";
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(address);
            if(!matcher.find()){
                return "Address is in invalid format, please use letters, digits, commas and dots only";
            }
        }
        return "validated";
    }

    public static boolean validateExistenceOfId(List<Student> studentList, String id) {
        if(studentList==null||id==null)
            return false;

        Student student =
                studentList.stream()
                .filter(s->s.getId().equals(id))
                .findFirst()
                .orElse(null);

        return student != null;
    }
}
