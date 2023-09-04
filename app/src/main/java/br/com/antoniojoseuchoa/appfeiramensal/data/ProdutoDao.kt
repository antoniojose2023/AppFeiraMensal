package br.com.antoniojoseuchoa.appfeiramensal.data

import android.content.Context
import br.com.antoniojoseuchoa.appfeiramensal.Constantes
import br.com.antoniojoseuchoa.appfeiramensal.model.Produto

class ProdutoDao(private val context: Context): IProdutoDAO {

    val db = Database(context)
    val writer = db.writableDatabase
    val reader = db.readableDatabase

    override fun save(produto: Produto): Boolean {
        val nome = produto.nomeProduto
        val quantidade = produto.qtd
        val id = produto.id

        val sql = "INSERT INTO ${Constantes.TABELA_PRODUTO} (${Constantes.COLUNA_NOME}, ${Constantes.COLUNA_QTD}) VALUES ('$nome', $quantidade)"

        try{
            writer.execSQL(sql)
            return true

        }catch (ex: Exception){
             ex.printStackTrace()
        }

        return false
    }

    override fun delete(produto: Produto): Boolean {
        val id = produto.id
        val sql = "DELETE FROM ${Constantes.TABELA_PRODUTO} WHERE ${Constantes.COLUNA_ID}= $id"

        try{
            writer.execSQL(sql)
            return true

        }catch (ex: Exception){
            ex.printStackTrace()
        }

        return false
    }

    override fun update(produto: Produto): Boolean {
        val nome = produto.nomeProduto
        val quantidade = produto.qtd
        val id = produto.id

        val sql = "UPDATE ${Constantes.TABELA_PRODUTO} SET ${Constantes.COLUNA_NOME}='$nome', ${Constantes.COLUNA_QTD}= $quantidade WHERE ${Constantes.COLUNA_ID} = $id;"

        try{
            writer.execSQL(sql)
            return true

        }catch (ex: Exception){
            ex.printStackTrace()
        }

        return false
    }

    override fun getProduto(): List<Produto> {
        val sql = "SELECT * FROM ${Constantes.TABELA_PRODUTO}"
        val cursor = reader.rawQuery(sql, null)

        val id = cursor.getColumnIndex("${Constantes.COLUNA_ID}")
        val produto = cursor.getColumnIndex("${Constantes.COLUNA_NOME}")
        val quantidade = cursor.getColumnIndex("${Constantes.COLUNA_QTD}")

        val listProduto = mutableListOf<Produto>()

        while (cursor.moveToNext()){
            val idProduto = cursor.getInt(id)
            val nomeProduto = cursor.getString(produto)
            val qtdProduto = cursor.getInt(quantidade)

            listProduto.add(
                Produto(idProduto, nomeProduto, qtdProduto)
            )
        }

        return listProduto
    }
}