package com.mpexabi.football.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mpexabi.football.R
import com.mpexabi.football.database.FavoriteNext
import java.text.ParseException
import java.text.SimpleDateFormat

class FavoriteNextAdapter (
    private val match: List<FavoriteNext>,
    private val context: Context,
    private val listener: (FavoriteNext) -> Unit
) : RecyclerView.Adapter<FavoriteNextAdapter.FavoriteHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, p0, false)
        return FavoriteHolder(view)
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }


    class FavoriteHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val homeTeam: TextView = itemview.findViewById(R.id.homeNameTV)
        val awayTeam: TextView = itemview.findViewById(R.id.awaynameTV)
        val tanggal: TextView = itemview.findViewById(R.id.dateEventTV)
        val scoreHome: TextView = itemview.findViewById(R.id.homeScoreTV)
        val scoreAway: TextView = itemview.findViewById(R.id.awayScoreTV)

        fun bindItem(teams: FavoriteNext, listener: (FavoriteNext) -> Unit) {
            val getDate: String = teams.tanggal.toString()

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date = simpleDateFormat.parse(getDate)
                val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
                val dateFix = newFormat.format(date)
                tanggal.text = dateFix
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            scoreAway.text = teams.scoreAway
            scoreHome.text = teams.scoreHome


            awayTeam.text = teams.awayTeam
            homeTeam.text = teams.homeTeam

            itemView.setOnClickListener { listener(teams) }
        }
}


}