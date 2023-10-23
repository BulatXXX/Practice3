package com.example.practice3

import android.util.Log
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object DateSaver {

    private val dateList = ArrayList<Item>()
    private const val FILE_NAME = "date.txt"
    private const val DIR_NAME = "photos"
    init {
        createFile()
    }

    fun saveCurrentDateTimeToFile() {
        getCurrentTime()
        getCurrentDate()
        dateList.add(Item(time = getCurrentTime() , date = getCurrentDate()))
    }

    private fun createFile() {

    }


    fun readDataFromFile(): List<Item> {
        return dateList
    }

    // Функция для получения текущей даты
    private fun getCurrentDate(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentTime = java.util.Date(currentTimeMillis)
        val dateFormat = java.text.SimpleDateFormat("dd.MM.yyyy")
        return dateFormat.format(currentTime)
    }

    // Функция для получения текущего времени по миллисекундам
    private fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val currentTime = java.util.Date(currentTimeMillis)
        val timeFormat = java.text.SimpleDateFormat("HH:mm:ss")
        return timeFormat.format(currentTime)
    }
}