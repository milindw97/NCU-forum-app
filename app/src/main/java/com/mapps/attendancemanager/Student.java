package com.mapps.attendancemanager;


import java.util.Date;

public class Student {

    String Name, Email, Password, Roll;

    public Student() {
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setRoll(String roll) {
        Roll = roll;
    }

    public Student(String name, String email, String password, String roll) {
        Name = name;
        Email = email;
        Password = password;
        Roll = roll;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getRoll() {
        return Roll;
    }
}

class ForumData {

    public ForumData() {
    }

    String Subject, Content, Name;

    public ForumData(String subject, String content, String name) {
        Subject = subject;
        Content = content;
        Name = name;
    }

    public String getSubject() {

        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

class Appointment {
    String StudentName, Rollno, FacultyName, Subject;

    public Appointment() {
    }

    public Appointment(String studentName, String rollno, String facultyName, String subject) {

        StudentName = studentName;
        Rollno = rollno;
        FacultyName = facultyName;
        Subject = subject;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getRollno() {
        return Rollno;
    }

    public void setRollno(String rollno) {
        Rollno = rollno;
    }

    public String getFacultyName() {
        return FacultyName;
    }

    public void setFacultyName(String facultyName) {
        FacultyName = facultyName;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}

class CommentThread {

    String Name, Comment;
    Date date;

    public CommentThread() {
    }

    public CommentThread(String name, String comment, Date date) {
        Name = name;
        Comment = comment;
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}