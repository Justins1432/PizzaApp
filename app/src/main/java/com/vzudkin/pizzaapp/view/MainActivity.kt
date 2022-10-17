package com.vzudkin.pizzaapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vzudkin.pizzaapp.R
import com.vzudkin.pizzaapp.view.menu.MenuApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MenuApp::class.java)
        startActivity(intent)
    }
}