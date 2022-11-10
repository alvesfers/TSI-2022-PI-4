package br.senac.pi.service

import br.senac.pi.model.Categoria
import retrofit2.Call
import retrofit2.http.GET

interface CategoriaService {
    @GET("/api/categoria/") //Call<T>(retrofit2) //Se a anotação não importar automático aperte alt + enter
    fun listar(): Call<List<Categoria>>
}