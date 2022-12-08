package br.senac.pi.service

import br.senac.pi.model.CarrinhoResponse
import br.senac.pi.model.ItemListaCarrinhoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface CarrinhoService {

    //Outro exemplo de parâmetro de corpo de requisição, normalmente utilizado
//    //no envio por requisições de inserção
//    @POST("/api/carrinho")
//    fun inserir(@Body produto: Carrinho): Call<Carrinho>

    @POST("/api/carrinho")
    fun inserir(@Query("PRODUTO_ID") PRODUTO_ID: Int, @Query("ITEM_QTD") ITEM_QTD: Int, @Header("USUARIO_ID") USUARIO_ID: Int ): Call<CarrinhoResponse>

    @GET("/api/carrinho")
    fun listarCarrinho(@Header("USUARIO_ID") USUARIO_ID: Int ): Call<ItemListaCarrinhoResponse>

}