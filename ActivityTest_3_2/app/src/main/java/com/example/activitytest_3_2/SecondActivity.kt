package com.example.activitytest_3_2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.activitytest_3_2.databinding.SecondLayoutBinding

class SecondActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = SecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        val extra = intent.getStringExtra("extra")
        Log.d("SecondActivity","extra is $extra")
         */
        val receivedData = intent.getStringExtra("data_to_second")
        Log.d("SecondActivity","extra is $receivedData")

        binding.button2.setOnClickListener {
            val returnintent = Intent()
            returnintent.putExtra("data_return","hello first")
            setResult(Activity.RESULT_OK,returnintent)
            finish()
        }
    }

    override fun onBackPressed() {
        val returnIntentPressed=Intent()
        returnIntentPressed.putExtra("data_return","hello first by pressed")
        setResult(Activity.RESULT_OK,returnIntentPressed)
        finish()
    }

    companion object {
        fun actionStart(context: Context,data1: String,data2: String)
        {
            val intent=Intent(context,SecondActivity::class.java)
            intent.putExtra("param1", data1)
            intent.putExtra("param2", data2)
            context.startActivity(intent)
        }
    }
}
// 使用：button1.setOnClickListener {
//SecondActivity.actionStart(this, "data1", "data2")
//}
// Activity 是一个表示用户界面的组件，它通常对应应用程序中的一个屏幕或一个交互界面