package com.mpexabi.football.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mpexabi.football.R
import com.mpexabi.football.model.Match
import java.text.ParseException
import java.text.SimpleDateFormat

class MatchAdapter(private var match: List<Match>,
                   private val listener : (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position],listener)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_event, viewGroup, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = match.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val homeTeam: TextView = itemView.findViewById(R.id.homeNameTV)
        val awayTeam : TextView = itemView.findViewById(R.id.awaynameTV)
        val dateMatch : TextView = itemView.findViewById(R.id.dateEventTV)
        val scoreHome : TextView = itemView.findViewById(R.id.homeScoreTV)
        val scoreAway : TextView = itemView.findViewById(R.id.awayScoreTV)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(match: Match, listener: (Match) -> Unit) {

            scoreAway.text = match.awayScore
            scoreHome.text = match.homeScore
            awayTeam.text = match.awayTeam
            homeTeam.text = match.homeTeam

            val getDate : String? = match.dateEvent

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date = simpleDateFormat.parse(getDate)
                val new = SimpleDateFormat("EEEE, MMM dd, yyyy")
                val dateFix = new.format(date)
                dateMatch.text = dateFix
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            itemView.setOnClickListener { listener(match) }
        }

    }


}
