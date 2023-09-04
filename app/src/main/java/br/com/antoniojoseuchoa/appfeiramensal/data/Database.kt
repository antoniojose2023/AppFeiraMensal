package br.com.antoniojoseuchoa.appfeiramensal.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.antoniojoseuchoa.appfeiramensal.Constantes

class Database(val context: Context): SQLiteOpenHelper(context, "produtodb", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {

        val sql = "CREATE TABLE ${Constantes.TABELA_PRODUTO} (" +
                "${Constantes.COLUNA_ID} INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "${Constantes.COLUNA_NOME} TEXT," +
                "${Constantes.COLUNA_QTD} INTEGER" +
                ");"

         db?.execSQL( sql )

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}