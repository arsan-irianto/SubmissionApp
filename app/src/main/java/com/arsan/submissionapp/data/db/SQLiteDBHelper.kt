package com.arsan.submissionapp.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.arsan.submissionapp.data.db.model.FavoritesMatch
import org.jetbrains.anko.db.*

class SQLiteDBHelper(ctx: Context)
    : ManagedSQLiteOpenHelper(ctx, "favoritesMatch.db", null, 1) {

    companion object {
        private var instance: SQLiteDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): SQLiteDBHelper {
            if (instance == null) {
                instance = SQLiteDBHelper(ctx.applicationContext)
            }
            return instance as SQLiteDBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoritesMatch.TABLE_FAVORITE, true,
                FavoritesMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoritesMatch.EVENT_ID to TEXT + UNIQUE,
                FavoritesMatch.EVENT_DATE to TEXT,
                FavoritesMatch.HOME_TEAM_ID to TEXT,
                FavoritesMatch.HOME_TEAM to TEXT,
                FavoritesMatch.HOME_SCORE to TEXT,
                FavoritesMatch.AWAY_TEAM_ID to TEXT,
                FavoritesMatch.AWAY_TEAM to TEXT,
                FavoritesMatch.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoritesMatch.TABLE_FAVORITE, true)
    }

}

// Access property for Context
val Context.database: SQLiteDBHelper get() = SQLiteDBHelper.getInstance(applicationContext)