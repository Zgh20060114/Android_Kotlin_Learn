package com.example.activitytest_3_2

import android.app.Activity

// 单例类
object ActivityCollector {
    private val activities = ArrayList<Activity>()
    // ArrayList 是一种可变集合类型，它可以动态地增加或删除元素，并且可以按照插入顺序来访问其中的元素
    // ArrayList<Activity> 表示这个集合中存储的是 Activity 对象
    fun addActivity(activity: Activity){
        activities.add(activity)
    }
    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }
    fun finishAll(){
        for (activity in activities)
        {
            if (!activity.isFinishing)
            {
                activity.finish()
            }
        }
        activities.clear()
    }
}
