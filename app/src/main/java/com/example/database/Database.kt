package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context : Context?) : SQLiteOpenHelper(context,"MyData.db",null,1)  {

    var context = context

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT, address TEXT)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addData(name:String,surname:String,address:String){
        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("surname",surname)
        values.put("address",address)
        db.insert("students",null,values)
    }

    fun showData() : ArrayList<StudentModel> {
        var stdList = ArrayList<StudentModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM student"
        var cursor:Cursor = db.rawQuery(sql,null)
        cursor.moveToFirst()

        for (i in 0..cursor.count-1){
            var id = cursor.getInt(0)
            var name = cursor.getString(1)
            var surname = cursor.getString(2)
            var address = cursor.getString(3)

            var std = StudentModel(id ,name,surname,address)
            stdList.add(std)
            cursor.moveToNext()
        }
        return stdList
    }

    fun updateData(name:String,surname: String,address: String,id:Int){
        var db = writableDatabase
        var values = ContentValues()
        values.put("name",name)
        values.put("surname",surname)
        values.put("address",address)
        db.update("student",values,"id = $id",null);
    }
}