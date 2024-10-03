package com.demo.wallpaper_app

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var grid: GridView
    lateinit var image : ImageView

    var walllist = ArrayList<Int>()

    lateinit var Myname: TextView
    lateinit var btnid: Button

//    lateinit var sp: SharedPreferences   // Get
//    lateinit var editior: SharedPreferences.Editor  // set

//    var value = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        grid = findViewById(R.id.grid)

//        sp = getSharedPreferences("hdhd", 0)
//        editior = sp.edit()

//        value = sp.getInt("level",12)


//        Myname = findViewById(R.id.txtid)
//        btnid = findViewById(R.id.btnid)

//        Myname.setText("$value")
//        btnid.setOnClickListener {
//            value++
//            editior.putInt("level",value)
//            editior.apply()
//            Myname.setText("$value")
//        }

//        image = findViewById(R.id.image)

//        walllist.add(R.drawable.w1)
//        walllist.add(R.drawable.w2)
//        walllist.add(R.drawable.w3)
//        walllist.add(R.drawable.w4)
//        walllist.add(R.drawable.w5)
//        walllist.add(R.drawable.w6)
//        walllist.add(R.drawable.w7)
//        walllist.add(R.drawable.w8)
//        walllist.add(R.drawable.w9)
//        walllist.add(R.drawable.w10)
//        walllist.add(R.drawable.w11)
//        walllist.add(R.drawable.w12)


        for(i in 1..20)
        {
            var resourceid = resources.getIdentifier("wp$i","drawable",packageName)
            walllist.add(resourceid)
        }
//
        grid.setOnItemClickListener { adapterView, view, i, l ->
            var intent = Intent(this@MainActivity,full_screen_wallpaper::class.java)
            intent.putExtra("imagePosition",i)
            intent.putExtra("imageList",walllist)
            Log.e("abc","$walllist")
            startActivity(intent)
        }

//        image.setImageResource(walllist[0])

        var myadap = Adapter_Wall(this@MainActivity,walllist)
        grid.adapter = myadap
    }
}