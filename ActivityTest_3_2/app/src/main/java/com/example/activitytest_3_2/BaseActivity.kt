package com.example.activitytest_3_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity",javaClass.simpleName)
    }
}
// javaClass 是 Kotlin 中的一个属性，它返回当前对象所属的类的 KClass 对象。然后，通过调用 simpleName 属性，可以获取该类的简单名称。
// 所以，javaClass.simpleName 返回的是当前对象所属的类的简单名称。