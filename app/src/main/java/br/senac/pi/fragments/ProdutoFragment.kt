package br.senac.pi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.senac.pi.R
import br.senac.pi.databinding.FragmentProdutoBinding
import br.senac.pi.databinding.ListaLateralBinding
import br.senac.pi.model.DetalheProdutoResponse
import br.senac.pi.model.Produto
import br.senac.pi.model.ProdutoResponse
import br.senac.pi.service.API
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.*

class ProdutoFragment(val PRODUTO_ID: Int) : Fragment() {

    lateinit var binding: FragmentProdutoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentProdutoBinding.inflate(inflater)

        //Chama a função para obter o produto e mostrar na tela
        obterDadosDoProduto(PRODUTO_ID)

        return binding.root

    }

    //Obtpem dados do produto indicado
    fun obterDadosDoProduto(id: Int) {

        //Callback acionado quando a execução da API concluir
        val callback = object : Callback<DetalheProdutoResponse> {

            //Chamada quando o endpoint responder
            override fun onResponse(call: Call<DetalheProdutoResponse>, response: Response<DetalheProdutoResponse>) {

                //Verifica se a resposta teve sucesso
                if (response.isSuccessful) {
                    //Obtém os dados do produto em formato de modelo
                    val produto = response.body()
                    //Chama a função para atualizar a tela
                    atualizarUI(produto?.produto)
                }
                else {
                    //Mostra uma mensagem de falha de carregamento para o usuário
                    Snackbar.make(binding.root, "Não é possível obter dados de produto",
                        Snackbar.LENGTH_LONG).show()

                    //Registra possíveis erros no console
                    Log.e("ERROR", response.errorBody().toString())
                }
            }

            //Chamada caso aconteça algum problema e não seja possível bater no endpoint
            //Ou a resposta seja incompatível
            override fun onFailure(call: Call<DetalheProdutoResponse>, t: Throwable) {

                //Mostra uma mensagem de falha de carregamento para o usuário
                Snackbar.make(binding.root, "Não foi possível se conectar ao servidor",
                    Snackbar.LENGTH_LONG).show()

                //Registra possíveis erros no console
                Log.e("ERROR", "Falha ao executar serviço", t)

            }
        }

        //Faz a chamada a API para obter um produto
        API().produto.getProduto(id).enqueue(callback)

    }


    //Utilizado para atualizar a tela quando a resposta voltar
    fun atualizarUI(produto: Produto?) {


        //Atualiza a tela com os dados do produto
        produto?.let {

            //Usado para formatação de real
//            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            binding.tituloProdutoDetalhe.text = it.PRODUTO_NOME
            binding.descricaoProdutoDetalhe.text = it.PRODUTO_DESC

            binding.precoProdutoDetalhe.text = it.PRODUTO_PRECO.toString()
//            binding.txtDesconto.text = it.PRODUTO_DESCONTO.toString()
            if (it.IMAGENS != null && it?.IMAGENS?.size!! > 0) {
                Picasso.get().load(
                    "${it?.IMAGENS?.first()?.IMAGEM_URL}"
                ).into(binding.imagemProdutoDetalhe)
            }
        }

    }

}