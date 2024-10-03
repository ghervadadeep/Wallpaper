package com.demo.wallpaper_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class Mypagerad(var fullScreenWallpaper: full_screen_wallpaper,var getimage: ArrayList<Int>?) :
    PagerAdapter() {
    override fun getCount(): Int = getimage!!.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var newitem123 : ImageView

        var view = LayoutInflater.from(fullScreenWallpaper).inflate(R.layout.new_item,container,false)
        newitem123 = view.findViewById(R.id.newitem123)

        newitem123.setBackgroundResource(getimage!![position])

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as View)

    }

}
