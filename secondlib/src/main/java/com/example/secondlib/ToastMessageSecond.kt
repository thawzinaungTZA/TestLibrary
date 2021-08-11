package com.example.secondlib

import android.content.Context
import android.widget.Toast
import com.example.secondlib.config.Config
import io.reactivex.disposables.CompositeDisposable

class ToastMessageSecond {
    companion object {
        fun show(c: Context, message: String) {
            val cd = CompositeDisposable()
            Toast.makeText(c, message + ":" + Config.FLAVOR_NAME, Toast.LENGTH_LONG).show()
        }
    }
}