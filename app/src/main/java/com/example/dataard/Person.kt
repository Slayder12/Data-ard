package com.example.dataard

import android.content.Context
import android.widget.Toast
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Person (
    val firstName: String,
    val lastName: String,
    val date: String,
    val phoneNumber: String,
    val image: String?
) : Serializable

class InputPersonData(private val context: Context, private val person: Person) {
    fun isValidate(): Boolean {
        if (person.firstName.isEmpty() && person.lastName.isEmpty() && person.date.isEmpty() && person.phoneNumber.isEmpty()) {
            Toast.makeText(context,  "Введите данные", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.firstName.isEmpty()) {
            Toast.makeText(context, "Введите имя", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.firstName.length !in 2..32) {
            Toast.makeText(context, "Введите корректное имя", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.lastName.isEmpty()) {
            Toast.makeText(context,
                "Введите фамилию", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.lastName.length !in 2..32) {
            Toast.makeText(context, "Введите корректную фамилию", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.date.isEmpty()) {
            Toast.makeText(context,
                "Введите дату рождения", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.date.length != 10 || !isValidDate(person.date)) {
            Toast.makeText(context,
                "Введите корректную дату рождения", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.phoneNumber.isEmpty()) {
            Toast.makeText(context,
                "Введите номер телефона", Toast.LENGTH_SHORT).show()
            return false
        }
        if (person.phoneNumber.length !in 10..15) {
            Toast.makeText(context,
                "Введите корректный номер", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun isValidDate(date: String): Boolean {
        val datePattern = Regex("""\d{2}\.\d{2}\.\d{4}""")
        if (!date.matches(datePattern)) {
            return false
        }
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return try {
            val parsedDate = LocalDate.parse(date, formatter)
            val day = parsedDate.dayOfMonth
            val month = parsedDate.monthValue
            val year = parsedDate.year
            year in 1..9999
        } catch (e: DateTimeParseException) {
            false
        }
    }
}