package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.senac.pi.R
import br.senac.pi.databinding.FragmentRecentsBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.databinding.ProdutoCarrinhoBinding
import br.senac.pi.model.CarrinhoResponse
import br.senac.pi.model.Categoria
import br.senac.pi.model.Produto
import br.senac.pi.service.API
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentsFragment : Fragment() {

    lateinit var binding: FragmentRecentsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecentsBinding.inflate(inflater)

        atualizaCarrinho()
        return binding.root
    }

    fun atualizaCarrinho(){

        val callback = object : Callback<CarrinhoResponse> {
            override fun onResponse(call: Call<CarrinhoResponse>, response: Response<CarrinhoResponse>) {
                if(response.isSuccessful){
                    val listaProdutosCarrinho = response.body()

                    atualizaUICarrinho(listaProdutosCarrinho)
                }else{
                    Snackbar.make(binding.containerCarrinho, "Não é possível atualizar os produtos",
                        Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<CarrinhoResponse>, t: Throwable) {
                Snackbar.make(binding.containerCarrinho, "Não foi possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar o serviço", t)
            }

        }
        API().carrinho.listar().enqueue(callback)

    }

    fun atualizaUICarrinho(lista: CarrinhoResponse?) {
        binding.containerCarrinho.removeAllViews()

//        lista?.let {
//            val cardBinding = ProdutoCarrinhoBinding.inflate(layoutInflater)
//
//            cardBinding.txtProduto.text = it.carrinho.toString()
//
//            binding.containerCarrinho.addView(cardBinding.root)
//        }

    }

}