package com.example.pokemong.data.retrofit

import com.example.pokemong.data.response.PokemonDetail
import com.example.pokemong.data.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("pokemon")
    fun getPokemons(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonResponse>

    @GET("pokemon/{name}")
    fun getPokemonDetail(@Path("name") name: String): Call<PokemonDetail>
}
