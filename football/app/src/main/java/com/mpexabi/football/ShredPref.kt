package com.mpexabi.football

import android.content.Context

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

class ShredPref(context: Context) {

    val NAME = "MyPreference"
    val pref = context.getSharedPreferences(NAME,Context.MODE_PRIVATE)
    val ID_LIGA = "ID_LIGA"

    fun setIdLiga(idLiga : String){
        val editor = pref.edit()
        editor.putString(ID_LIGA,idLiga)
        editor.apply()
    }
    fun getIdLiga() : String {
        return pref.getString(ID_LIGA,"")
    }



}