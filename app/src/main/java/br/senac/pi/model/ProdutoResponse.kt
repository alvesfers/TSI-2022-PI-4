package br.senac.pi.model

data class ProdutoResponse(
    var status: String,
    var produtos: List<Produto>
)
