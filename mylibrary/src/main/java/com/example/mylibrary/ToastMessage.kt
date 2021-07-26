package com.example.mylibrary

import android.content.Context
import android.widget.Toast

class ToastMessage {
    companion object {
        fun show(c: Context, message: String) {
            Toast.makeText(c, message, Toast.LENGTH_LONG).show()
        }
    }
}