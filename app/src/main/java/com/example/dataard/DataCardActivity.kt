package com.example.dataard

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DataCardActivity : AppCompatActivity() {

    private lateinit var toolbarDataCard: Toolbar
    private lateinit var imageDataCardTV: ImageView
    private lateinit var firstNameTV: TextView
    private lateinit var lastNameTV: TextView
    private lateinit var dateTV: TextView
    private lateinit var phoneNumberTV: TextView
    private lateinit var ageDataTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_card)

        init()
        createPerson()

    }

    private fun init() {
        toolbarDataCard = findViewById(R.id.toolbar)
        setSupportActionBar(toolbarDataCard)
        title = "Данные о пользователе"

        imageDataCardTV = findViewById(R.id.imageDataCardTV)
        firstNameTV = findViewById(R.id.firstNameTV)
        lastNameTV = findViewById(R.id.lastNameTV)
        dateTV = findViewById(R.id.DateTV)
        phoneNumberTV = findViewById(R.id.phoneNumberTV)
        ageDataTV = findViewById(R.id.ageDataTV)

    }

    private fun createPerson() {
        val person = intent.extras?.getSerializable("person") as Person?
        imageDataCardTV.setImageURI(Uri.parse(person?.image))
        firstNameTV.text = person?.firstName
        lastNameTV.text = person?.lastName
        dateTV.text = person?.date
        phoneNumberTV.text = person?.phoneNumber

        ageDataTV.text = getAgeAndTimeUntilBirthday(person?.date.toString())
    }

    private fun getAgeAndTimeUntilBirthday(dateOfBirth: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val birthDate = LocalDate.parse(dateOfBirth, formatter)
        val currentDate = LocalDate.now()

        val age = Period.between(birthDate, currentDate).years

        var nextBirthday = birthDate.withYear(currentDate.year)
        if (!nextBirthday.isAfter(currentDate)) {
            nextBirthday = nextBirthday.plusYears(1)
        }

        val periodUntilNextBirthday = Period.between(currentDate, nextBirthday)
        val monthsUntilNextBirthday = periodUntilNextBirthday.months
        val daysUntilNextBirthday = periodUntilNextBirthday.days

        return "Возраст: $age лет\nДо следующего дня рождения: $monthsUntilNextBirthday месяцев и $daysUntilNextBirthday дней"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) {
            Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
            finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }












}