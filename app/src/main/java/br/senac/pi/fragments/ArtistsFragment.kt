package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import br.senac.pi.R
import br.senac.pi.databinding.FragmentArtistsBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.model.Produto
import br.senac.pi.model.ProdutoResponse
import br.senac.pi.service.API
import br.senac.pi.service.ProdutoService
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArtistsFragment : Fragment() {

    lateinit var binding: FragmentArtistsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentArtistsBinding.inflate(inflater)


        atualizaProdutos()
        return binding.root
    }

    fun atualizaProdutos(){

        val callback = object : Callback<ProdutoResponse> {
            override fun onResponse(call: Call<ProdutoResponse>, response: Response<ProdutoResponse>) {
                if(response.isSuccessful){
                    val listaProdutos = response.body()
                    atualizaUI(listaProdutos?.produtos)
                }else{
                    Snackbar.make(binding.linearHorizontalProdutos, "Não é possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ProdutoResponse>, t: Throwable) {
                Snackbar.make(binding.linearHorizontalProdutos, "Não foi possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar o serviço", t)
            }

        }
        API().produto.listar().enqueue(callback)

    }

    fun atualizaUI(lista: List<Produto>?) {
        binding.linearHorizontalProdutos.removeAllViews()

        lista?.forEach {
            val cardBinding = ListaLateralBinding.inflate(layoutInflater)

            cardBinding.nomeProdutoLateral.text = it.PRODUTO_NOME

            cardBinding.root.setOnClickListener{ cartaoLateral ->
                //enviar para o detalhe
            }

            binding.linearHorizontalProdutos.addView(cardBinding.root)
        }
    }


}