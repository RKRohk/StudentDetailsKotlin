package com.example.newstudent

import androidx.databinding.InverseMethod

object Converter{
    @InverseMethod("doubleToString")
    @JvmStatic fun stringToDouble(value:String):Double?{
        if(value.toDouble() == 0.0)
            return null
        else
            return value.toDouble()
    }
    @JvmStatic fun doubleToString(value:Double):String{
        return value.toString()
    }
}