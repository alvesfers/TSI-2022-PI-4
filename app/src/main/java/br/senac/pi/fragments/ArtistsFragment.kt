package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.senac.pi.R
import br.senac.pi.databinding.FragmentArtistsBinding
import br.senac.pi.databinding.FragmentCategoriaLateralBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.model.Categoria
import br.senac.pi.model.CategoriaResponse
import br.senac.pi.model.Produto
import br.senac.pi.model.ProdutoResponse
import br.senac.pi.service.API
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
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

    fun atualizaProdutos() {
        val callback = object : Callback<ProdutoResponse> {
            override fun onResponse(call: Call<ProdutoResponse>, response: Response<ProdutoResponse>) {
                if(response.isSuccessful){
                    val produtoResponse = response.body()

                    atualizaUIProdutos(produtoResponse?.produtos)
                }else{
                    Snackbar.make(binding.linearLayoutProdutos, "Não é possível atualizar os produtos",
                        Snackbar.LENGTH_LONG).show()
                    Log.e("ERROR", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<ProdutoResponse>, t: Throwable) {
                Snackbar.make(binding.linearLayoutProdutos, "Não foi possível conectar ao servidor",
                    Snackbar.LENGTH_LONG).show()
                Log.e("ERROR", "Falha ao executar o serviço", t)
            }
        }
        API().produto.listar().enqueue(callback)
    }


    fun atualizaUIProdutos(lista: List<Produto>?) {

        lista?.forEach {
            val cardBinding = ListaLateralBinding.inflate(layoutInflater)

            cardBinding.nomeProdutoLateral.text = it.PRODUTO_NOME

            if (it.IMAGENS != null && it.IMAGENS?.size!! > 0) {
                Picasso.get().load(
                    "${it.IMAGENS?.first()?.IMAGEM_URL}"
                ).into(cardBinding.image)
            }

            cardBinding.root.setOnClickListener{ cartao ->
                val frag = ProdutoFragment(it.PRODUTO_ID)
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.container, frag)
                    ?.addToBackStack("Detalhe do Produto")?.commit()
            }
            //Adiciona o cartão no container para que apareça na tela
            binding.linearHorizontalProdutos.addView(cardBinding.root)
        }
    }

    fun atualizaCategorias(){

        val callback = object : Callback<CategoriaResponse> {
            override fun onResponse(call: Call<CategoriaResponse>, response: Response<CategoriaResponse>) {
                if(response.isSuccessful){
                    val listaCategorias = response.body()

                    atualizaUICategorias(listaCategorias?.categoria)
                }else{
                    Snackbar.make(binding.linearHorizontalCategorias, "Não é possível atualizar as categorias",
                        Snackbar.LENGTH_LONG).show()

                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<CategoriaResponse>, t: Throwable) {
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
            val cardBinding = FragmentCategoriaLateralBinding.inflate(layoutInflater)

            cardBinding.nomeCategoriaLateral.text = it.CATEGORIA_NOME

            binding.linearHorizontalCategorias.addView(cardBinding.root)
        }

    }


}