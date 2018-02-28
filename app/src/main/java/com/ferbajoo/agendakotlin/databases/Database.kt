package com.ferbajoo.agendakotlin.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by
 *          feuribe on 23/02/2018.
 */
class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "AgendaKotlin"
        val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(TableContacts().CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(TableContacts().DROP_TABLE)
        onCreate(p0)
    }

    class TableContacts {
        val TABLE = "contacts"
        val ID = "id_contact"
        val NAME = "name"
        val PHONE = "phone"
        val GENER = "gener"
        val AGE = "age"

        val CREATE_TABLE = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE,
                ID,
                NAME,
                PHONE,
                GENER,
                AGE)

        val DROP_TABLE = String.format("DROP TABLE IF EXISTS %s ", TABLE)

    }

}
