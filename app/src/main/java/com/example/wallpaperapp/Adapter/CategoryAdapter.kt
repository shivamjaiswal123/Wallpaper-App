package com.example.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.CategoryWallpaperScreen
import com.example.wallpaperapp.Model.CategoryModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val requireContext: Context, private val listCategory: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = listCategory[position].name
        Glide.with(requireContext).load(listCategory[position].link).into(holder.image)


        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, CategoryWallpaperScreen::class.java)
            //sending id to CategoryWallpaperScreen for getting ref of wallpaper collection
            intent.putExtra("id", listCategory[position].id)
            intent.putExtra("name", listCategory[position].name)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listCategory.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.category_name
        val image = itemView.category_image
    }
}