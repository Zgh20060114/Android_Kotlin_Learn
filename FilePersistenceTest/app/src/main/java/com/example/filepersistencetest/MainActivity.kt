package com.example.filepersistencetest

import android.content.Context
import android.os.Bundle
import android.widget.EditText
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
import com.example.filepersistencetest.databinding.ActivityMainBinding
import com.example.filepersistencetest.ui.theme.FilePersistenceTestTheme
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    //private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        //setContentView(binding.root)

        val inputText= load()
        if (inputText.isNotEmpty())
        {
            val editText: EditText = findViewById(R.id.editText)
            editText.setText(inputText)
            editText.setSelection(inputText.length)
            Toast.makeText(this, "restore succeeded",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val editText: EditText = findViewById(R.id.editText)
        val inputText=editText.text.toString()
        save(inputText)
    }

    private fun save(inputText: String)
    {
        try {
            val output= openFileOutput("data",Context.MODE_PRIVATE)
            val writer= BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inputText)
            }
        }
        catch (e:IOException)
        {
            e.printStackTrace()
        }
    }

    private fun load(): String
    {
       val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        }catch (e: IOException)
        {
            e.printStackTrace()
        }
        return content.toString()
    }
}
