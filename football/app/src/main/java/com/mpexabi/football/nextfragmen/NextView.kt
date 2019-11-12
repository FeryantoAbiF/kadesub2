package com.mpexabi.football.nextfragmen

import com.mpexabi.football.model.Match

interface NextView {
    fun visibleLoad()
    fun invisibleLoad()
    fun showList(data : List<Match>)
}