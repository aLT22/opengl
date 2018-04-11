package com.bytebuilding.opengl.utils

import android.content.Context
import android.content.res.Resources
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Turkin A. on 30.03.2018.
 */
fun readFromRaw(context: Context,
                resId: Int): String {
    val stringBuilder = StringBuilder()
    try {
        var bufferedReader: BufferedReader? = null
        try {
            val inputStream = context.resources.openRawResource(resId)
            bufferedReader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (bufferedReader.readLine() != null) {
                line = bufferedReader.readLine()
                stringBuilder.append(line)
                stringBuilder.append("\r\n")
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close()
            }
        }
    } catch (ioex: IOException) {
        ioex.printStackTrace()
    } catch (nfex: Resources.NotFoundException) {
        nfex.printStackTrace()
    }

    return stringBuilder.toString()
}