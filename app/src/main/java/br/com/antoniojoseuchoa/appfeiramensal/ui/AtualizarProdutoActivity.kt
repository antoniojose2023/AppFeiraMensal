package br.com.antoniojoseuchoa.appfeiramensal.ui

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.antoniojoseuchoa.appfeiramensal.R
import br.com.antoniojoseuchoa.appfeiramensal.data.ProdutoDao
import br.com.antoniojoseuchoa.appfeiramensal.databinding.ActivityAtualizarProdutoBinding
import br.com.antoniojoseuchoa.appfeiramensal.model.Produto

class AtualizarProdutoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAtualizarProdutoBinding.inflate(layoutInflater) }
    private val produtoDao by lazy { ProdutoDao(this)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //recuperar produto da tela de listagem
        val bundle = intent.extras
        val produto = if(VERSION.SDK_INT >= VERSION_CODES.TIRAMISU){
                          bundle?.getSerializable("produto", Produto::class.java) as Produto
        }else{
                          bundle?.getSerializable("produto") as Produto
        }

        //popular tela de atualização de produto
        popularDados(produto)

        binding.buttonAtualizar.setOnClickListener {
             val nomeProduto = binding.editInputProdutoUpdate.text.toString()
             val qtd = binding.editInputQuantidadeUpdate.text.toString()
             val id = produto.id

             if(nomeProduto.isNotEmpty() && qtd.isNotEmpty()){
                   val qtdProduto = qtd.toInt()
                   val produto = Produto(id, nomeProduto, qtdProduto)

                   if(produtoDao.update(produto)){
                         Toast.makeText(this, "Produto atualizado com sucesso", Toast.LENGTH_LONG).show()
                         startActivity(Intent(this, MainActivity::class.java))
                         finish()
                   }else{
                       Toast.makeText(this, "Erro ao atualizado produto", Toast.LENGTH_LONG).show()
                   }
             }
        }

    }

    fun popularDados(produto: Produto){

         with(binding){
             editInputProdutoUpdate.setText(produto.nomeProduto)
             editInputQuantidadeUpdate.setText(produto.qtd.toString())
         }
    }

}