package com.example.practice3

import android.util.Log

object DateSaver {
    private val dateList = ArrayList<Item>()
    fun saveCurrentDateTimeToFile() {
        getCurrentTime()
        getCurrentDate()
        dateList.add(Item(time = getCurrentTime() , date = getCurrentDate()))
    }

    fun readDataFromFile(): List<Item> {
        return dateList
    }

    // Функция для получения текущей даты
    private fun getCurrentDate(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentTime = java.util.Date(currentTimeMillis)
        val dateFormat = java.text.SimpleDateFormat("dd.MM.yyyy")
        Log.e("Boobs",dateFormat.format(currentTime))
        return dateFormat.format(currentTime)
    }

    // Функция для получения текущего времени по миллисекундам
    private fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentTime = java.util.Date(currentTimeMillis)
        val timeFormat = java.text.SimpleDateFormat("HH:mm:ss")
        Log.e("Boobs",timeFormat.format(currentTime))
        return timeFormat.format(currentTime)
    }
}