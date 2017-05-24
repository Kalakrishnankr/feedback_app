package com.example.raghulsn.feedback_new.model;

/**
 * Created by kalakrishnan.kr on 20/10/16.
 */
public class Customer {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String age;
    private String sex;
    private String date;

    public Customer(){
        this.id = 0;
        this.name = "";
        this.email = "";
        this.phone = "";
        this.age = "";
        this.sex = "";
        this.date = "";
    }

    public void gender(String a,String s) {
        this.age = a;
        this.sex = s;

    }
    public void contact(String name,String mail,String phon)
    {
        this.name=name;
        this.email=mail;
        this.phone=phon;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAge() {
        return age;
    }
    public String getSex()
    {
        return sex;
    }

    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


   /* public Customer(String name,String email,String phone,String age,String sex,String date)
    {
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.age=age;
        this.sex=sex;
        this.date=date;

    }*/



}