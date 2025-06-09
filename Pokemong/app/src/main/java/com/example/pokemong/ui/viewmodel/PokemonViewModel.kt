package com.example.pokemong.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemong.data.response.Pokemon
import com.example.pokemong.data.response.PokemonResponse
import com.example.pokemong.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList


    fun searchPokemon(query: String) {
        ApiConfig.getApiService().getPokemons(100, 0).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    val filteredList = response.body()?.results?.filter {
                        it.name.contains(
                            query,
                            ignoreCase = true
                        )
                    }
                    _pokemonList.value = filteredList ?: emptyList()
                } else {
                    Log.e("PokemonViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("PokemonViewModel", "Failure: ${t.message}")
            }
        })
    }

    fun getPokemons(limit: Int, offset: Int) {
        ApiConfig.getApiService().getPokemons(limit, offset)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onResponse(
                    call: Call<PokemonResponse>,
                    response: Response<PokemonResponse>
                ) {
                    if (response.isSuccessful) {
                        // Menyimpan daftar Pokémon yang diterima dari API ke LiveData
                        _pokemonList.value = response.body()?.results ?: emptyList()
                    } else {
                        Log.e("PokemonViewModel", "Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Log.e("PokemonViewModel", "Failure: ${t.message}")
                }
            })
    }
}
