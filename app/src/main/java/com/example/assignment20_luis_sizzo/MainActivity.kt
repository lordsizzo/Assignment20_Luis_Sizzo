package com.example.assignment20_luis_sizzo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.assignment20_luis_sizzo.databinding.ActivityMainBinding
import com.example.assignment20_luis_sizzo.ui.TabsAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        bindingMain.tabLayout.addTab(bindingMain.tabLayout.newTab().setText("Rock").setIcon(R.drawable.rock))
        bindingMain.tabLayout.addTab(bindingMain.tabLayout.newTab().setText("Classic").setIcon(R.drawable.classic))
        bindingMain.tabLayout.addTab(bindingMain.tabLayout.newTab().setText("Pop").setIcon(R.drawable.rock))

        bindingMain.tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabsAdapter(this, supportFragmentManager,
            bindingMain.tabLayout.tabCount)
        bindingMain.viewPager.adapter = adapter
        bindingMain.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(bindingMain.tabLayout))

        bindingMain.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                bindingMain.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}