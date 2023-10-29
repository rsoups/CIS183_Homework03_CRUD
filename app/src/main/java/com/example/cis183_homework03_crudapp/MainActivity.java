package com.example.cis183_homework03_crudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_j_addEmployee;
    ListView lv_j_listOfEmployees;
    ArrayList<Employee> listOfEmployees;
    Intent intent;
    EmployeeAdapter adapter;
    Database db;
    Intent updateIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_j_addEmployee = findViewById(R.id.btn_v_addEmployee);
        lv_j_listOfEmployees = findViewById(R.id.lv_v_listOfEmployees);

        listOfEmployees = new ArrayList<Employee>();

        intent = new Intent(MainActivity.this, AddEmployee.class);

        db = new Database(this);
        db.initializeDb();
        listOfEmployees = db.getAllRows();

        updateIntent = new Intent(MainActivity.this, Update.class);

        addEmployeePageButtonEventHandler();
        fillListView();
        //displayEmployees();
        deleteUserEvent();
        updateUserEvent();
    }

    public void addEmployeePageButtonEventHandler()
    {
        btn_j_addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(intent);
            }
        });
    }

    public void fillListView()
    {
        adapter = new EmployeeAdapter(this, listOfEmployees);
        lv_j_listOfEmployees.setAdapter(adapter);
    }

    public void displayEmployees()
    {
        for(int i = 0; i < listOfEmployees.size(); i++)
        {
            Log.d("User", listOfEmployees.get(i).getfName());
        }
    }

    public void deleteUserEvent()
    {
        lv_j_listOfEmployees.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                db.deleteUser(listOfEmployees.get(i).getuName());
                listOfEmployees.remove(i);
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }

    public void updateUserEvent()
    {
        lv_j_listOfEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                updateIntent.putExtra("Employee", listOfEmployees.get(i));
                startActivity(updateIntent);
            }
        });
    }
}