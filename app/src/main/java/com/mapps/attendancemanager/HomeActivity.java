package com.mapps.attendancemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username,password;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    RadioButton StudentButton, FacultyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            startActivity(new Intent(this,AttendanceActivity.class));
            finish();
        }

        StudentButton = (RadioButton) findViewById(R.id.radioStudent);
        FacultyButton = (RadioButton) findViewById(R.id.radioFaculty);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(new PasswordTransformationMethod());

        Button signIn = (Button) findViewById(R.id.signIn);
        Button signUp = (Button) findViewById(R.id.signUp);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.signUp){
            startActivity(new Intent(HomeActivity.this,LoginOption.class));
            finish();
        }

        else if(view.getId() == R.id.signIn) {

            if (username.getText().toString().isEmpty()) {
                username.setError("Field is Required!");
                username.requestFocus();
            } else if (password.getText().toString().isEmpty()) {
                password.setError("Field is Required!");
                password.requestFocus();
            } else {
                final ProgressDialog p = new ProgressDialog(this);
                p.setMessage("Signing in");
                p.show();

                if (StudentButton.isChecked()) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("/Students");
                } else {
                    databaseReference = FirebaseDatabase.getInstance().getReference("/Faculty");
                }

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            Log.d("KEY", postSnapshot.getKey());

                            if (postSnapshot.getKey().equals(username.getText().toString())) {
                                final Student student = postSnapshot.getValue(Student.class);
                                if (student != null) {
                                    firebaseAuth.signInWithEmailAndPassword(student.getEmail(), password.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        if (StudentButton.isChecked()) {
                                                            Intent i = new Intent(HomeActivity.this, AttendanceActivity.class);
                                                            p.cancel();
                                                            startActivity(i);
                                                            finish();
                                                        } else {
                                                            Intent i = new Intent(HomeActivity.this, FacultyActivity.class);
                                                            p.cancel();
                                                            startActivity(i);
                                                            finish();
                                                        }
                                                    } else {
                                                        p.cancel();
                                                        Log.d("Error", task.getException().toString());
                                                        Toast.makeText(HomeActivity.this, "Error in Login", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    p.cancel();
                                    Toast.makeText(HomeActivity.this, "error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        p.cancel();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }
}
