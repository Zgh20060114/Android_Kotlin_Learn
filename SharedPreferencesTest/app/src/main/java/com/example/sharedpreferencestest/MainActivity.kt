package com.example.sharedpreferencestest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharedpreferencestest.ui.theme.SharedPreferencesTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val saveButton :Button = findViewById(R.id.saveButton)
        val restoreButton : Button = findViewById(R.id.restoreButton)
        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("name","Tom")
            editor.putInt("age",28)
            editor.putBoolean("married",false)
            editor.apply()
            Toast.makeText(this, "SaveOk", Toast.LENGTH_SHORT).show()
        }
        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("MainActivity", "name is $name")
            Log.d("MainActivity", "age is $age")
            Log.d("MainActivity", "married is $married")
            Toast.makeText(this, "RestoreOk", Toast.LENGTH_SHORT).show()
        }

    }
}
