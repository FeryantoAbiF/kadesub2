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

class MatchAdapter(private val match: List<Match>, private val listener : (Match) -> Unit)
    : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(match[position],listener)    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false))

}

class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

    val homeTeam: TextView = itemview.findViewById(R.id.homeNameTV)
    val awayTeam : TextView = itemview.findViewById(R.id.awaynameTV)
    val tgl : TextView = itemview.findViewById(R.id.dateEventTV)
    val scoreHome : TextView = itemview.findViewById(R.id.homeScoreTV)
    val scoreAway : TextView = itemview.findViewById(R.id.awayScoreTV)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(match: Match, listener: (Match) -> Unit) {
        val getDate : String = match.dateEvent

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date = simpleDateFormat.parse(getDate)
            val newFormat = SimpleDateFormat("EEEE, MMM dd, yyyy")
            val dateFix = newFormat.format(date)
            tgl.text = dateFix
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        scoreAway.text = match.intAwayScore
        scoreHome.text = match.intHomeScore

        awayTeam.text = match.strAwayTeam
        homeTeam.text = match.strHomeTeam

        itemView.setOnClickListener { listener(match)
    }

}




}