package com.mpexabi.football.api

import com.mpexabi.football.model.LeagueResponse
import com.mpexabi.football.model.MatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDBApi {

    @GET("lookupevent.php")
    fun getDetailEvent(@Query("id") id_event : String)
            : Call<MatchResponse>

    @GET("lookupteam.php")
    fun getDetailTeam(@Query("id") id_team : String)
            : Call<MatchResponse>

    @GET("lookupleague.php")
    fun getDetailLeugue(@Query("id") id_league : String)
            : Call<LeagueResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id")id_liga : String)
            : Call<MatchResponse>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id_liga : String)
            : Call<MatchResponse>

    @GET("searchevents.php")
    fun getSearchMatch(@Query("e") query : String)
            : Call<MatchResponse>

}