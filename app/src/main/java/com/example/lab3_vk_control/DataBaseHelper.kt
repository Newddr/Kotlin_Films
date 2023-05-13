package com.example.lab3_vk_control

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "movies.db"
        private const val DATABASE_VERSION = 5
    }

    init {
        //context.deleteDatabase("movies.db")
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
       //db.execSQL("CREATE TABLE movies (id TEXT PRIMARY KEY, name TEXT,year TEXT, description TEXT,poster TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
       // db.execSQL("DROP TABLE IF EXISTS movies")


    }
    fun checkIfMovieExists(id: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM movies WHERE _id = ?"
        val cursor = db.rawQuery(query, arrayOf(id))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
    fun insertMovie(movie: Movie) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("_id", movie.id)
        values.put("name", movie.title)
        values.put("year", movie.year)
        values.put("poster", movie.image)
        values.put("description", movie.plot)
        // Добавьте другие столбцы, если необходимо
        db.insert("movies", null, values)
        db.close()
    }
}