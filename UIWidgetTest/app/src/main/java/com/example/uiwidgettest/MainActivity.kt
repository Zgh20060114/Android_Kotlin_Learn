package com.example.uiwidgettest

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
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
import com.example.uiwidgettest.databinding.ActivityMainBinding
import com.example.uiwidgettest.ui.theme.UIWidgetTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val inputText = binding.editText.text.toString()
            Toast.makeText(this,inputText,Toast.LENGTH_SHORT).show()
        }
        binding.button2.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.img_pandas)
        }
        binding.button3.setOnClickListener {
            /*
            if (binding.progressBar.visibility == View.VISIBLE)
            {
                binding.progressBar.visibility = View.GONE
            }
            else
            {
                binding.progressBar.visibility = View.VISIBLE
            }
            */
            binding.progressBar.progress +=10
        }
        binding.button4.setOnClickListener {
            // AlertDialog.Builder 是一个用于构建对话框的构建器类
            // AlertDialog.Builder(this) 创建了一个 AlertDialog.Builder 对象，并通过 apply 函数应用了一些配置
            AlertDialog.Builder(this).apply {
                setTitle("This is Dialog")
                setMessage("Important")
                // 设置对话框为不可取消状态，即用户点击对话框外部或返回键时不会关闭对话框
                setCancelable(false)
                setPositiveButton("OK")
                {
                    dialog,which ->
                }
                setNegativeButton("Cancel")
                {
                    dialog,which ->
                }
                show()

            }
        }
    }
}
