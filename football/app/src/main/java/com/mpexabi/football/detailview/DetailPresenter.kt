package com.mpexabi.football

import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.api.TheSportDBApi
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.MatchResponse
import com.mpexabi.football.model.TeamLeague
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter (private var view : DetailView , private var apiRepository: ApiRepository) {

    fun getDetailMatch(id_event : String){
        view.showloading()

        val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getDetailEvent(id_event).enqueue(object : Callback<MatchResponse> {

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                val getDetail : Match = response.body()!!.events.get(0)
                view.showDetailEvent(getDetail)
                view.hideloading()

            }


        })
    }

    fun getDetailHomeTeam(id_teams : String){
        view.showloading()

        val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchResponse>{
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                val getTeams : TeamLeague = response.body()!!.teams.get(0)
                view.showHomeTeam(getTeams)
                view.hideloading()

            }

        })
    }
    fun getDetailAwayTeam(id_teams : String){
        view.showloading()

        val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getDetailTeam(id_teams).enqueue(object : Callback<MatchResponse>{

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                val getTeams : TeamLeague = response.body()!!.teams.get(0)
                view.showAwayTeam(getTeams)
                view.hideloading()


            }

        })
    }
}