package com.example.weatherforcasting.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun View.updateVisibility(visibility: Int) {
    this.visibility = visibility
}
fun getDate(dateString : String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val inDate =  format.parse(dateString) ?: Date()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd / hh.mm a", Locale.ENGLISH)
    return dateFormat.format(inDate.time)

}