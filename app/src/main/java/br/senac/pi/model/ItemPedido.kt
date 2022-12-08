package br.senac.pi.model

data class ItemPedido(

    var ITEM_PRECO: String,
    var ITEM_QTD: Int,
    var PRODUTO: List<ItemProdutoCarrinho>

)
