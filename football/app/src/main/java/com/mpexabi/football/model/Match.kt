package com.mpexabi.football.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match (
    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    // -- HOME TEAM --
    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    // -- AWAY TEAM --
    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strAwayGoalDetails")
    val awayGoalDetails: String? = null

) : Parcelable