package com.mpexabi.football.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mpexabi.football.R
import com.mpexabi.football.model.League
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val context: Context, private val items: List<League>,
                      private val listener: (League) -> Unit) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league, parent, false))


    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(items: League, listener: (League) -> Unit) {
            val ligaBadge: ImageView = itemView.findViewById(R.id.imageView)
            val ligaName: TextView = itemView.findViewById(R.id.nameView)
                items.image?.let { Picasso.get().load(it).into(ligaBadge) }
                ligaName.text = items.name
                itemView.setOnClickListener { listener(items) }
        }
    }

}