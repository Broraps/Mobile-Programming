package raffiargianda.latnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import raffiargianda.latnavigation.databinding.ActivityNewsFragmentBinding

class NewsFragment : Fragment() {
    private var _binding: ActivityNewsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityNewsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    Tugas
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    binding.btnNewsActivity.setOnClickListener { view ->
        view.findNavController().navigate(R.id.action_newsFragment_to_newsActivity)
    }
    }
}
