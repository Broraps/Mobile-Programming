package com.example.pokemong.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemong.data.response.PokemonDetail
import com.example.pokemong.data.retrofit.ApiConfig
import retrofit2.*

class PokemonDetailViewModel : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail> = _pokemonDetail

    fun getPokemonDetail(name: String) {
        ApiConfig.getApiService().getPokemonDetail(name).enqueue(object : Callback<PokemonDetail> {
            override fun onResponse(
                call: Call<PokemonDetail>,
                response: Response<PokemonDetail>
            ) {
                if (response.isSuccessful) {
                    _pokemonDetail.value = response.body()
                } else {
                    Log.e("PokemonDetailViewModel", "Error: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PokemonDetail>, t: Throwable) {
                Log.e("PokemonDetailViewModel", "Failure: ${t.message}")
            }
        })
    }
}
