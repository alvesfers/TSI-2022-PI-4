package br.senac.pi.model

data class DetalheProdutoResponse(
    var status: String,
    var mensagem: String,
    var produto: Produto
)

