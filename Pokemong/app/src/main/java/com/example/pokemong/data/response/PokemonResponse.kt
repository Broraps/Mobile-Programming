package com.example.pokemong.data.response

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @field:SerializedName("results")
    val results: List<Pokemon>
)

data class Pokemon(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String
)

data class PokemonDetail(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("height")
    val height: Int,

    @field:SerializedName("weight")
    val weight: Int,

    @field:SerializedName("stats")
    val stats: List<PokemonStats>,

    @field:SerializedName("sprites")
    val sprites: PokemonSprites
)

data class PokemonStats(
    @field:SerializedName("stat")
    val stat: Stat,

    @field:SerializedName("base_stat")
    val baseStat: Int
)

data class Stat(
    @field:SerializedName("name")
    val name: String
)

data class PokemonSprites(
    @field:SerializedName("front_default")
    val frontDefault: String
)
