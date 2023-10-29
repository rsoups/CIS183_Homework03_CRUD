package com.example.cis183_homework03_crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddEmployee extends AppCompatActivity {

    EditText et_j_fName;
    EditText et_j_lName;
    EditText et_j_uName;
    EditText et_j_password;
    EditText et_j_email;
    EditText et_j_age;
    Button btn_j_ae_addEmployee;
    Button btn_j_ae_back;
    Intent intent;
    ArrayList<Employee> listOfEmployees;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        et_j_fName = findViewById(R.id.et_v_fName);
        et_j_lName = findViewById(R.id.et_v_lName);
        et_j_uName = findViewById(R.id.et_v_uName);
        et_j_password = findViewById(R.id.et_v_password);
        et_j_email = findViewById(R.id.et_v_email);
        et_j_age = findViewById(R.id.et_v_age);
        btn_j_ae_addEmployee = findViewById(R.id.btn_v_ae_addEmployee);
        btn_j_ae_back = findViewById(R.id.btn_v_ae_back);

        listOfEmployees = new ArrayList<Employee>();
        //connection to main page
        intent = new Intent(AddEmployee.this, MainActivity.class);
        //database connection
        db = new Database(this);

        addEmployeeButtonHandler();
        backButtonHandler();
    }

    public void addEmployeeButtonHandler()
    {
        btn_j_ae_addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f = et_j_fName.getText().toString();
                String l = et_j_lName.getText().toString();
                String u = et_j_uName.getText().toString();
                String p = et_j_password.getText().toString();
                String e = et_j_email.getText().toString();
                String a = et_j_age.getText().toString();

                Employee employee = new Employee(f, l, u, p, e, a);
                //add new employee to arraylist and database
                listOfEmployees.add(employee);
                db.addNewUser(employee);

                et_j_fName.setText("");
                et_j_lName.setText("");
                et_j_uName.setText("");
                et_j_password.setText("");
                et_j_email.setText("");
                et_j_age.setText("");
            }
        });
    }

    public void backButtonHandler()
    {
        btn_j_ae_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intent);
            }
        });
    }
}