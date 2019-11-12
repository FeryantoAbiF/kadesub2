package com.mpexabi.football.prevfragment

import com.mpexabi.football.model.Match

interface PrevView {
    fun visibleLoad()
    fun invisibleLoad()
    fun showList(data : List<Match>)
}