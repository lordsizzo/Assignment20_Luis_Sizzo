package com.example.assignment20_luis_sizzo.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.assignment20_luis_sizzo.R
import com.example.assignment20_luis_sizzo.adapters.TabsAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

fun View.snackbar(message:String, duration: Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this, message, duration).show()
}

fun Context.toast(message:String, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(this, message, duration).show()
}

fun View.click(listener: (View) -> Unit){
    this.setOnClickListener{
        listener(it)
    }
}
fun RecyclerView.layoutManagerCustom(){
    this.setHasFixedSize(true)
    this.layoutManager =  LinearLayoutManager(this.context)
}

fun TabLayout.tabs() {
    this.addTab(this.newTab().setText("Rock").setIcon(R.drawable.rock))
    this.addTab(this.newTab().setText("Classic").setIcon(R.drawable.classic))
    this.addTab(this.newTab().setText("Pop").setIcon(R.drawable.rock))

    this.tabGravity = TabLayout.GRAVITY_FILL
}

fun TabLayout.tabSelected(listener: (TabLayout.Tab) -> Unit){

    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                listener(tab)
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
    })
}

fun ViewPager.settings(tabLayout: TabLayout, adapter: TabsAdapter){
    this.adapter = adapter
    this.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
}
