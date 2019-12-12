package com.example.newstudent.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student (
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    @ColumnInfo(name = "roll_no")
    var rollNo:Int?,
    @ColumnInfo(name = "cgpa")
    var cgpa:Double?,
    @ColumnInfo(name = "name")
    var name:String?
)