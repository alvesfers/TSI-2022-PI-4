package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.senac.pi.R
import br.senac.pi.databinding.FragmentArtistsBinding
import br.senac.pi.databinding.FragmentProdutoBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.model.Categoria
import br.senac.pi.model.Produto
import br.senac.pi.service.API
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArtistsFragment : Fragment() {

    lateinit var binding: FragmentArtistsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentArtistsBinding.inflate(inflater)


        atualizaProdutos()
        atualizaCategorias()
        return binding.root
    }

    fun atualizaProdutos(){

        val callback = object : Callback<List<Produto>> {
            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                if(response.isSuccessful){
                    val listaProdutos = response.body()

                    atualizaUIProdutos(listaProdutos)
                }else{
                    Snackbar.make(binding.linearHorizontalProdutos, "Não é possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                Snackbar.make(binding.linearHorizontalProdutos, "Não foi possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar o serviço", t)
            }

        }
        API().produto.listar().enqueue(callback)

    }

    fun atualizaUIProdutos(lista: List<Produto>?) {
        binding.linearHorizontalProdutos.removeAllViews()

        lista?.forEach {
            val cardBinding = ListaLateralBinding.inflate(layoutInflater)

            cardBinding.nomeProdutoLateral.text = it.PRODUTO_NOME
            //Picasso.get().load()

            cardBinding.cartaoLateral.setOnClickListener { view ->
                val frag = ProdutoFragment(it.PRODUTO_ID)
                activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, frag)?.commit()
            }

            binding.linearHorizontalProdutos.addView(cardBinding.root)
        }

    }

    fun atualizaCategorias(){

        val callback = object : Callback<List<Categoria>> {
            override fun onResponse(call: Call<List<Categoria>>, response: Response<List<Categoria>>) {
                if(response.isSuccessful){
                    val listaCategorias = response.body()

                    atualizaUICategorias(listaCategorias)
                }else{
                    Snackbar.make(binding.linearHorizontalCategorias, "Não é possível atualizar os produtos",
                        Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
                Snackbar.make(binding.linearHorizontalCategorias, "Não foi possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()

                Log.e("ERROR", "Falha ao executar o serviço", t)
            }

        }
        API().categoria.listar().enqueue(callback)

    }

    fun atualizaUICategorias(lista: List<Categoria>?) {
        binding.linearHorizontalCategorias.removeAllViews()

        lista?.forEach {
            val cardBinding = ListaLateralBinding.inflate(layoutInflater)

            cardBinding.nomeProdutoLateral.text = it.CATEGORIA_NOME
            //Picasso.get().load()



            binding.linearHorizontalCategorias.addView(cardBinding.root)
        }

    }


}