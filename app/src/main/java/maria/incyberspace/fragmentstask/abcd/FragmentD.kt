package maria.incyberspace.fragmentstask.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.databinding.FragmentDBinding

class FragmentD : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentDBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goBackToBFragmentButton.setOnClickListener { goBackToBButton() }
    }

    private fun goBackToBButton() {
        with (activity as MainActivity) {
            goBackToFragment(FragmentB::class.simpleName!!)
            startFragment(FragmentB())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}