package com.example.activitytest_3_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.activitytest_3_2.databinding.FirstLayoutBinding


//Bundle 是一个用于存储和传递数据的对象。它可以包含不同类型的数据，如整数、字符串、布尔值等，以及更复杂的数据结构，如数组和对象。
//FirstActivity 类继承自 AppCompatActivity，这是 Android Jetpack 库提供的一个基类，用于创建兼容较旧版本 Android 系统的 Activity。
class FirstActivity : BaseActivity() {
    // 如果你需要在onCreate()函数之外的地方对控件进行操作，那么就得将binding变量声明成全局变量,下面这个注释
    //private lateinit var binding: FirstLayoutBinding // 将 YourActivityBinding 替换为您实际的绑定类
    //首先调用父类的 onCreate 方法以执行一些必要的初始化操作。
    override fun onCreate(savedInstanceState: Bundle?) {
        //savedInstanceState 参数是一个 Bundle 对象，用于存储 Activity 的状态信息，如屏幕旋转时保存数据等。
        super.onCreate(savedInstanceState)
        /*
        setContentView(R.layout.first_layout)
        val button1: Button =findViewById(R.id.button1)
        button1.setOnClickListener {Toast.makeText(this,"You clicked Button 1",Toast.LENGTH_SHORT).show()
        }
        */
        val binding = FirstLayoutBinding.inflate(layoutInflater)
        //binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 将某布局设置为当前 Activity 的界面布局

        /* binding.button1.setOnClickListener {
            Toast.makeText(this, "You clicked Button 1", Toast.LENGTH_SHORT).show()
        }
        */
        /*
        binding.button1.setOnClickListener {
            finish()
        }

        */
        /*
        //显示Intent
        // 当调用 this.toString() 时，它会返回当前对象的字符串表示形式
        Log.d("FirstActivity", this.toString())
        binding.button1.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java) //调用 java 属性以获取 Java 类型的对象
            // 调用 ::class.java 属性是为了获取 Java 类型的对象，以便与特定的 Java API 一起使用或传递给需要 Java 类型的构造函数。这样做允许我们在 Kotlin 代码中与 Java 代码进行交互。
            startActivity(intent)
        }
        */
        /*
        //隐式Intent
        binding.button1.setOnClickListener{
            val intent = Intent("com.example.activitytest_3_2.ACTION_START")
            startActivity(intent)
        }

         */
        /*
        binding.button1.setOnClickListener{
            // Intent.ACTION_VIEW 表示我们希望查看（View）指定的数据。
            // Intent.ACTION_VIEW 是一个动作常量，表示将要执行的操作是查看某个内容
            // 这个动作通常用于请求系统打开指定的数据，比如打开一个网页、查看一个图片、播放一个视频等
            val intent = Intent(Intent.ACTION_VIEW)
            // Uri.parse() 是一个用于将字符串转换为 Uri 对象的方法
            intent.data = Uri.parse("https://www.baidu.com")
            // 能执行这个操作的有google这个Activity，也可以是符合category条件的其他自定义的activity
            startActivity(intent)
            // 当你调用 startActivity() 启动一个新的 Activity 时，系统会负责创建新的 Activity 实例，.
            // .并在其中调用 onCreate() 方法。
        }
        */
        /*
        binding.button1.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }
         */
        /*
        binding.button1.setOnClickListener {
            val data = "hello second"
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("extra",data)
            startActivity(intent)
        }

         */

        /*
        binding.button1.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivityForResult(intent,1)
        }

         */

        binding.button1.setOnClickListener {

            val data = "Hello SecondActivity"
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("data_to_second", data)
            startSecondActivityForResult.launch(intent)
        }





    }
    // 给当前Activity创建菜单
    // 接受一个 Menu 类型的参数 menu，同时返回一个布尔值。
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true  // 给这个方法返回 true，表示允许创建的菜单显示出来，如果返回了false，创建的菜单将无法显示
    }
    // 定义菜单响应事件
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "You clicked Add",
                Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "You clicked Remove",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }

    // registerForActivityResult(...)：这是一个用于注册 Activity Result 的函数。
    // 它接受一个 ActivityResultContract 对象作为参数，并返回一个 ActivityResultLauncher 对象。
    // ActivityResultContract 是一个接口，用于定义活动启动和结果处理之间的合同。
    private val startSecondActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val returnedData = data?.getStringExtra("data_return")
            Log.d("FirstActivity", "Returned data from SecondActivity: $returnedData")
        }
    }  // Lambda 表达式
}

