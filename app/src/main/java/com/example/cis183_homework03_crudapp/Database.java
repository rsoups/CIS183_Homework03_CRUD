package com.example.cis183_homework03_crudapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "Employees.db";
    private static final String TABLE_NAME = "Employees";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create table of employees
        //first name, last name, username, password, email, age
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (firstname TEXT, lastname TEXT, username TEXT PRIMARY KEY NOT NULL, password TEXT, email TEXT, age TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        //delete table if the version number is changed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //create new table
        onCreate(db);
    }

    public boolean initializeDb()
    {
        if(numberofRowsInTable() == 0)
        {
            //read the database and write to it
            SQLiteDatabase db = this.getWritableDatabase();

            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Yuji', 'Itadori', 'Yitadori', 'blackFl@sh', 'yitadori@gmail.com', '15');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Satoru', 'Gojo', 'Sgojo', 'h0llowPurpl3', 'sgojo@yahoo.com', '28');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Megumi', 'Fushiguro', 'Mfushi', 'd1vined0gs', 'mfushiguro@yahoo.com', '15');");
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('Nobara', 'Kugisaki', 'Nkugi', 'h@1rp1n', 'nkugi@gmail.com', '16');");

            db.close();

            return true;
        }
        else {
            return false;
        }
    }

    public int numberofRowsInTable()
    {
        //read the database and store it in db
        SQLiteDatabase db = this.getReadableDatabase();
        //number of records/employees
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        db.close();

        return numRows;
    }

    @SuppressLint("Range")
    public ArrayList<Employee> getAllRows()
    {
        //store the table in this array
        ArrayList<Employee> listOfEmployees = new ArrayList<Employee>();
        //select from the table
        String selectQuery = "SELECT * FROM " + TABLE_NAME + ";";
        //read the database
        SQLiteDatabase db = this.getReadableDatabase();
        //cursor cycles through
        Cursor cursor = db.rawQuery(selectQuery, null);
        //variables to store table info
        String fName;
        String lName;
        String uName;
        String password;
        String email;
        String age;

        if(cursor.moveToFirst())
        {
            do
            {
                //get all the information and store it
                fName = cursor.getString(cursor.getColumnIndex("firstname"));
                lName = cursor.getString(cursor.getColumnIndex("lastname"));
                uName = cursor.getString(cursor.getColumnIndex("username"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                email = cursor.getString(cursor.getColumnIndex("email"));
                age = cursor.getString(cursor.getColumnIndex("age"));
                //add that information to the array
                listOfEmployees.add(new Employee(fName, lName, uName, password, email, age));
            }
            while(cursor.moveToNext());
        }
        db.close();
        return listOfEmployees;
    }
}
