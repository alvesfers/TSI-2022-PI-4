package br.senac.pi.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    private val retrofit: Retrofit
        get() {
            return Retrofit
                .Builder()
                .baseUrl("https://alpha2go.alpha2go-fome-de-poder.repl.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    val produto: ProdutoService
    get() {
        return retrofit.create(ProdutoService::class.java)
    }

    val categoria: CategoriaService
    get() {
        return retrofit.create(CategoriaService::class.java)
    }
}