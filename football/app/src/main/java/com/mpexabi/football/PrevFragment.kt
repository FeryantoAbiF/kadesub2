package com.mpexabi.football


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.mpexabi.football.adapter.MatchAdapter
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.model.Match
import com.mpexabi.football.prevfragment.PrevPresenter
import com.mpexabi.football.prevfragment.PrevView

/**
 * A simple [Fragment] subclass.
 */
class PrevFragment : Fragment(), PrevView {

    override fun visibleLoad() {
        progressBar.visible()
    }

    override fun invisibleLoad() {
        progressBar.invisible()
    }

    override fun showList(data: List<Match>) {
        match.clear()
        match.addAll(data)

        matchAdapter = MatchAdapter(match, { itemMatch: Match -> itemMatchClicked(itemMatch) })

        recyclerView.adapter = matchAdapter
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
    }

    private fun itemMatchClicked(itemMatch: Match) {

    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var matchAdapter: MatchAdapter
    private lateinit var presenter: PrevPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var progressBar: ProgressBar
    private lateinit var pref: ShredPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.rv_prev)
        progressBar = view.findViewById(R.id.proggresPrev)
        pref = ShredPref(this.activity!!)
        showMatch()
    }

    private fun showMatch() {
        val request = ApiRepository()
        presenter = PrevPresenter(this.activity!!, this, request)
        presenter.getPrevList(pref.getIdLiga())
    }


}
