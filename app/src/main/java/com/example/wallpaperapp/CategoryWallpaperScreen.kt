package com.example.wallpaperapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.wallpaperapp.Adapter.CatWallAdapter
import com.example.wallpaperapp.Model.BOTMModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_category_wallpaper_screen.*

class CategoryWallpaperScreen : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_wallpaper_screen)

        val dId = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")

        tv_cat.text = name
        db = FirebaseFirestore.getInstance()

        db.collection("category").document(dId!!).collection("wallpaper")
            .addSnapshotListener { value, error ->
                //getting id and link of image so using already created model
                val listCatWall = arrayListOf<BOTMModel>()
                val data = value?.toObjects(BOTMModel::class.java)
                listCatWall.addAll(data!!)

                recycler_cat_wall.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                recycler_cat_wall.adapter = CatWallAdapter(this, listCatWall)

            }


    }
}