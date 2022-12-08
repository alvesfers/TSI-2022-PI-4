package br.senac.pi.model

data class PedidoResponse(

    var status: String,
    var mensagem: String,
    var pedido: List<Pedido>
)
