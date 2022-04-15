package com.example.assignment20_luis_sizzo.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.assignment20_luis_sizzo.fragments.FragmentSearch

class TabsAdapter(fm: FragmentManager,
                  var totalTabs: Int,
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentSearch("rock")
            }
            1 -> {
                FragmentSearch("classick")
            }
            2 -> {
                FragmentSearch("pop")
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}