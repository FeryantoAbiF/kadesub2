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
        progress!!.visible()
    }

    override fun invisibleLoad() {
        progress!!.invisible()
    }

    override fun showList(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter = MatchAdapter(match,{itemMatch :Match -> itemMatchClicked(itemMatch)})
        recycle!!.adapter = adapter
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recycle!!.layoutManager = layoutManager
    }

    private fun itemMatchClicked(itemMatch: Match) {

    }

    private var recycle: RecyclerView? = null
    private var adapter: MatchAdapter? = null
    private lateinit var presenter: PrevPresenter
    private var match: MutableList<Match> = mutableListOf()
    private var progress : ProgressBar? = null
    private lateinit var pref: ShredPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_prev, container, false)
        recycle = view.findViewById(R.id.rv_prev)
        progress = view.findViewById(R.id.proggresPrev)
        pref = ShredPref(this.activity!!)
        showMatch()
        return view
    }

    private fun showMatch() {
        val request = ApiRepository()
        presenter = PrevPresenter(this.activity!!,this, request)
        presenter.getPrevList(pref.getIdLiga())
    }


}
