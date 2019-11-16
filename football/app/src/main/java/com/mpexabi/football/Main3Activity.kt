package com.mpexabi.football

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.mpexabi.football.api.ApiRepository
import com.mpexabi.football.model.Match
import com.mpexabi.football.model.TeamLeague
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity(), DetailView {

    private var id_event: String? = null
    private var id_home: String? = null
    private var id_away: String? = null
    private lateinit var presenter: DetailPresenter

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
        vs.text = "VS"

        val apiRepository = ApiRepository()
        presenter = DetailPresenter(this, apiRepository)
        id_event = intent.getStringExtra("id_event")
        id_home = intent.getStringExtra("id_home")
        id_away = intent.getStringExtra("id_away")

        presenter.getDetailMatch(id_event!!)
        presenter.getDetailHomeTeam(id_home!!)
        presenter.getDetailAwayTeam(id_away!!)

    }
}
