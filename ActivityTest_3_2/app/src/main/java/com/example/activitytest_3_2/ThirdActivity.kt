package com.example.activitytest_3_2

import android.os.Bundle
import com.example.activitytest_3_2.databinding.FirstLayoutBinding

class ThirdActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(R.layout.third_layout)
    }
}