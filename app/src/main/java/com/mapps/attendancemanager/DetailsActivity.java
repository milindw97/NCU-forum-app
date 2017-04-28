package com.mapps.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    EditText name, Id;
    Button Continue;
    Faculty faculty;
    Student student;
    String Password, Activity;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        firebaseAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        Id = (EditText) findViewById(R.id.Id);
        textView = (TextView) findViewById(R.id.textView);

        Intent j = getIntent();
        Activity = j.getStringExtra("Activity");
        Password = j.getStringExtra("Password");

        if(Activity.equals("Student")){
            Id.setHint("Student Roll Number");
            textView.setText("Student Details");
        }
        else {
            Id.setHint("Faculty ID");
            textView.setText("Faculty Details");
        }

        Continue = (Button) findViewById(R.id.Continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {
                    name.setError("Field is Required!");
                    name.requestFocus();
                } else if (Id.getText().toString().isEmpty()) {
                    Id.setError("Field is Required!");
                    Id.requestFocus();
                } else {

                    final String Name = name.getText().toString();

                    if (Activity.equals("Student")) {
                        databaseReference = FirebaseDatabase.getInstance().getReference("/Students");
                        student = new Student(name.getText().toString(), firebaseAuth.getCurrentUser().getEmail(), Password, Id.getText().toString());
                        databaseReference.child(Id.getText().toString()).setValue(student);
                    } else {
                        databaseReference = FirebaseDatabase.getInstance().getReference("/Faculty");
                        faculty = new Faculty(name.getText().toString(), firebaseAuth.getCurrentUser().getEmail(), Password, Id.getText().toString());
                        databaseReference.child(Id.getText().toString()).setValue(faculty);
                    }

                    UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder().setDisplayName(Name).build();

                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    firebaseUser.updateProfile(profileUpdate);

                    if(Activity.equals("Student")){
                        Intent i = new Intent(DetailsActivity.this, AttendanceActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Intent i = new Intent(DetailsActivity.this, FacultyActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }
}
