package br.senac.pi.model

data class CarrinhoResponse(
    var status: Int,
    var mensagem: String,
    var carrinho: List<Produto>
)
