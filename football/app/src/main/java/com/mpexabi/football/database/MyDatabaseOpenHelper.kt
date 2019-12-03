package com.mpexabi.football

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {


    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(
            FavoriteModelNext.TABLE_FAVORITE_NEXT, true,
            FavoriteModelNext.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteModelNext.ID_EVENT to TEXT + UNIQUE,
            FavoriteModelNext.HOME_TEAM to TEXT,
            FavoriteModelNext.AWAY_TEAM to TEXT,
            FavoriteModelNext.TANGGAL to TEXT,
            FavoriteModelNext.SCORE_HOME to TEXT,
            FavoriteModelNext.SCORE_AWAY to TEXT,
            FavoriteModelNext.ID_HOME_TEAM to TEXT,
            FavoriteModelNext.ID_AWAY_TEAM to TEXT
        )

        db?.createTable(
            FavoriteModelPrev.TABLE_FAVORITE_PREV, true,
            FavoriteModelPrev.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteModelPrev.ID_EVENT to TEXT + UNIQUE,
            FavoriteModelPrev.HOME_TEAM to TEXT,
            FavoriteModelPrev.AWAY_TEAM to TEXT,
            FavoriteModelPrev.TANGGAL to TEXT,
            FavoriteModelPrev.SCORE_HOME to TEXT,
            FavoriteModelPrev.SCORE_AWAY to TEXT,
            FavoriteModelPrev.ID_HOME_TEAM to TEXT,
            FavoriteModelPrev.ID_AWAY_TEAM to TEXT
        )

    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteModelNext.TABLE_FAVORITE_NEXT, true)
        db?.dropTable(FavoriteModelPrev.TABLE_FAVORITE_PREV,true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)