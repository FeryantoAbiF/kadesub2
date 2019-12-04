package com.mpexabi.football.detailview

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.mpexabi.football.*
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.database.FavoriteNext
import com.mpexabi.football.database.FavoritePrev
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.TeamLeague
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main3.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class Main3Activity : AppCompatActivity(), DetailView {

    private lateinit var id_event: String
    private var id_home: String? = null
    private var id_away: String? = null
    private lateinit var presenter: DetailPresenter

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var matchGo: Match? = null

    private lateinit var scoreHome: TextView
    private lateinit var scoreAway: TextView
    private lateinit var homeName: TextView
    private lateinit var awayName: TextView
    private lateinit var goalHome: TextView
    private lateinit var goalAway: TextView
    private lateinit var homeRedCard: TextView
    private lateinit var awayRedCard: TextView
    private lateinit var homeYellowCard: TextView
    private lateinit var awayYellowCard: TextView
    private lateinit var leagueName: TextView
    private lateinit var imageHome: ImageView
    private lateinit var imageAway: ImageView
    private lateinit var imageBanner: ImageView
    private var progressBar: ProgressBar? = null


    override fun showloading() {
        progressBar!!.visible()
    }

    override fun hideloading() {
        progressBar!!.invisible()
    }

    override fun showDetailEvent(event: Match) {
        scoreHome.text = event.homeScore
        scoreAway.text = event.awayScore
        homeName.text = event.homeTeam
        awayName.text = event.awayTeam
        goalHome.text = event.homeGoalDetails
        goalAway.text = event.awayGoalDetails
        homeRedCard.text = event.homeRedCards
        awayRedCard.text = event.awayRedCards
        homeYellowCard.text = event.homeYellowCards
        awayYellowCard.text = event.awayYellowCards
        leagueName.text = event.league

        Picasso.get()
            .load(event.strThumb)
            .error(R.mipmap.ic_launcher)
            .into(img_poster)
    }

    override fun showHomeTeam(teams: TeamLeague) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(homeBadge)
    }

    override fun showAwayTeam(teams: TeamLeague) {
        Picasso.get()
            .load(teams.strTeamBadge)
            .into(awayBadge)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        scoreAway = findViewById(R.id.awayScore)
        scoreHome = findViewById(R.id.homeScore)
        imageHome = findViewById(R.id.homeBadge)
        imageAway = findViewById(R.id.awayBadge)
        homeName = findViewById(R.id.homeTeam)
        awayName = findViewById(R.id.awayTeam)
        leagueName = findViewById(R.id.leagueName)
        goalHome = findViewById(R.id.goalHomeDetail)
        goalAway = findViewById(R.id.goalAwayDetail)
        homeRedCard = findViewById(R.id.homeRedCardDetail)
        awayRedCard = findViewById(R.id.awayRedCardDetail)
        homeYellowCard = findViewById(R.id.homeYellowCardDetail)
        awayYellowCard = findViewById(R.id.awayYellowCardDetail)
        progressBar = findViewById(R.id.progressDetail)
        imageBanner = findViewById(R.id.img_poster)

        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this, apiRepository)
        id_event = intent.getStringExtra("id_event")
        id_home = intent.getStringExtra("id_home")
        id_away = intent.getStringExtra("id_away")

        presenter.getDetailMatch(id_event)
        presenter.getDetailHomeTeam(id_home!!)
        presenter.getDetailAwayTeam(id_away!!)

        favoriteState()
        favoriteStatePref()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else  addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border)
        }    }

    private fun addToFavorite() {
        if (matchGo?.homeScore == null) {
            try {
                database.use {
                    insert(
                        FavoriteNext.TABLE_FAVORITE_NEXT,
                        FavoriteNext.ID_EVENT to matchGo?.idEvent,
                        FavoriteNext.AWAY_TEAM to matchGo?.awayTeam,
                        FavoriteNext.TANGGAL to matchGo?.dateEvent,
                        FavoriteNext.SCORE_HOME to matchGo?.homeScore,
                        FavoriteNext.SCORE_AWAY to matchGo?.awayScore,
                        FavoriteNext.ID_HOME_TEAM to matchGo?.homeTeamId,
                        FavoriteNext.ID_AWAY_TEAM to matchGo?.awayTeamId


                    )
                }
                Toast.makeText(this, "Data Next Match Berhasil di Tambahkan", Toast.LENGTH_SHORT).show()
            } catch (e: SQLiteConstraintException) {

            }
        } else {
            try {
                database.use {
                    insert(
                        FavoritePrev.TABLE_FAVORITE_PREV,
                        FavoritePrev.ID_EVENT to matchGo?.idEvent,
                        FavoritePrev.AWAY_TEAM to matchGo?.awayTeam,
                        FavoritePrev.TANGGAL to matchGo?.dateEvent,
                        FavoritePrev.SCORE_HOME to matchGo?.homeScore,
                        FavoritePrev.SCORE_AWAY to matchGo?.awayScore,
                        FavoritePrev.ID_HOME_TEAM to matchGo?.homeTeamId,
                        FavoritePrev.ID_AWAY_TEAM to matchGo?.awayTeamId


                    )
                }
                Toast.makeText(this, "Data Prev Match Berhasil di Tambahkan", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {

            }
        }

    }

    private fun removeFromFavorite() {
        if (matchGo?.homeScore == null) {
            try {
                database.use {
                    delete(FavoriteNext.TABLE_FAVORITE_NEXT, "(ID_EVENT = {id})", "id" to id_event)
                }
                Toast.makeText(this, "Berhasil menghapus Data", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            try {
                database.use {
                    delete(FavoritePrev.TABLE_FAVORITE_PREV, "(ID_EVENT = {id})", "id" to id_event)
                }
                Toast.makeText(this, "Berhasil menghapus Data", Toast.LENGTH_SHORT).show()

            } catch (e: SQLiteConstraintException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun favoriteState() {
        database.use {
            val result = select(FavoriteNext.TABLE_FAVORITE_NEXT)
                .whereArgs("(ID_EVENT = {id})", "id" to id_event)
            val favorite = result.parseList(classParser<FavoriteNext>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun favoriteStatePref() {
        database.use {
            val result = select(FavoritePrev.TABLE_FAVORITE_PREV)
                .whereArgs("(ID_EVENT = {id})", "id" to id_event)
            val favorite = result.parseList(classParser<FavoritePrev>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

}
