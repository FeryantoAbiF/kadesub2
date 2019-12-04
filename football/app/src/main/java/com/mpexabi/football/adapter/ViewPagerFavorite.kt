package com.mpexabi.football.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mpexabi.football.FavoriteNextFragment
import com.mpexabi.football.FavoritePrevFragment

class ViewPagerFavorite (fragment : FragmentManager) : FragmentPagerAdapter(fragment) {
    private val pages = listOf(
        FavoriteNextFragment(),
        FavoritePrevFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]

    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Next Match"
            else -> "Previous Match"
        }
    }
}