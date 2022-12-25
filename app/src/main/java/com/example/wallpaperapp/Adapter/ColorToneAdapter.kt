package com.example.wallpaperapp.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallpaperapp.FinalScreen
import com.example.wallpaperapp.Model.ColorToneModel
import com.example.wallpaperapp.R
import kotlinx.android.synthetic.main.item_colortone.view.*

class ColorToneAdapter(private val requireContext: Context, private val listColorTone: List<ColorToneModel>) : RecyclerView.Adapter<ColorToneAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_colortone, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //setting the background color of card
        //setCardBackgroundColor takes int values so parsing string to int using Color.parseColor
        holder.colorCard.setCardBackgroundColor(Color.parseColor(listColorTone[position].color))

        //when user click on any img of BOTM
        holder.itemView.setOnClickListener {
            val intent = Intent(requireContext, FinalScreen::class.java)
            //sending link of that particular color to FinalScreen for opening that img in finalscreen layout
            intent.putExtra("link", listColorTone[position].link)
            requireContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listColorTone.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colorCard = itemView.color_card
    }
}