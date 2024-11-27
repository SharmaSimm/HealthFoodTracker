package com.example.bitfit

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Create table for food data
        val foodTableQuery = """
            CREATE TABLE IF NOT EXISTS $FOOD_TABLE_NAME (
                $ID INTEGER PRIMARY KEY, 
                $FOOD TEXT, 
                $CALORIE INTEGER, 
                $DAYOFYEAR INTEGER
            )
        """.trimIndent()

        // Create table for image data
        val imageTableQuery = """
            CREATE TABLE IF NOT EXISTS $IMAGE_TABLE_NAME (
                $ID INTEGER PRIMARY KEY, 
                $IMAGE_PATH TEXT
            )
        """.trimIndent()

        db.execSQL(foodTableQuery)
        db.execSQL(imageTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop and recreate tables if the schema changes
        db.execSQL("DROP TABLE IF EXISTS $FOOD_TABLE_NAME")
        db.execSQL("DROP TABLE IF EXISTS $IMAGE_TABLE_NAME")
        onCreate(db)
    }

    // Add food item
    @RequiresApi(Build.VERSION_CODES.N)
    fun addFood(foodName: String, calorieCount: String) {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(FOOD, foodName)
        values.put(CALORIE, calorieCount)
        values.put(DAYOFYEAR, dayOfYear)

        db.insert(FOOD_TABLE_NAME, null, values)
        db.close()
    }

    // Add image path
    fun addImagePath(imagePath: String) {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(IMAGE_PATH, imagePath)

        db.insert(IMAGE_TABLE_NAME, null, values)
        db.close()
    }

    // Get food data
    fun getFood(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $FOOD_TABLE_NAME", null)
    }

    // Get image paths
    fun getImg(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $IMAGE_TABLE_NAME", null)
    }

    companion object {
        private val DATABASE_NAME = "BitFit"
        private val DATABASE_VERSION = 3

        // Food table
        val FOOD_TABLE_NAME = "calorie_table"
        val ID = "id"
        val FOOD = "food_name"
        val CALORIE = "calorie_count"
        val DAYOFYEAR = "day_of_year"

        // Image table
        val IMAGE_TABLE_NAME = "image_table"
        val IMAGE_PATH = "image_path"
    }
}
