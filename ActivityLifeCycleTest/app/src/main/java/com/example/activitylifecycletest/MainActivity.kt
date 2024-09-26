package com.example.activitylifecycletest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.activitylifecycletest.databinding.MainLayoutBinding
import com.example.activitylifecycletest.ui.theme.ActivityLifeCycleTestTheme

class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(tag, "onCreate")
        val binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState !=null)
        {
            val value=savedInstanceState.getString("data_key")
            Log.d(tag,"data_value is $value")
        }

        binding.startNormalActivity.setOnClickListener{
            startActivity(Intent(this,NormalActivity::class.java))
        }
        binding.startDialogActivity.setOnClickListener {
            startActivity(Intent(this,DialogActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("data_key","data_value:OK")
    }
}
