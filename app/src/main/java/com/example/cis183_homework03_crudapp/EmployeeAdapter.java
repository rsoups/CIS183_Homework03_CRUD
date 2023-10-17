package com.example.cis183_homework03_crudapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EmployeeAdapter extends BaseAdapter
{
    Context context;
    ArrayList<Employee> listOfEmployees;

    public EmployeeAdapter(Context c, ArrayList<Employee> e)
    {
        context = c;
        listOfEmployees = e;
    }

    @Override
    public int getCount() {
        return listOfEmployees.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfEmployees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            LayoutInflater mInflator = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.custom_cell, null);
        }

        TextView tv_j_cc_employeeName = view.findViewById(R.id.tv_v_cc_employeeName);
        TextView tv_j_cc_username = view.findViewById(R.id.tv_v_cc_username);

        Employee employee = listOfEmployees.get(i);

        tv_j_cc_employeeName.setText(employee.getfName() + " " + employee.getlName());
        tv_j_cc_username.setText(employee.getuName());

        return view;
    }
}
