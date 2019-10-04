package com.bhupen.dailynews.shared.utils


import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



fun dateConversion (input: String) : String {

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val output = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    var d: Date? = null
    try {
        d = inputFormat.parse(input)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    val formatted = output.format(d)

    return formatted
}
