package com.example.uiwidgettest

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.uiwidgettest.databinding.ActivityMainBinding
import com.example.uiwidgettest.databinding.FruitItemBinding

class FruitAdapter(activity:Activity, val resourceId: Int, data: List<Fruit>):
    ArrayAdapter<Fruit>(activity, resourceId, data)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        // 手动将视图添加到父视图中，而不是在加载时自动添加

        val view = LayoutInflater.from(context).inflate(resourceId, parent, false)
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
        val fruit= getItem(position)  // 获取当前项的Fruit实例
        if (fruit !=null)
        {
            fruitImage.setImageResource(fruit.imageID)
            fruitName.text=fruit.name
        }
        return view
    }

}