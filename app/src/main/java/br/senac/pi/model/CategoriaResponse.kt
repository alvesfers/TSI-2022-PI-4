package br.senac.pi.model

data class CategoriaResponse(
    var status : Int,
    var mensagem: String,
    var categoria: List<Categoria>
)
