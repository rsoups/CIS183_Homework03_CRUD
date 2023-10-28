package com.example.cis183_homework03_crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Update extends AppCompatActivity
{
    EditText et_j_u_lName;
    EditText et_j_u_fName;
    TextView tv_j_u_username;
    EditText et_j_u_password;
    EditText et_j_u_email;
    EditText et_j_u_age;
    Button btn_j_u_update;
    Button btn_j_u_back;
    Intent mainActivity;
    Database db;
    Employee employeePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        et_j_u_fName = findViewById(R.id.et_v_u_fName);
        et_j_u_lName = findViewById(R.id.et_v_u_lName);
        tv_j_u_username = findViewById(R.id.tv_v_u_username);
        et_j_u_password = findViewById(R.id.et_v_u_password);
        et_j_u_email = findViewById(R.id.et_v_u_email);
        et_j_u_age = findViewById(R.id.et_v_u_age);
        btn_j_u_update = findViewById(R.id.btn_v_u_update);
        btn_j_u_back = findViewById(R.id.btn_v_u_back);

        mainActivity = new Intent(Update.this, MainActivity.class);

        Intent cameFrom = getIntent();
        employeePassed = (Employee) cameFrom.getSerializableExtra("Employee");

        et_j_u_fName.setText(employeePassed.getfName());
        et_j_u_lName.setText(employeePassed.getlName() + ",");
        tv_j_u_username.setText(employeePassed.getuName());
        et_j_u_password.setText(employeePassed.getPassword());
        et_j_u_email.setText(employeePassed.getEmail());
        et_j_u_age.setText(employeePassed.getAge());

        db = new Database(this);

        updateButtonEvent();
        backButtonEvent();
    }

    public void updateButtonEvent()
    {
        btn_j_u_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeePassed.setlName(et_j_u_lName.getText().toString());
                employeePassed.setfName(et_j_u_fName.getText().toString());
                employeePassed.setPassword(et_j_u_password.getText().toString());
                employeePassed.setEmail(et_j_u_email.getText().toString());
                employeePassed.setAge(et_j_u_age.getText().toString());

                db.updateUser(employeePassed);
                startActivity(mainActivity);
            }
        });
    }

    public void backButtonEvent()
    {
        btn_j_u_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mainActivity);
            }
        });
    }
}