package com.example.testmultithreading

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val APP_PREFERENCES = "mysettings"
        const val APP_PREFERENCES_NAME = "Nickname"
        const val APP_PREFERENCES_AGE = "Age"
    }

    private lateinit var mSettings: SharedPreferences
    private lateinit var setName: EditText
    private lateinit var setAge: EditText
    private lateinit var saveButton: Button
    private lateinit var getButton: Button
    private lateinit var getName: TextView
    private lateinit var getAge: TextView
    private lateinit var deleteButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        setName = findViewById(R.id.set_name)
        setAge = findViewById(R.id.set_age)
        saveButton = findViewById(R.id.save)
        getButton = findViewById(R.id.get_button)
        getName = findViewById(R.id.get_name)
        getAge = findViewById(R.id.get_age)
        deleteButton = findViewById(R.id.delete_button)

        saveButton.setOnClickListener {

            val name = setName.text.toString()
            val age = setAge.text.toString()
            val editor = mSettings.edit()

            editor.putString(APP_PREFERENCES_NAME, name)
            editor.putString(APP_PREFERENCES_AGE, age)
            editor.apply()

            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()

        }

        getButton.setOnClickListener {

            if (mSettings.contains(APP_PREFERENCES_NAME)) {
                getName.text = mSettings.getString(APP_PREFERENCES_NAME, "")
            } else {
                getName.text = "not name"
            }

            if (mSettings.contains(APP_PREFERENCES_AGE)) {
                getAge.text = mSettings.getString(APP_PREFERENCES_AGE, "").toString()
            } else {
                getAge.text = "not age"
            }

        }

        deleteButton.setOnClickListener {

            mSettings
                .edit()
                .clear()
                .apply()

        }

    }

}