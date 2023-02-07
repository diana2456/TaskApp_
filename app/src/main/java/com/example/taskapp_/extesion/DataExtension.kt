package com.example.taskapp_.extesion

import java.text.SimpleDateFormat
import java.util.Date

fun Long.convertTime():String{
    val date = Date(this)
    val format  = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}