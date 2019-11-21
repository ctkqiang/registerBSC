package com.johnmelodyme.BSC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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

import java.util.List;

public class REGISTER extends AppCompatActivity {

    FirebaseAuth aa;
    EditText email_register;
    EditText password_register;
    EditText name;
    Button REGISTER;
    realtimedata rd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aa = FirebaseAuth.getInstance();

        name = findViewById(R.id.Name);
        email_register = findViewById(R.id.email);
        password_register = findViewById(R.id.password);
        REGISTER = findViewById(R.id.register);

        REGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email;
                String password;
                email = email_register.getText().toString().trim();
                password = password_register.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter An Email ",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(),
                            "Please Enter Password ",
                            Toast.LENGTH_SHORT)
                            .show();
                }
                if(password.length() < 6 ){
                    Toast.makeText(getApplicationContext(),
                            "The Password Must Be 6 Letters Long",
                            Toast.LENGTH_SHORT)
                            .show();
                } else if (!(email.isEmpty() && password.isEmpty())){
                    aa.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(REGISTER.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(REGISTER.this,
                                                "SERVER :: \"SUCCESS\" ",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                        finish();
                                    } else {
                                        Toast.makeText(REGISTER.this,
                                                "SERVER :: \"FAILED\" ",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                        Toast.makeText(REGISTER.this,
                                                "SERVER :: \"CONTACT THE DEVELOPER <3 \" ",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }
                            });
                }
                rd = new realtimedata();
                rd.setNAME(name.getText().toString());
                rd.setEMAIL(email_register.getText().toString());
                rd.setPASSWORD(password_register.getText().toString());

                new theDataManipulator().addData(rd, new theDataManipulator.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<realtimedata> BSC_RES, List<String> keys) {
                    }

                    @Override
                    public void DataIsInserted() {
                        String insertion;
                        insertion = "Saved to server";
                        Toast.makeText(getApplicationContext(),
                                insertion,
                                Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void DataIsUpdated() {
                    }

                    @Override
                    public void DataIsDeleted() {
                    }
                });
            }
        });
    }
    // Pressed Twice to exit ::
    boolean doubleBackToExitPressedOne = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOne) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOne = true;
        Toast.makeText(this, "Press twice to exit",
                Toast.LENGTH_SHORT)
                .show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOne = false;
            }
        }, 2000);
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}