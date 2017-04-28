package com.mapps.attendancemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.sort;

public class QuestionView extends AppCompatActivity {

    TextView Subject, Content, Email;
    EditText Comment;
    ListView CommentsList;
    Button Submit;
    String strsub;
    MyCommentsAdapter commentsAdapter;
    List<CommentThread> commentThreadList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        Intent i = getIntent();
        strsub = i.getStringExtra("Subject");
        String strcontent = i.getStringExtra("Content");
        String stremail = i.getStringExtra("Email");

        Subject = (TextView) findViewById(R.id.subject);
        Content = (TextView) findViewById(R.id.content);
        Email = (TextView) findViewById(R.id.email);
        Comment = (EditText) findViewById(R.id.comment);
        CommentsList = (ListView) findViewById(R.id.CommentsList);
        Submit = (Button) findViewById(R.id.submitcomment);

        Subject.setText(strsub);
        Content.setText(strcontent);
        Email.setText(stremail);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/Topics/" + strsub + "/Comments");
        commentThreadList.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    CommentThread Data = postSnapshot.getValue(CommentThread.class);
                    commentThreadList.add(i, Data);
                    i++;
                    commentsAdapter = new MyCommentsAdapter(getApplicationContext(), commentThreadList);
                    CommentsList.setAdapter(commentsAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UID = UUID.randomUUID().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/Topics");
                CommentThread commentThread = new CommentThread(FirebaseAuth.getInstance().getCurrentUser().getEmail(), Comment.getText().toString(), new Date());
                databaseReference.child(strsub).child("Comments").child(UID).setValue(commentThread);
                Comment.setText("");
                finish();
            }
        });
    }

}
