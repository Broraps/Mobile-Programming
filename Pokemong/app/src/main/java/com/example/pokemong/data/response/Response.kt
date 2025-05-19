package com.example.pokemong.data.response

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("pokemon")
	val pokemon: List<PokemonItem?>? = null
)

data class NextEvolutionItem(

	@field:SerializedName("num")
	val num: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class PokemonItem(

	@field:SerializedName("img")
	val img: String? = null,

	@field:SerializedName("egg")
	val egg: String? = null,

	@field:SerializedName("candy")
	val candy: String? = null,

	@field:SerializedName("num")
	val num: String? = null,

	@field:SerializedName("weight")
	val weight: String? = null,

	@field:SerializedName("type")
	val type: List<String?>? = null,

	@field:SerializedName("weaknesses")
	val weaknesses: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avg_spawns")
	val avgSpawns: Int? = null,

	@field:SerializedName("multipliers")
	val multipliers: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("prev_evolution")
	val prevEvolution: List<PrevEvolutionItem?>? = null,

	@field:SerializedName("spawn_time")
	val spawnTime: String? = null,

	@field:SerializedName("height")
	val height: String? = null,

	@field:SerializedName("spawn_chance")
	val spawnChance: Any? = null,

	@field:SerializedName("candy_count")
	val candyCount: Int? = null,

	@field:SerializedName("next_evolution")
	val nextEvolution: List<NextEvolutionItem?>? = null
)

data class PrevEvolutionItem(

	@field:SerializedName("num")
	val num: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)
