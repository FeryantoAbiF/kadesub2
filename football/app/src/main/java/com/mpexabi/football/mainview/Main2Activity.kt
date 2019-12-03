package com.mpexabi.football.mainview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.mpexabi.football.R
import com.mpexabi.football.ShredPref
import com.mpexabi.football.adapter.ViewPagerAdapter
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.invisible
import com.mpexabi.football.model.Item_League
import com.mpexabi.football.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity(), MainView {

    private lateinit var imgLiga: ImageView
    private lateinit var txtLiga: TextView
    private lateinit var desLiga: TextView
    private lateinit var pref : ShredPref
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar
    var idLiga: String = ""

    override fun showLiga(liga: Item_League) {

        progressBar.visible()

        Picasso.get()
            .load(liga.strBadge)
            .into(imgLiga)

        txtLiga.text = liga.strLeague
        desLiga.text = liga.strDescriptionEN
        desLiga.maxLines=5

        progressBar.invisible()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        progressBar = findViewById(R.id.progressbar)

        view_pager_main.adapter = ViewPagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(view_pager_main)

        imgLiga = findViewById(R.id.liga)
        txtLiga = findViewById(R.id.textLiga)
        desLiga = findViewById(R.id.descLiga)

        val apiRepository = ApiRepository()
        presenter = MainPresenter(this, apiRepository)
        idLiga = intent.getStringExtra("id_liga")?:""

        pref = ShredPref(this)
        pref.setIdLiga(idLiga)
        presenter.detailLiga(idLiga)

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.search_item, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.searchView) {
////            startActivity(Intent(this, CariActivity::class.java))
//            return true
//        }else  if (item.itemId == android.R.id.home){
//            onBackPressed()
//        }
//
//        return super.onOptionsItemSelected(item)
//
//    }

}
