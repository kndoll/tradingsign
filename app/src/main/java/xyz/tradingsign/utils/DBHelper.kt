package xyz.tradingsign.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
        context: Context?,
        name: String?,
        factory: SQLiteDatabase.CursorFactory?,
        version: Int
): SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        var sql: String = "create table kospi_list\n" +
                "(\n" +
                "    code TEXT not null\n" +
                "        constraint kospi_list_pk\n" +
                "            primary key,\n" +
                "    name TEXT not null,\n" +
                "    date TEXT not null\n" +
                ");"

        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql: String = "DROP TABLE if exists kospi_list"

        db.execSQL(sql)
        onCreate(db)
    }
}