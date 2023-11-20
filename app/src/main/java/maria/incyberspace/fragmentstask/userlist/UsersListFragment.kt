package maria.incyberspace.fragmentstask.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.databinding.UserslistFragmentBinding

class UsersListFragment : Fragment() {

    private val requestKey = "CHANGE"
    private val bundleKey = "Id"
    private val binding get() = _binding!!
    private var _binding: UserslistFragmentBinding? = null
    private var users = UserList.users
    private lateinit var adapter: UsersAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = UserslistFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(requestKey) { _, bundle ->
            val userId = bundle.getInt(bundleKey)
            val user = users.find { it.id == userId }
            val index = users.indexOf(user)
            users[index] = UserList.users[index]
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {
        adapter = UsersAdapter { onItemClick(it) }
        with (binding) {
            usersRecyclerView.apply {
                adapter = this@UsersListFragment.adapter
                (adapter as UsersAdapter).apply {
                    items = users
                }
                layoutManager = LinearLayoutManager(this@UsersListFragment.context, RecyclerView.VERTICAL, false)
                itemAnimator = DefaultItemAnimator()
            }
        }
    }

    private fun onItemClick(u: User) {
        (activity as MainActivity).startFragment(UserDetailsFragment.newInstance(u.id))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}