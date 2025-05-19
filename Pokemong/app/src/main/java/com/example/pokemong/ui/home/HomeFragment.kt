package com.example.pokemong.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemong.R
import com.example.pokemong.databinding.FragmentHomeBinding
import com.example.pokemong.ui.viewmodel.PokemonViewModel
import com.example.pokemong.data.response.Pokemon
import com.example.pokemong.ui.adapter.PokemonAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var pokemonViewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        val adapter = PokemonAdapter { pokemon ->
            // Navigate to DetailFragment with the pokemon's name
            val bundle = Bundle().apply {
                putString("pokemonName", pokemon.name)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = adapter

        pokemonViewModel.pokemonList.observe(viewLifecycleOwner) { pokemonList ->
            adapter.submitList(pokemonList)
        }

        pokemonViewModel.getPokemons(200, 0)

        binding.searchView.setQueryHint("Search Pokémon")
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                pokemonViewModel.searchPokemon(newText.orEmpty())
                return true
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
