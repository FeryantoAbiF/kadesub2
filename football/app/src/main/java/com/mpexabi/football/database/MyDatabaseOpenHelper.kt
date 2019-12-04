package com.mpexabi.football

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.mpexabi.football.database.FavoriteNext
import com.mpexabi.football.database.FavoritePrev
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
            FavoriteNext.TABLE_FAVORITE_NEXT, true,
            FavoriteNext.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteNext.ID_EVENT to TEXT + UNIQUE,
            FavoriteNext.HOME_TEAM to TEXT,
            FavoriteNext.AWAY_TEAM to TEXT,
            FavoriteNext.TANGGAL to TEXT,
            FavoriteNext.SCORE_HOME to TEXT,
            FavoriteNext.SCORE_AWAY to TEXT,
            FavoriteNext.ID_HOME_TEAM to TEXT,
            FavoriteNext.ID_AWAY_TEAM to TEXT
        )

        db?.createTable(
            FavoritePrev.TABLE_FAVORITE_PREV, true,
            FavoritePrev.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoritePrev.ID_EVENT to TEXT + UNIQUE,
            FavoritePrev.HOME_TEAM to TEXT,
            FavoritePrev.AWAY_TEAM to TEXT,
            FavoritePrev.TANGGAL to TEXT,
            FavoritePrev.SCORE_HOME to TEXT,
            FavoritePrev.SCORE_AWAY to TEXT,
            FavoritePrev.ID_HOME_TEAM to TEXT,
            FavoritePrev.ID_AWAY_TEAM to TEXT
        )

    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteNext.TABLE_FAVORITE_NEXT, true)
        db?.dropTable(FavoritePrev.TABLE_FAVORITE_PREV,true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)