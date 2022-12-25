package com.example.wallpaperapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaperapp.Adapter.BOTMAdapter
import com.example.wallpaperapp.Adapter.CategoryAdapter
import com.example.wallpaperapp.Adapter.ColorToneAdapter
import com.example.wallpaperapp.Model.BOTMModel
import com.example.wallpaperapp.Model.CategoryModel
import com.example.wallpaperapp.Model.ColorToneModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("UNREACHABLE_CODE")
class HomeFragment : Fragment() {
    lateinit var firestore: FirebaseFirestore

    //private val listBestOfTheMonth = mutableListOf<BOTMModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //create instance of firebase
        firestore = FirebaseFirestore.getInstance()

        //fetching data of bestOfTheMonth collection
        firestore.collection("bestOfTheMonth").addSnapshotListener { value, error ->
            val listBestOfTheMonth = mutableListOf<BOTMModel>()
            val data = value?.toObjects(BOTMModel::class.java)
            listBestOfTheMonth.addAll(data!!)

            recycler_botm.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val adapter = BOTMAdapter(requireContext(), listBestOfTheMonth)
            recycler_botm.adapter = adapter
        }

        //fetching data of colorTone collection
        firestore.collection("colorTone").addSnapshotListener { value, error ->
            val listColorTone = mutableListOf<ColorToneModel>()
            val data = value?.toObjects(ColorToneModel::class.java)
            listColorTone.addAll(data!!)

            recycler_colorTone.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recycler_colorTone.adapter = ColorToneAdapter(requireContext(), listColorTone)

        }

        //fetching data of category collection
        firestore.collection("category").addSnapshotListener { value, error ->
            val listCategory = mutableListOf<CategoryModel>()
            val data = value?.toObjects(CategoryModel::class.java)
            listCategory.addAll(data!!)

            recycler_category.layoutManager =
                GridLayoutManager(requireContext(), 2)
            recycler_category.adapter = CategoryAdapter(requireContext(), listCategory)
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}