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
import com.mpexabi.football.nextfragmen.NextPresenter
import com.mpexabi.football.nextfragmen.NextView
import com.mpexabi.football.prevfragment.PrevPresenter

/**
 * A simple [Fragment] subclass.
 */
class NextFragment : Fragment(), NextView {

    private lateinit var recycle: RecyclerView
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: NextPresenter
    private var match: MutableList<Match> = mutableListOf()
    private lateinit var progress : ProgressBar
    private lateinit var pref: ShredPref

    override fun visibleLoad() {
        progress.visible()
    }

    override fun invisibleLoad() {
        progress.invisible()
    }

    override fun showList(data: List<Match>) {
        match.clear()
        match.addAll(data)
        adapter = MatchAdapter(match,{itemMatch :Match -> itemMatchClicked(itemMatch)})
        recycle.adapter = adapter
        recycle.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recycle.layoutManager = layoutManager
        adapter.notifyDataSetChanged()
    }

    private fun itemMatchClicked(itemMatch: Match) {

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
        recycle = view.findViewById(R.id.rv_next)
        progress = view.findViewById(R.id.progressNext)
        pref = ShredPref(this.activity!!)
        showMatch()

    }

    private fun showMatch() {
        val request = ApiRepository()
        presenter = NextPresenter(this.activity!!,this, request)
        presenter.getNextList(pref.getIdLiga())
    }


}
