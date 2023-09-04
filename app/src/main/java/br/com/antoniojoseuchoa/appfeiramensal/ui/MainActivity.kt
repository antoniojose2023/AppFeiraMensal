package br.com.antoniojoseuchoa.appfeiramensal.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

import androidx.core.view.MenuProvider
import br.com.antoniojoseuchoa.appfeiramensal.R
import br.com.antoniojoseuchoa.appfeiramensal.data.ProdutoDao
import br.com.antoniojoseuchoa.appfeiramensal.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.appfeiramensal.model.Produto
import com.afollestad.materialdialogs.MaterialDialog

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val produtoDao by lazy { ProdutoDao(this)  }
    private val adapterProduto by lazy { AdapterProduto()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar( binding.toolbar )

        addMenuProvider(
            object : MenuProvider{
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_main, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when(menuItem.itemId){
                        R.id.menu_cadastro -> {
                            startActivity(Intent(applicationContext, CadastroProdutoActivity::class.java))
                        }

                    }

                    return true
                }

            }
        )


        listenerUpdate()
        listenerDelete()

    }

    fun listenerUpdate(){
        adapterProduto.onclickItemUpdate = {
             val intent = Intent(this, AtualizarProdutoActivity::class.java)
             intent.putExtra("produto", it)
             startActivity(intent)
        }
    }

    fun listenerDelete(){
        adapterProduto.onclickItemDelete= {
               exibeDialogDelete(it)
        }
    }

    override fun onStart() {
        exibirDados()
        super.onStart()
    }

    fun exibirDados(){
        val list =  produtoDao.getProduto()
        adapterProduto.addProduto( list )
        binding.rvItensCompra.adapter = adapterProduto
    }


    private fun exibeDialogDelete(produto: Produto){
//        val dialog = AlertDialog.Builder(applicationContext)
//        dialog.setTitle("Excluir item")
//        dialog.setMessage("Deseja Realmente exlcuir este item da lista?")
//        dialog.setPositiveButton("Sim"){dialog, which ->
//
//        }
//        dialog.setNegativeButton("Não"){dialog, which ->
//
//        }
//
//        dialog.create()
//        dialog.show()

        val dialog = MaterialDialog(this)
            .title(R.string.dialog_title)
            .message(R.string.dialog_mensage)
            .positiveButton(R.string.dialog_sim) {
                   if(produtoDao.delete(produto)){
                        Toast.makeText(this, "Produto excluído com sucesso.", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity::class.java))
                   }else{
                       Toast.makeText(this, "Erro ao excluir o produto.", Toast.LENGTH_LONG).show()
                   }
            }
            .negativeButton (R.string.dialog_nao){

            }

        dialog.show()



    }

}