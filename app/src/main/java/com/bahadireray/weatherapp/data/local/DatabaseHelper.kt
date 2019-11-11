package com.bahadireray.weatherapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val TABLE_NAME = "User"
    private val COL_ID = "id"
    private val COL_CITY = "city"
    private val COL_DEG = "deg"

    companion object {
        private val DATABASE_NAME = "SQLITE_DATABASE"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =  "CREATE TABLE $TABLE_NAME ($COL_ID Integer PRIMARY KEY, $COL_CITY TEXT, $COL_DEG TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertData(city: String, deg: String) {
        val sqliteDB = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_CITY, city)
        contentValues.put(COL_DEG, deg)

        val result = sqliteDB.insert(TABLE_NAME, null, contentValues)

        Toast.makeText(
            context,
            if (result != -1L) "Kayıt Başarılı" else "Kayıt yapılamadı.",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun readData(): MutableList<WeatherData> {
        val userList = mutableListOf<WeatherData>()
        val sqliteDB = this.readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val result = sqliteDB.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val user = WeatherData()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(COL_CITY))
                user.deg = result.getString(result.getColumnIndex(COL_DEG))
                userList.add(user)
            } while (result.moveToNext())
        }
        result.close()
        sqliteDB.close()
        return userList
    }

    fun deleteAllData() {
        val sqliteDB = this.writableDatabase
        sqliteDB.delete(TABLE_NAME, null, null)
        sqliteDB.close()

    }


}