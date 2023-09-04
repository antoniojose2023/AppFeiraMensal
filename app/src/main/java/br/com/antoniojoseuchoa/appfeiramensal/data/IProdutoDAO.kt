package br.com.antoniojoseuchoa.appfeiramensal.data

import br.com.antoniojoseuchoa.appfeiramensal.model.Produto

interface IProdutoDAO {
    fun save(produto: Produto): Boolean
    fun delete(produto: Produto): Boolean
    fun update(produto: Produto): Boolean
    fun getProduto(): List<Produto>
}