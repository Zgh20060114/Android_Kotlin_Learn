package com.example.fragment_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.fragment_6.databinding.LeftFragmentBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val leftFragment = supportFragmentManager.findFragmentById(R.id.leftFrag) as LeftFragment
        // as LeftFragment：将找到的 Fragment 转换为 LeftFragment 类型，以便访问 LeftFragment 中的成员。
        val button1 = leftFragment.view?.findViewById(R.id.button1) ?: Button(this)
        button1.setOnClickListener {
            //replaceFragment(AnotherRightFragment())
        }
        //replaceFragment(RightFragment())
    }

//    private fun replaceFragment(fragment: Fragment)
//    {
//        val fragmentManager = supportFragmentManager  // 获取FragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout,fragment)  // 向容器内添加或替换Fragment，一般使用replace()方法实现
//        transaction.addToBackStack(null)
//        transaction.commit()  // 提交事务
//
//    }
}