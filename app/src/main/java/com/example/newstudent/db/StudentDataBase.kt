package com.example.newstudent.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

<<<<<<< HEAD
@Database(entities = [Student::class],version = 12,exportSchema = false)
=======
@Database(entities = [Student::class],version = 1,exportSchema = false)
>>>>>>> parent of 4100084... Fixed issues with room. I can get all the students as well as the last entered student. Recyclerview implementation pending
abstract class StudentDataBase : RoomDatabase(){

    abstract val studentDao:StudentDao

    companion object{
        @Volatile
        private var INSTANCE:StudentDataBase? = null

        fun getInstance(context: Context):StudentDataBase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDataBase::class.java,
                        "student_details_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}