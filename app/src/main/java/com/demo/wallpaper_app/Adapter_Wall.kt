package com.demo.wallpaper_app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView

class Adapter_Wall(var mainActivity: MainActivity, var walllist: ArrayList<Int>) : BaseAdapter() {
    override fun getCount(): Int = walllist.size

    override fun getItem(p0: Int): Any = p0

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {

        var grid: ImageView

        var view = LayoutInflater.from(mainActivity).inflate(R.layout.grid_item, p2, false)
        grid = view.findViewById(R.id.griditem1)

        grid.setImageResource(walllist[position])
        return view
    }

}
