package com.demo.wallpaper_app

import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.viewpager.widget.ViewPager
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

class full_screen_wallpaper : AppCompatActivity() {

    lateinit var share: ImageView
    lateinit var download: ImageView

    lateinit var fullscreen: CardView
    lateinit var viewpager: ViewPager

    //    lateinit var imageView:ImageView
    lateinit var set: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_wallpaper)

        share = findViewById(R.id.share)
        download = findViewById(R.id.download)

        fullscreen = findViewById(R.id.fullscreen)
        viewpager = findViewById(R.id.viewpager)

        set = findViewById(R.id.set)

        var getindex = intent.getIntExtra("imagePosition", 0)
        var getimage = intent.getIntegerArrayListExtra("imageList")

        Log.e("abcd", "$getimage")

//        imageView.setImageResource(getimage!![getindex])
//        viewpager.setBackgroundResource(getindex)

        var myadpter = Mypagerad(this@full_screen_wallpaper, getimage)
        viewpager.adapter = myadpter
        viewpager.setCurrentItem(getindex)

//        viewpager.setCurrentItem(getindex)
        var currentpo = viewpager.currentItem



        download.setOnClickListener {
            var currentimage = viewpager.currentItem
            saveImageToExternal(getimage!![currentimage])
        }

        share.setOnClickListener {
            var currentimage = viewpager.currentItem
            shareImage(getimage!![currentimage])

        }

        set.setOnClickListener {
            var currentimage = viewpager.currentItem

            val bitmap = BitmapFactory.decodeResource(resources, getindex)

            // Obtain WallpaperManager instance
            val wallpaperManager = WallpaperManager.getInstance(applicationContext)

            try {
                // set the wallpaper by calling the setResource function and
                // passing the drawable file
                wallpaperManager.setResource(getimage!![currentimage])
                Toast.makeText(this@full_screen_wallpaper, "wallpaper set", Toast.LENGTH_LONG)
                    .show()

            } catch (e: IOException) {
                // here the errors can be logged instead of printStackTrace
                e.printStackTrace()
            }

//            Toast.makeText(this@full_screen_wallpaper, "wallpaper set", Toast.LENGTH_LONG)

        }
    }

    private fun shareImage(resId: Int) {
        var currentimage = viewpager.currentItem

        val bm: Bitmap = BitmapFactory.decodeResource(resources, resId)


        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/MyWallpaper") //Creates app specific folder
        Log.d(
            "====-==== ",
            "saveImageToExternal:  ---> path = ${path}"
        ) //   /storage/sdcard0/Pictures/MyWallpaper
        path.mkdirs()
        val imageFile = File(path, "${Random.nextInt()}.png") // Imagename.png
        // /storage/sdcard0/Pictures/MyWallpaper/-1053059993.png
        val out = FileOutputStream(imageFile)
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, out) // Compress Image
            out.flush()
            out.close()

            val bitmapUri = Uri.parse(imageFile.absolutePath)

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
            startActivity(Intent.createChooser(intent, "Share"))

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
//            MediaScannerConnection.scanFile(
//                this, arrayOf<String>(imageFile.absolutePath), null
//            ) { path, uri ->
//                Log.i("ExternalStorage", "Scanned $path:")
//                Log.i("ExternalStorage", "-> uri=$uri")

//                val bitmapUri = uri //Uri.parse(imageFile.absolutePath)

//                val intent = Intent(Intent.ACTION_SEND)
//                intent.type = "image/*"
//                intent.putExtra(Intent.EXTRA_STREAM, bitmapUri)
//                startActivity(Intent.createChooser(intent, "Share"))
//
//            }
        } catch (e: Exception) {
            throw IOException()
        }

    }

    fun saveImageToExternal(resId: Int) {
        val bm: Bitmap = BitmapFactory.decodeResource(resources, resId)

        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/MyWallpaper") //Creates app specific folder
        Log.d(
            "====-==== ",
            "saveImageToExternal:  ---> path = ${path}"
        ) //   /storage/sdcard0/Pictures/MyWallpaper
        path.mkdirs()
        val imageFile = File(path, "${Random.nextInt()}.png") // Imagename.png
        // /storage/sdcard0/Pictures/MyWallpaper/-1053059993.png
        val out = FileOutputStream(imageFile)
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, out) // Compress Image
            out.flush()
            out.close()

            // Tell the media scanner about the new file so that it is
            // immediately available to the user.
            MediaScannerConnection.scanFile(
                this, arrayOf<String>(imageFile.absolutePath), null
            ) { path, uri ->
                Log.i("ExternalStorage", "Scanned $path:")
                Log.i("ExternalStorage", "-> uri=$uri")
                Toast.makeText(
                    this@full_screen_wallpaper,
                    "Download Successful",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: Exception) {
            throw IOException()
        }
    }

}
