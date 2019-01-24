package com.zhuzichu.mvvm

import android.widget.Toast

fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(App.context, message, duration).show()