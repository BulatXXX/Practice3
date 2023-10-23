package com.example.practice3

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths

object DateSaver {

    private val dateList = ArrayList<Item>()
    private const val FILE_NAME = "date.txt"
    private const val DIR_NAME = "photos"


    fun saveCurrentDateTimeToFile(filesDir: File) {
        val path = filesDir
        val letDirectory = File(path, "Photos")
        letDirectory.mkdirs()
        val photoFile = File(letDirectory, "date.txt")
        photoFile.appendBytes((getCurrentTime() +" "+ getCurrentDate() +"\n").toByteArray())
    }

    fun createFile(filesDir: File) {
        val path = filesDir
        val letDirectory = File(path, "Photos")
        letDirectory.mkdirs()
        val photoFile = File(letDirectory, "date.txt")
        photoFile.delete()
        photoFile.createNewFile()
    }


    fun readDataFromFile(filesDir:File): List<Item> {
        val list = ArrayList<Item>()
        val path = filesDir
        val letDirectory = File(path, "Photos")
        val photoFile = File(letDirectory, "date.txt")
        val inputStream = FileInputStream(photoFile)
        val bufferedReader =
            BufferedReader(InputStreamReader(inputStream))
// Чтение данных построчно
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            list.add(Item(date = line!!.split(" ")[1], time = line!!.split(" ")[0]))
            Log.e("Boobs", line!!)
        }
// Закрытие потоков
        bufferedReader.close()
        inputStream.close()
        Log.e("Boobs",list.size.toString())

        return list
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