package com.example.wallpaperapp


import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_final_screen.*
import kotlinx.coroutines.*
import java.io.File
import java.io.OutputStream
import java.net.URL
import java.util.*
import kotlin.Exception


class FinalScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        //receiving link from adapters
        val url = intent.getStringExtra("link")
        //opening the image and setting in imageview of final screen
        Glide.with(this).load(url).into(set_wallpaper)

        val urlImg = URL(url)

        //download image
        btn_download.setOnClickListener {
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImg.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                //this await() fun can be called only from coroutines or another suspend function
                saveImage(result.await())
            }
        }
        //set wallpaper in home screen
        btn_setWallpaper.setOnClickListener {
            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImg.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                wallpaperManager.setBitmap(result.await())

            }
        }
    }

    private fun saveImage(image: Bitmap?) {
        val random1 = Random().nextInt(528985)
        val random2 = Random().nextInt(952663)

        val name = "Wallpy-${random1 + random2}"
        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_PICTURES + File.separator + "Wallpy Images"
                )
            }
            val imgUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imgUri)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image saved", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Image not saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: Exception) {
            null
        }
    }
}