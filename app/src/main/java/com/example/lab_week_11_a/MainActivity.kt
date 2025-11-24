package com.example.lab_week_11_a

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the settings store from the application
        val settingsStore = (application as SettingsApplication).settingsStore

        // Create the ViewModel using a Factory
        val settingsViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(settingsStore) as T
            }
        })[SettingsViewModel::class.java]

        // Observe LiveData to update UI
        settingsViewModel.textLiveData.observe(this) {
            findViewById<TextView>(R.id.activity_main_text_view).text = it
        }

        // Save data on button click
        findViewById<Button>(R.id.activity_main_button).setOnClickListener {
            settingsViewModel.saveText(
                findViewById<EditText>(R.id.activity_main_edit_text).text.toString()
            )
        }
    }
}