package com.example.databasetest

import android.content.ContentValues
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
import com.example.databasetest.ui.theme.DatabaseTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val databaseHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        val createDB: Button = findViewById(R.id.createDatabase)
        createDB.setOnClickListener {
            databaseHelper.writableDatabase  //用于创建和升级数据库
        }

        val addData: Button = findViewById(R.id.addData)
        addData.setOnClickListener {
            val db = databaseHelper.writableDatabase
            val value1 = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, value1)

            val values2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }

        val updateData: Button = findViewById(R.id.updateData)
        updateData.setOnClickListener {
            val db = databaseHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.11)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            Toast.makeText(this, "updata succeeded", Toast.LENGTH_SHORT).show()
        }

        val deleteData: Button = findViewById(R.id.deleteData)
        deleteData.setOnClickListener {
            val db = databaseHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
            Toast.makeText(this, "delete succeeded", Toast.LENGTH_SHORT).show()
        }

        val queryData: Button = findViewById(R.id.queryData)
        queryData.setOnClickListener {
            val db = databaseHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            if (cursor != null && cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex("name")
                val authorIndex = cursor.getColumnIndex("author")
                val pagesIndex = cursor.getColumnIndex("pages")
                val priceIndex = cursor.getColumnIndex("price")

                if (nameIndex >= 0 && authorIndex >= 0 && pagesIndex >= 0 && priceIndex >= 0) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        val name = cursor.getString(nameIndex)
                        val author = cursor.getString(authorIndex)
                        val pages = cursor.getInt(pagesIndex)
                        val price = cursor.getDouble(priceIndex)
                        Log.d("MainActivity", "book name is $name")
                        Log.d("MainActivity", "book author is $author")
                        Log.d("MainActivity", "book pages is $pages")
                        Log.d("MainActivity", "book price is $price")
                    } while (cursor.moveToNext())
                } else {
                    Log.e("MainActivity", "Column index is invalid")
                }
                cursor.close()
            }

        }

        val replaceData: Button = findViewById(R.id.replaceData)
        replaceData.setOnClickListener {
            val db = databaseHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book", null, null)
//                if (true) {
//                    // 手动抛出一个异常，让事务失败
//                    throw NullPointerException()
//                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
                Toast.makeText(this, "endTransaction", Toast.LENGTH_SHORT).show()
            }
        }
    }
}