package br.senac.pi.model

data class ItemProdutoCarrinho(

    var PRODUTO_ID: Int,
    var PRODUTO_NOME: String,
    var PRODUTO_DESC: String,
    var PRODUTO_PRECO: String,
    var PRODUTO_DESCONTO: String,
    var CATEGORIA_ID: Int,
    var PRODUTO_ATIVO: Int

)
