package com.mpexabi.football.prevfragment

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.api.TheSportDBApi
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.MatchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevPresenter (private val context: Context, private val view : PrevView, private val apiRepository: ApiRepository) {

    fun message(context: Context) {
        Toast.makeText(context, "Previous Match", Toast.LENGTH_SHORT).show()
    }

    fun getPrevList(league : String){

        view.visibleLoad()

        val connect : TheSportDBApi = apiRepository.getUrl().create(TheSportDBApi::class.java)
        connect.getLastMatch(league).enqueue(object : Callback<MatchResponse> {
            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                t.printStackTrace() }

            override fun onResponse(
                call: Call<MatchResponse>,
                response: Response<MatchResponse>) {

                view.invisibleLoad()
                val get : List<Match>? = response.body()!!.events
                if(get == null){
                    message(context)
                    Log.e("DATA ShowList",get?.size.toString()) }
                else{
                    view.showList(get) }

            }

        })
    }


}