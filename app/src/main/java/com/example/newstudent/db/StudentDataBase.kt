package com.example.newstudent.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class],version = 9,exportSchema = false)
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