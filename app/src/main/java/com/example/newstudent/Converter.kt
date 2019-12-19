package com.example.newstudent

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.example.newstudent.db.Student
import org.w3c.dom.Text

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
@BindingAdapter("toName")
fun TextView.setName(item:Student){
    item?.let {
        text = item.name
    }
}
@BindingAdapter("toRollNo")
fun TextView.setRollNo(item:Student){
    item?.let {
        text = item.rollNo.toString()
    }
}
@BindingAdapter("toCGPA")
fun TextView.setCGPA(item:Student){
    item?.let {
        text = item.cgpa.toString()
    }
}