package com.example.testlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.ToastMessage
import com.example.secondlib.ToastMessageSecond

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToastMessageSecond.show(this, "Hello")
    }
}