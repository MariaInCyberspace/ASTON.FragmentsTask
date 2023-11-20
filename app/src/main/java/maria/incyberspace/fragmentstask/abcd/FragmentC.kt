package maria.incyberspace.fragmentstask.abcd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentCBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = requireArguments().getString("text")
        with (binding) {
            helloCFragText.text = text
            goToAFragmentButton.setOnClickListener { goBackToAFragment() }
            goToDFragmentButton.setOnClickListener { goToDFragment() }
        }
    }

    private fun goToDFragment() {
        (activity as MainActivity).startFragment(FragmentD())
    }

    private fun goBackToAFragment() {
        (activity as MainActivity).goBackToFragment(FragmentB::class.simpleName!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}