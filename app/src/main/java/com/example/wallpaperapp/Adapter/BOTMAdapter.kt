package com.example.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wallpaperapp.FinalScreen
import com.example.wallpaperapp.Model.BOTMModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_bom.view.*

class BOTMAdapter(private val requireContext: Context, private val listBestOfTheMonth: List<BOTMModel>) : RecyclerView.Adapter<BOTMAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bom, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = holder.image
        Glide.with(requireContext).load(listBestOfTheMonth[position].link).into(img)

        //when user click on any img of BOTM
        holder.itemView.setOnClickListener {
            //Opens FinalScreen
            val intent = Intent(requireContext, FinalScreen::class.java)
            //sending link of that particular image to FinalScreen for opening that img in finalscreen layout
            intent.putExtra("link", listBestOfTheMonth[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listBestOfTheMonth.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.bom_image
    }
}