package com.mapps.attendancemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivityStudent extends AppCompatActivity implements View.OnClickListener {

    EditText password, email;
    Button signUp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(new PasswordTransformationMethod());

        signUp = (Button) findViewById(R.id.RegisterStudent);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (email.getText().toString().isEmpty()) {
            email.setError("Field is Required!");
            email.requestFocus();
        } else if (password.getText().toString().isEmpty()) {
            password.setError("Field is Required!");
            password.requestFocus();
        } else if (password.getText().toString().length() < 4) {
            password.setError("Length should be greater than 4!");
            password.requestFocus();
        }
        else {

            final ProgressDialog p = new ProgressDialog(this);
            p.setMessage("Registering Student");
            p.show();

            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivityStudent.this, "Added Student", Toast.LENGTH_SHORT).show();
                                p.cancel();
                                Intent i = new Intent(RegisterActivityStudent.this, DetailsActivity.class);
                                i.putExtra("Password", password.getText().toString());
                                i.putExtra("Activity", "Student");
                                startActivity(i);
                                finish();
                            } else {
                                p.cancel();
                                Toast.makeText(RegisterActivityStudent.this, "Registration Error! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
