package com.centafrique.offlinedatacollection

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {


    companion object{

        val DATABASE_VERSION = 1
        val DATABASE_NAME = "attributes_details"

        val TABLE_ATTRIBUTE = "attributes"

        val KEY_ID = "id"
        val KEY_NAME = "name"
        val KEY_DESCRIPTION = "description"
        val KEY_IMAGE = "image"

    }

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE_ATTRIBUTE = ("CREATE TABLE " + TABLE_ATTRIBUTE + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_IMAGE + " BLOB" + ")")

        db?.execSQL(CREATE_TABLE_ATTRIBUTE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRIBUTE)
        onCreate(db)
    }

    fun addAttribute(name : String, description :String, image: ByteArray){

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NAME, name)
        contentValues.put(KEY_DESCRIPTION, description)
        contentValues.put(KEY_IMAGE, image)

        db.insert(TABLE_ATTRIBUTE, null, contentValues)
        db.close()
    }

    fun getAttributes():ArrayList<AttributesClass>{

        val empList: ArrayList<AttributesClass> = ArrayList<AttributesClass>()
        val selectQuery = "SELECT  * FROM $TABLE_ATTRIBUTE"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var name: String
        var desc: String
        var image : ByteArray


        if (cursor !=null){

            if (cursor.moveToFirst()){

                do {

                    name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                    desc = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                    image = cursor.getBlob(3)

                    val attributes = AttributesClass(name = name, description = desc, image = image)
                    empList.add(attributes)

                }while (cursor.moveToNext())

            }
            cursor.close()

        }

        return empList


    }


}