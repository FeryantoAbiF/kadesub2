package com.mpexabi.football.mainview

import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.api.TheSportDBApi
import com.mpexabi.football.model.Item_League
import com.mpexabi.football.model.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private var view: MainView , private var apiRepository: ApiRepository) {

    fun detailLiga(id: String) {

        val connect: TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getDetailLeugue(id).enqueue(object : Callback<LeagueResponse> {
            override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<LeagueResponse>,
                response: Response<LeagueResponse>
            ) {
                val getDetail: Item_League = response.body()?.leagues!!.get(0)
                view.showLiga(getDetail)

            }

        })
    }
}