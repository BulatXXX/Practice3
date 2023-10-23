package com.example.practice3


import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.navigation.fragment.NavHostFragment
import com.example.practice3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val TAG = "camX"
    val REQUEST_CODE_PERMISSIONS = 123
    val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        if (!permissonsGranted()) {
            ActivityCompat.requestPermissions(
                this ,
                REQUIRED_PERMISSIONS ,
                REQUEST_CODE_PERMISSIONS
            )
        }
        getExternalFilesDir(null)?.let { DateSaver.createFile(it) }
        setContentView(binding.root)
    }

    private fun permissonsGranted() =
        REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext , it
            ) == PackageManager.PERMISSION_GRANTED
        }

}