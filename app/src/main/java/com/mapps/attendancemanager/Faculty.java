package com.mapps.attendancemanager;


public class Faculty {

    String Name, Email, Password, Fid;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFid() {
        return Fid;
    }

    public void setFid(String fid) {
        Fid = fid;
    }

    public Faculty(String name, String email, String password, String fid) {

        Name = name;
        Email = email;
        Password = password;
        Fid = fid;
    }

    public Faculty() {
    }
}
