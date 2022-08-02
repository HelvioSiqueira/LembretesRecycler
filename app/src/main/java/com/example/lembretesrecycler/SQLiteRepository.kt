package com.example.lembretesrecycler

import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.lembretesrecycler.repositorys.LembretesRepository

class SQLiteRepository(ctx: Context): LembretesRepository {
    private val helper = LembretesSqlHelper(ctx)

    private fun insert(lembrete: Lembrete){
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_TITULO, lembrete.titulo)
            put(COLUMN_TEXT, lembrete.titulo)
            put(COLUMN_PRIORIDADE, lembrete.prioridade)
            put(COLUMN_DATA, lembrete.data)
        }

        db.insert(TABLE_LEMBRETES, null, cv)

        db.close()
    }

    private fun update(lembrete: Lembrete){
        val db = helper.writableDatabase

        val cv = ContentValues().apply {
            put(COLUMN_TITULO, lembrete.titulo)
            put(COLUMN_TEXT, lembrete.texto)
            put(COLUMN_PRIORIDADE, lembrete.prioridade)
            put(COLUMN_DATA, lembrete.data)
        }

        db.insertWithOnConflict(TABLE_LEMBRETES, null, cv, SQLiteDatabase.CONFLICT_REPLACE)

        db.close()
    }

    override fun add(lembrete: Lembrete) {
        insert(lembrete)

        //Log.i("HSV", lembrete.titulo)
    }

    override fun obterLembretes(): List<Lembrete> {
        val lembretes = ArrayList<Lembrete>()

        val sql = "SELECT * FROM $TABLE_LEMBRETES"

        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, arrayOf())

        while (cursor.moveToNext()){
            val lembrete = lembreteFromCursor(cursor)
            lembretes.add(lembrete)
        }

        Log.i("HSV", lembretes.joinToString())

        return lembretes
    }

    override fun remove(position: Int, term: String, callback: (Lembrete) -> Unit) {
        val db = helper.writableDatabase

        db.delete(TABLE_LEMBRETES, "$COLUMN_TITULO = ?", arrayOf(term))

        db.close()
    }

    override fun move(from: Int, to: Int) {
        TODO("Not yet implemented")
    }

    override fun search(term: String, callback: (List<Lembrete>) -> Unit) {
        var sql = "SELECT * FROM $TABLE_LEMBRETES"
        var args: Array<String>? = null

        if(term.isNotEmpty()){
            sql += " WHERE $COLUMN_TITULO LIKE ?"
            args = arrayOf("%$term%")
        }

        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)

        val lembretes = ArrayList<Lembrete>()

        while (cursor.moveToNext()){
            val lembrete = lembreteFromCursor(cursor)
            lembretes.add(lembrete)
        }
        cursor.close()
        db.close()
        callback(lembretes)
    }

    private fun lembreteFromCursor(cursor: Cursor): Lembrete{
        val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT))
        val texto = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEXT))
        val prioridade = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRIORIDADE))
        val data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA))

        return Lembrete(titulo, texto, prioridade, data)
    }
}