package com.mapps.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewTopic extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button submit;
    EditText subject, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_topic);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final Intent i = getIntent();
        databaseReference = FirebaseDatabase.getInstance().getReference("/Topics");
        subject = (EditText) findViewById(R.id.subject);
        content = (EditText) findViewById(R.id.content);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForumData forumData = new ForumData(subject.getText().toString(),content.getText().toString(),firebaseAuth.getCurrentUser().getEmail());
                databaseReference.child(subject.getText().toString()).setValue(forumData);
                startActivity(new Intent(NewTopic.this,AttendanceActivity.class));
                finish();
            }
        });
    }
}

