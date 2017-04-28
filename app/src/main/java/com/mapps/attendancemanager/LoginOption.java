package com.mapps.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginOption extends AppCompatActivity implements View.OnClickListener {

    Button student,faculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_option);

        student = (Button)findViewById(R.id.student);
        faculty = (Button)findViewById(R.id.faculty);

        student.setOnClickListener(this);
        faculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.student)
        {
            startActivity(new Intent(LoginOption.this,RegisterActivityStudent.class));
        }

        else if(view.getId() == R.id.faculty)
        {
            startActivity(new Intent(LoginOption.this,RegisterActivity.class));
        }
    }
}
