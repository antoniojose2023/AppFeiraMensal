package br.com.antoniojoseuchoa.appfeiramensal.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.appfeiramensal.databinding.ItemCompraBinding
import br.com.antoniojoseuchoa.appfeiramensal.model.Produto

class AdapterProduto: RecyclerView.Adapter<AdapterProduto.ViewHolderProduto>() {

    private var list = listOf<Produto>()
    var onclickItemUpdate: (Produto) -> Unit = {}
    var onclickItemDelete: (Produto) -> Unit = {}

    fun addProduto(list: List<Produto>){
           this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProduto {
         val layoutInflater = LayoutInflater.from(parent.context)
         val binding = ItemCompraBinding.inflate(layoutInflater, parent, false)
         return ViewHolderProduto( binding )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderProduto, position: Int) {
        val item = list[position]
        holder.binding.apply {
            tvNomeProduto.text = item.nomeProduto
            tvQTDItem.text = " Qtd: ${item.qtd}"

            imageButtonEdit.setOnClickListener {
                  onclickItemUpdate(item)
            }

            imageButtonDelete.setOnClickListener {
                onclickItemDelete(item)
            }
        }
    }

    inner class ViewHolderProduto(val binding: ItemCompraBinding): RecyclerView.ViewHolder(binding.root){

    }

}