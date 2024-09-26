package com.example.providertest

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.contentValuesOf
import com.example.providertest.ui.theme.ProviderTestTheme

class MainActivity : ComponentActivity() {
    var bookId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addData: Button = findViewById(R.id.addData)
        val queryData: Button = findViewById(R.id.queryData)
        val updateData: Button = findViewById(R.id.updateData)
        val deleteData: Button = findViewById(R.id.deleteData)
        addData.setOnClickListener {
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            val values = contentValuesOf(
                "name" to "A Clash of Kings",
                "author" to "George Martin", "pages" to 1040, "price" to 22.85
            )
            val newUri = contentResolver.insert(uri, values)
            bookId = newUri?.pathSegments?.get(1)
        }
        queryData.setOnClickListener {
            val uri = Uri.parse("content://com.example.databasetest.provider/book")
            contentResolver.query(uri, null, null, null, null)?.apply {
                while (moveToNext()) {
                    val nameIndex = getColumnIndex("name")
                    val authorIndex = getColumnIndex("author")
                    val pagesIndex = getColumnIndex("pages")
                    val priceIndex = getColumnIndex("price")
                    if (nameIndex != -1 && authorIndex != -1 && pagesIndex != -1 && priceIndex != -1) {
                        val name = getString(nameIndex)
                        val author = getString(authorIndex)
                        val pages = getInt(pagesIndex)
                        val price = getDouble(priceIndex)
                        Log.d("MainActivity", "book name is $name")
                        Log.d("MainActivity", "book author is $author")
                        Log.d("MainActivity", "book pages is $pages")
                        Log.d("MainActivity", "book price is $price")
                    }
                }
                close()
            }
        }
        updateData.setOnClickListener {
                bookId?.let {
                    val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                    val values = contentValuesOf(
                        "name" to "A Storm of Swords",
                        "pages" to 1216, "price" to 24.05
                    )
                    contentResolver.update(uri, values, null, null)
                }
            }
        deleteData.setOnClickListener {
                bookId?.let {
                    val uri = Uri.parse("content://com.example.databasetest.provider/book/$it")
                    contentResolver.delete(uri, null, null)
                }
            }

    }
}
