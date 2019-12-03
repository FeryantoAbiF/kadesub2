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
import com.mpexabi.football.adapter.FavoritePrevAdapter
import com.mpexabi.football.database.FavoritePrev
import com.mpexabi.football.database.database
import com.mpexabi.football.detailview.Main3Activity
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritePrevFragment : Fragment() {

    private lateinit var recyclerView: AnimatedRecyclerView
    private lateinit var pb: ProgressBar
    private lateinit var adapter: FavoritePrevAdapter

    private var favorites: MutableList<FavoritePrev> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_favorite_prev, container, false)

        recyclerView = v.findViewById(R.id.rv_prev_favorite)
        pb = v.findViewById(R.id.progressPrevFav)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {

        adapter = FavoritePrevAdapter(favorites, this.activity!!)
        { items: FavoritePrev -> getItemClick(items) }

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }

    private fun getItemClick(itemTeams: FavoritePrev) {
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
            val result = select(FavoritePrev.TABLE_FAVORITE_PREV)
            val favorite = result.parseList(classParser<FavoritePrev>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
            recyclerView.scheduleLayoutAnimation()
        }
    }


}
