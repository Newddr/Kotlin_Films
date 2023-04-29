package com.example.lab3_vk_control

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "films"
        private const val DATABASE_VERSION = 2
    }

    init {
        context.deleteDatabase("films")
        val dbFile = context.getDatabasePath(DATABASE_NAME)
        if (!dbFile.exists()) {
            val assets = context.assets
            val buffer = ByteArray(1024)
            var length: Int
            val inputStream = assets.open(DATABASE_NAME)
            val outputStream = FileOutputStream(dbFile)
            while ((inputStream.read(buffer).also { length = it }) > 0) {
                outputStream.write(buffer, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }
        else{

        }
    }

    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL("CREATE TABLE films (id INTEGER PRIMARY KEY, name TEXT,year INTEGER, description TEXT,poster TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS films")


    }
}