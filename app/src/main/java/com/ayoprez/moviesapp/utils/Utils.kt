package com.ayoprez.moviesapp.utils

import android.content.Context
import java.io.IOException
import java.io.InputStreamReader

class Utils {

    fun readStringFromJsonFile(context: Context, fileName: String): String {
        try {
            val inputStream = context.assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            throw e
        }
    }
}