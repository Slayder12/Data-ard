package com.example.dataard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val GALLARY_REQUEST = 1
    var personUri: Uri? = null

    private lateinit var toolbar: Toolbar
    private lateinit var imageIV: ImageView
    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var dateET: EditText
    private lateinit var phoneNumberET: EditText
    private lateinit var saveBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        imageIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLARY_REQUEST)
        }

        saveBTN.setOnClickListener {
            val image: Uri? = Uri.parse(personUri.toString())
            val person = Person(
                firstNameET.text.toString(),
                lastNameET.text.toString(),
                dateET.text.toString(),
                phoneNumberET.text.toString(),
                image?.toString()
            )
            if (!InputPersonData(this, person).isValidate()) return@setOnClickListener
            val intent = Intent(this, DataCardActivity::class.java)
            intent.putExtra("person", person)
            startActivity(intent)
        }

    }

    private fun init() {
        toolbar = findViewById(R.id.toolbarMain)
        title = ""
        setSupportActionBar(toolbar)

        imageIV = findViewById(R.id.imageTV)
        firstNameET = findViewById(R.id.firstNameET)
        lastNameET = findViewById(R.id.lastNameET)
        dateET = findViewById(R.id.dateET)
        phoneNumberET = findViewById(R.id.phoneNumberET)
        saveBTN = findViewById(R.id.saveBTN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageIV = findViewById(R.id.imageTV)
        when (requestCode) {
            GALLARY_REQUEST -> if (resultCode == RESULT_OK) {
                personUri = data?.data
                imageIV.setImageURI(personUri)
            }
        }
    }
}