package raffiargianda.dummyapi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import raffiargianda.dummyapi.ui.adapter.ProductAdapter

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ProductAdapter { product ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(product)
            findNavController().navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        viewModel.products.observe(viewLifecycleOwner) { products ->
            adapter.submitList(products)
        }

        binding.searchBar.addTextChangedListener {
            val query = it.toString()
            viewModel.searchProducts(query)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
