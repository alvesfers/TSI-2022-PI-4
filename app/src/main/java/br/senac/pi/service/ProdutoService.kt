package br.senac.pi.service

import br.senac.pi.model.DetalheProdutoResponse
import br.senac.pi.model.Produto
import br.senac.pi.model.ProdutoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProdutoService {
    @GET("/api/produto/") //Call<T>(retrofit2) //Se a anotação não importar automático aperte alt + enter
    fun listar(): Call<ProdutoResponse>

    @GET("/api/produto/{id}")
    fun getProduto(@Path("id") id: Int): Call<DetalheProdutoResponse>


}