package com.mpexabi.football.api

import com.mpexabi.football.model.LeagueResponse
import com.mpexabi.football.model.MatchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportDBApi {

    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailEvent(@Query("id") id_event : String)
            : Call<MatchResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") id_team : String)
            : Call<MatchResponse>

    @GET("api/v1/json/1/lookupleague.php")
    fun getDetailLeugue(@Query("id") id_league : String)
            : Call<LeagueResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id")id_liga : String)
            : Call<MatchResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getLastMatch(@Query("id") id_liga : String)
            : Call<MatchResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun getSearchMatch(@Query("e") query : String)
            : Call<MatchResponse>

}