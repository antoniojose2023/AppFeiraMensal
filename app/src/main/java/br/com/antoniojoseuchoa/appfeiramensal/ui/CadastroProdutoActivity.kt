package br.com.antoniojoseuchoa.appfeiramensal.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.antoniojoseuchoa.appfeiramensal.R
import br.com.antoniojoseuchoa.appfeiramensal.data.Database
import br.com.antoniojoseuchoa.appfeiramensal.data.ProdutoDao
import br.com.antoniojoseuchoa.appfeiramensal.databinding.ActivityCadastroProdutoBinding
import br.com.antoniojoseuchoa.appfeiramensal.model.Produto

class CadastroProdutoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCadastroProdutoBinding.inflate(layoutInflater) }
    private val produtoDao by lazy { ProdutoDao(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.buttonSalvar.setOnClickListener {
             val nome = binding.editInputProduto.text.toString()
             val quantidade = binding.editInputQuantidade.text.toString()

             if(nome.isNotEmpty() && quantidade.isNotEmpty()){
                   val produto = Produto(-1, nome, quantidade.toInt())
                   if(produtoDao.save(produto)){
                       Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_LONG).show()
                   }else{
                       Toast.makeText(this, "erro ao salvar.", Toast.LENGTH_LONG).show()
                   }
             }else{
                 Toast.makeText(this, "Campos vázios", Toast.LENGTH_LONG).show()
             }
        }


    }
}