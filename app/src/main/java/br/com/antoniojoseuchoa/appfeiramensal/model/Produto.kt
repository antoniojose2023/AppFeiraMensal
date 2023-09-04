package br.com.antoniojoseuchoa.appfeiramensal.model

import java.io.Serializable

class Produto(
    val id: Int = -1,
    val nomeProduto: String,
    val qtd: Int
): Serializable {}