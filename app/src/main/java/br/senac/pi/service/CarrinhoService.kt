package br.senac.pi.service

import br.senac.pi.model.CarrinhoResponse
import retrofit2.Call
import retrofit2.http.GET

interface CarrinhoService {
    @GET("/api/carrinho/")
    fun listar(): Call<CarrinhoResponse>
}