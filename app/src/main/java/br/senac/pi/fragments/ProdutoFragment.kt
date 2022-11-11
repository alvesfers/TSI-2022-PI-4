package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import br.senac.pi.R
import br.senac.pi.databinding.FragmentArtistsBinding
import br.senac.pi.databinding.FragmentProdutoBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.model.Produto
import br.senac.pi.model.ProdutoResponse
import br.senac.pi.service.API
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProdutoFragment(val idProduto: Int) : Fragment() {
    lateinit var binding: FragmentProdutoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProdutoBinding.inflate(inflater)

        atualizaProduto()
        return binding.root
    }

    fun atualizaProduto() {
        val callback = object : Callback<ProdutoResponse> {
            override fun onResponse(call: Call<ProdutoResponse>, response: Response<ProdutoResponse>) {
                if(response.isSuccessful){
                    val produtoResponse = response.body()

                    atualizaUIProduto(produtoResponse?.produto)
                }else{
                    Snackbar.make(binding.layoutProdutoDetalhe, "Não é possível atualizar os produtos",
                        Snackbar.LENGTH_LONG).show()
                    Log.e("ERROR", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<ProdutoResponse>, t: Throwable) {
                Snackbar.make(binding.layoutProdutoDetalhe, "Não foi possível atualizar os produtos",
                    Snackbar.LENGTH_LONG).show()
                Log.e("ERROR", "Falha ao executar o serviço", t)
            }
        }
        API().produto.getProduto(idProduto).enqueue(callback)
    }

    fun atualizaUIProduto(produto: Produto?) {

        binding.tituloProdutoDetalhe.text = produto?.PRODUTO_NOME
        binding.descricaoProdutoDetalhe.text = produto?.PRODUTO_DESC
        binding.precoProdutoDetalhe.text = produto?.PRODUTO_PRECO.toString()


        Picasso.get().load(produto?.produto__imagem?.first()?.IMAGEM_URL)
            .into(binding.imageProdutoDetalhe)

//                    binding.descricaoProdutoDetalhe.text = produto?.PRODUTO_DESC
//                    binding.precoProdutoDetalhe.text = produto?.PRODUTO_PRECO.toString()
        binding.buttonAdicionarAoCarrinho.setOnClickListener {


        }
    }
}