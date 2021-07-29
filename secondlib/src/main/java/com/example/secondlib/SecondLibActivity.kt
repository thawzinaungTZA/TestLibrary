package com.example.secondlib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mylibrary.ToastMessage

class SecondLibActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_lib)
        ToastMessage.show(this, "Hello!")
    }
}