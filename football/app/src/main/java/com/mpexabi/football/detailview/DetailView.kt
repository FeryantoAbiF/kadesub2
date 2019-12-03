package com.mpexabi.football

import com.mpexabi.football.model.Match
import com.mpexabi.football.model.TeamLeague

interface DetailView {
    fun showloading()
    fun hideloading()
    fun showDetailEvent(event: Match)
    fun showHomeTeam(teams : TeamLeague)
    fun showAwayTeam(teams : TeamLeague)
}