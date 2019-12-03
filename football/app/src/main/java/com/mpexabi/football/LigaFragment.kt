package com.mpexabi.football


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mpexabi.football.adapter.RecyclerAdapter
import com.mpexabi.football.mainview.Main2Activity
import com.mpexabi.football.model.League

/**
 * A simple [Fragment] subclass.
 */
class LigaFragment : Fragment() {

    private var items : MutableList<League> = mutableListOf()
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_liga, container, false)

        recyclerView = v.findViewById(R.id.team_list_rv)
        initData()

        return v
    }

    fun getItemClick(item : League){
        val intent = Intent(activity, Main2Activity::class.java)
        intent.putExtra("id_liga",item.id)
        startActivity(intent)
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.nama_liga)
        val image = resources.obtainTypedArray(R.array.image_liga)
        val idLiga = resources.getStringArray(R.array.id_liga)

        items.clear()
        for (i in name.indices) {
            items.add(League(name[i],image.getResourceId(i, 0),idLiga[i]))
        }
        adapter = RecyclerAdapter(items){ liga : League -> getItemClick(liga)}

        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this.activity)
        recyclerView.layoutManager = layoutManager
        adapter.notifyDataSetChanged()


        //Recycle the typed array
        image.recycle()
    }


}
