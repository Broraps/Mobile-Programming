package com.example.pokemong.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.pokemong.data.response.PokemonDetail
import com.example.pokemong.databinding.FragmentDetailBinding
import com.example.pokemong.ui.viewmodel.PokemonDetailViewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: PokemonDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupBackPressHandler()
        loadPokemonDetails()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(PokemonDetailViewModel::class.java)
    }

    private fun setupBackPressHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    private fun loadPokemonDetails() {
        arguments?.getString("pokemonName")?.let { pokemonName ->
            viewModel.getPokemonDetail(pokemonName)
            observePokemonDetails()
        }
    }

    private fun observePokemonDetails() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { detail ->
            detail?.let { displayPokemonDetails(it) }
        }
    }

    private fun displayPokemonDetails(pokemon: PokemonDetail) {
        with(binding) {
            tvName.text = pokemon.name
            tvHeight.text = formatHeight(pokemon.height)
            tvWeight.text = formatWeight(pokemon.weight)
            tvAttack.text = getAttackStat(pokemon)
            loadPokemonImage(pokemon.sprites.frontDefault)
        }
    }

    private fun formatHeight(heightInDecimeters: Int): String {
        val heightInMeters = heightInDecimeters.toFloat() / 10
        return if (heightInMeters < 1) {
            "Height: ${"%.0f".format(heightInMeters * 100)} cm"
        } else {
            "Height: ${"%.1f".format(heightInMeters)} m"
        }
    }

    private fun formatWeight(weightInHectograms: Int): String {
        val weightInKg = weightInHectograms.toFloat() / 10
        return if (weightInKg < 1) {
            "Weight: ${"%.0f".format(weightInKg * 1000)} g"
        } else {
            "Weight: ${"%.1f".format(weightInKg)} kg"
        }
    }

    private fun getAttackStat(pokemon: PokemonDetail): String {
        val attack = pokemon.stats.find { it.stat.name == "attack" }
        return "Attack: ${attack?.baseStat ?: "N/A"}"
    }

    private fun loadPokemonImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(binding.ivPokemon)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}