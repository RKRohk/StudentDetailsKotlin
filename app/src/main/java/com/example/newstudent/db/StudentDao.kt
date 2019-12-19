package com.example.newstudent.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: Student)

    @Query("SELECT * from student_table ORDER BY id DESC LIMIT 1")
    fun getLastStudent():Student?

    @Query("SELECT * from student_table")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT * from student_table ORDER BY id DESC")
    fun getStudentsList():List<Student>

    @Query("DELETE from student_table")
    fun clear()
}