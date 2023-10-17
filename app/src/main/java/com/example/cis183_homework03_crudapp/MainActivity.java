package com.example.cis183_homework03_crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button btn_j_addEmployee;
    ListView lv_j_listOfEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_j_addEmployee = findViewById(R.id.btn_v_addEmployee);
        lv_j_listOfEmployees = findViewById(R.id.lv_v_listOfEmployees);

        addEmployeePageButtonEventHandler();
    }

    public void addEmployeePageButtonEventHandler()
    {
        btn_j_addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddEmployee.class);
                startActivity(intent);
            }
        });
    }
}