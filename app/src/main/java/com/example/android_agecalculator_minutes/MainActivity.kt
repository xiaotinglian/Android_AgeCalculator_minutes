package com.example.android_agecalculator_minutes

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var minutes : TextView? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        minutes = findViewById(R.id.minutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, {
                _, selectedYear, selectedMonth, dayOfMonth ->
            Toast.makeText(this,
                "day was $dayOfMonth,month was ${selectedMonth+1}, year was $selectedYear", Toast.LENGTH_SHORT).show()

            val selectedDate = "$dayOfMonth/${selectedMonth+1}/$selectedYear"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate.time /60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate.time /60000
            val diff = currentDateInMinutes - selectedDateInMinutes

            minutes?.text = diff.toString()
        },
            year, month, day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86500000
        dpd.show()
    }

}