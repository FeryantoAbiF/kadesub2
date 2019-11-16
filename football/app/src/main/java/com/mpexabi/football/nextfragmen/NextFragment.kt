package com.mpexabi.football.nextfragmen


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.mpexabi.football.*
import com.mpexabi.football.adapter.MatchAdapter
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.model.Match

/**
 * A simple [Fragment] subclass.
 */
class NextFragment : Fragment(), NextView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var presenter: NextPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var progress: ProgressBar
    private lateinit var pref: ShredPref

    override fun visibleLoad() {
        progress.visible()
    }

    override fun invisibleLoad() {
        progress.invisible()
    }

    private fun itemMatchClicked(itemMatch: Match) {
        val intent = Intent(activity, Main3Activity::class.java)
        intent.putExtra("id_event",itemMatch.idEvent)
        intent.putExtra("id_home",itemMatch.homeTeamId)
        intent.putExtra("id_away",itemMatch.awayTeamId)
        startActivity(intent)

        Toast.makeText(this.activity, "detail match", Toast.LENGTH_LONG).show()
    }

    override fun showList(data: List<Match>) {

        match.clear()
        match.addAll(data)

        matchAdapter = MatchAdapter(match, { itemMatch: Match -> itemMatchClicked(itemMatch) })
        recyclerView.adapter = matchAdapter
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
        matchAdapter.notifyDataSetChanged()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_next)
        progress = view.findViewById(R.id.progressNext)
        pref = ShredPref(this.activity!!)

        showMatch()
    }

    private fun showMatch() {
        val request = ApiRepository()
        presenter = NextPresenter(this.activity!!, this, request)
        presenter.getNextList(pref.getIdLiga())
    }


}
