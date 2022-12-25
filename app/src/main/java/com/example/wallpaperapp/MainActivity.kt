package com.example.wallpaperapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> inflateFragment(HomeFragment.newInstance())
                R.id.download -> inflateFragment(DownloadFragment.newInstance())
            }
            true
        }
        //default fragment screen
        bottom_navbar.selectedItemId = R.id.home
    }

    private fun inflateFragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newInstance)
        transaction.commit()
    }
}