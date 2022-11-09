package br.senac.pi.service

import br.senac.pi.model.ProdutoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProdutoService {
    @GET("/api/produto/") //Call<T>(retrofit2) //Se a anotação não importar automático aperte alt + enter
    fun listar(): Call<ProdutoResponse>
}