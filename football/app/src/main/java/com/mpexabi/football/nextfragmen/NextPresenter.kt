package com.mpexabi.football.nextfragmen

import android.content.Context
import android.widget.Toast
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.api.TheSportDBApi
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextPresenter (private val context: Context,private val view : NextView, private val apiRepository: ApiRepository) {

        fun getNextList(liga : String){
            view.visibleLoad()
            val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
            connect.getNextMatch(liga).enqueue(object : Callback<MatchResponse> {
                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {

                }

                override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                    view.invisibleLoad()
                    val get : List<Match>? = response.body()!!.matchs
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