package br.senac.pi.model

data class ItemListaCarrinhoResponse(

    var status: String,
    var mensagem: String,
    var carrinho: List<ItemListaCarrinho>

)
