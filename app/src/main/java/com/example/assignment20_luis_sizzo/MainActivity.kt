package com.example.assignment20_luis_sizzo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment20_luis_sizzo.databinding.ActivityMainBinding
import com.example.assignment20_luis_sizzo.adapters.TabsAdapter
import com.example.assignment20_luis_sizzo.utils.settings
import com.example.assignment20_luis_sizzo.utils.tabSelected
import com.example.assignment20_luis_sizzo.utils.tabs

class MainActivity : AppCompatActivity() {

    lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        initViews()
    }
    fun initViews() {
        bindingMain.tabLayout.tabs()

        val adapter = TabsAdapter(supportFragmentManager,
            bindingMain.tabLayout.tabCount)

        bindingMain.viewPager.settings(bindingMain.tabLayout, adapter)

        bindingMain.tabLayout.tabSelected {
            bindingMain.viewPager.currentItem = it.position
        }
    }
}