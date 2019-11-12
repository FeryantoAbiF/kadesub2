package com.mpexabi.football.prevfragment

import android.content.Context
import android.widget.Toast
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.api.TheSportDBApi
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevPresenter (private val context: Context, private val view : PrevView, private val apiRepository: ApiRepository) {

    fun getPrevList(liga : String){
        view.visibleLoad()
        val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getLastMatch(liga).enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                view.invisibleLoad()
                val get : List<Match>? = response.body()!!.match
                if(get == null){
                    showToast(context)
                }else{
                    view.showList(get!!)

                }


            }

        })
    }

    fun showToast(context: Context) {
        Toast.makeText(context, "Pertandingan di Liga telah Selesai", Toast.LENGTH_SHORT).show()
    }
}