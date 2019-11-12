package com.mpexabi.football.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mpexabi.football.NextFragment
import com.mpexabi.football.PrevFragment

class ViewPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pages = listOf(
        NextFragment(),
        PrevFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Next Match"
            else -> "Prev Match"
        }
    }
}