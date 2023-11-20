package maria.incyberspace.fragmentstask.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentBBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            goBackToAFragmentButton.setOnClickListener { goBackToAFragment() }
            goToCFragmentButton.setOnClickListener { startCFragment() }
        }
    }

    private fun startCFragment() {
        (activity as MainActivity).startFragment(FragmentC().apply {
            arguments = bundleOf("text" to "Hello Fragment C")
        })
    }

    private fun goBackToAFragment() {
        (activity as MainActivity).goBackToFragment(FragmentB::class.simpleName!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}