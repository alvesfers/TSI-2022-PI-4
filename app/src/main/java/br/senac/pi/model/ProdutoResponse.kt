package br.senac.pi.model

data class ProdutoResponse (
    var status: Int,
    var mensagem: String,
    var produtos: List<Produto>,
    var produto: Produto
)
