package com.mpexabi.football


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.mlsdev.animatedrv.AnimatedRecyclerView
import com.mpexabi.football.adapter.FavoriteNextAdapter
import com.mpexabi.football.database.FavoriteNext
import com.mpexabi.football.detailview.Main3Activity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoriteNextFragment : Fragment() {

    private lateinit var recyclerView: AnimatedRecyclerView
    private lateinit var pb: ProgressBar
    private lateinit var adapter: FavoriteNextAdapter

    private var favorites: MutableList<FavoriteNext> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_favorite_next, container, false)
        recyclerView = v.findViewById(R.id.rv_next_fav)
        pb = v.findViewById(R.id.progressNextFav)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()

    }

    private fun initAdapter() {

        adapter = FavoriteNextAdapter(favorites, this.activity!!)
        { itemTeams: FavoriteNext -> getItemClick(itemTeams) }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }

    private fun getItemClick(itemTeams: FavoriteNext) {
        val intent = Intent(activity, Main3Activity::class.java)
        intent.putExtra("id_event", itemTeams.idEvent)
        intent.putExtra("id_home", itemTeams.idHomeTeam)
        intent.putExtra("id_away", itemTeams.idAwayTeam)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite() {
        favorites.clear()
        context?.database?.use {
            pb.invisible()
            val result = select(FavoriteNext.TABLE_FAVORITE_NEXT)
            val favorite = result.parseList(classParser<FavoriteNext>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()
        }
    }


}
