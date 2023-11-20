package maria.incyberspace.fragmentstask.abcd

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.databinding.FragmentABinding
import maria.incyberspace.fragmentstask.userlist.UsersListFragment

class FragmentA : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentABinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentFragmentManager.commit {
            setPrimaryNavigationFragment(this@FragmentA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with (binding) {
            goToBFragButton.setOnClickListener { startBFragment() }
            goToUserListButton.setOnClickListener { startUsersListFragment() }
        }
    }

    private fun startUsersListFragment() {
        (activity as MainActivity).startFragment(UsersListFragment())
    }

    private fun startBFragment() {
        (activity as MainActivity).startFragment(FragmentB())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}