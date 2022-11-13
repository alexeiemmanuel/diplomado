package com.aemm.primerproyecto.ejercicioclase.almacenamiento

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tlb_user"
        private const val ID = "id"
        private const val NAME = "name"
        private const val DESCRIPTION = "description"

    }

    override fun onCreate(database: SQLiteDatabase?) {
        val sqlCreate = "CREATE TABLE $TBL_USER ($ID INTEGER PRIMARY KEY AUTOINCREMENT,$NAME, $DESCRIPTION)"
        database?.execSQL(sqlCreate)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val sqlUpdate = "DROP TABLE IF EXISTS $TBL_USER"
        database?.execSQL(sqlUpdate)
        onCreate(database)
    }

    fun insert(user: UserSqlModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }

        val result = db.insert(TBL_USER, null, contentValues)
        db.close()

        return result
    }

    fun getAllUsers(): ArrayList<UserSqlModel>{
        val userList = arrayListOf<UserSqlModel>()
        val query = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        // El cursor puede ser nulo porque no puede tener información
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(query, null)
        }catch (e: Exception){
            e.printStackTrace()
            return userList
        }

        var id: Int
        var name: String
        var description: String
        with(cursor){
            while(moveToNext()){
                id = getInt(getColumnIndexOrThrow(ID))
                name = getString(getColumnIndexOrThrow(NAME))
                description = getString(getColumnIndexOrThrow(DESCRIPTION))

                val user = UserSqlModel(id, name,description)
                userList.add(user)
            }
        }

        // Se cierra el cursor para evitar consumo de memorias
        // Por cada acceso a la base se abre un cursor
        cursor.close()

       return userList
    }

    fun updateUser(user: UserSqlModel): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(ID, user.id)
            put(NAME, user.name)
            put(DESCRIPTION, user.description)
        }

        //val result = db.update(TBL_USER, contentValues, "id=${user.id}", null)
        val result = db.update(TBL_USER, contentValues, "id=?", arrayOf("${user.id}"))

        db.close()
        return result
    }

    fun deleteUser(idUsuario:Int): Int{
        val db = this.writableDatabase
        val result = db.delete(TBL_USER, "id=?", arrayOf("$idUsuario"))
        db.close()

        return result
    }

}
