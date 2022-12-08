package br.senac.pi.service

import br.senac.pi.model.PedidoResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface PedidoService {

    @POST("/api/pedido")
    fun inserirPedido(@Header("USUARIO_ID") USUARIO_ID: Int ): Call<PedidoResponse>
}