package br.senac.pi.model

data class Produto(
    var PRODUTO_ID: Long,
    var PRODUTO_NOME: String,
    var PRODUTO_DESC: String,
    var PRODUTO_PRECO: Float,
    var PRODUTO_DESCONTO: Float,
    var CATEGORIA_ID: Long,
    var PRODUTO_ATIVO: Float
)
