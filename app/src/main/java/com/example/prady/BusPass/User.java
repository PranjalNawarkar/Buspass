package com.example.prady.BusPass;


public class User {
    int id;
    String name, dob, phone, adhar, city, username, pass, value;

    public User(int id,String name,String dob,String phone,String adhar,String city, String username, String pass, String value)
    {
        this.id=id;
        this.name=name;
        this.dob=dob;
        this.phone=phone;
        this.adhar=adhar;
        this.city=city;
        this.username=username;
        this.pass=pass;
        this.value=value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdhar() {
        return adhar;
    }

    public String getCity() {
        return city;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getValue() {
        return value;
    }
}
