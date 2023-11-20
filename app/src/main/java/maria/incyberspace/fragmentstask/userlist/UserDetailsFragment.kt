package maria.incyberspace.fragmentstask.userlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.squareup.picasso.Picasso
import maria.incyberspace.fragmentstask.MainActivity
import maria.incyberspace.fragmentstask.R
import maria.incyberspace.fragmentstask.databinding.UserDetailsFragmentBinding

class UserDetailsFragment : Fragment() {

    private val requestKey = "CHANGE"
    private val bundleKey = "Id"
    private val binding get() = _binding!!
    private var _binding: UserDetailsFragmentBinding? = null
    companion object {
        fun newInstance(id: Int)
        = UserDetailsFragment().apply { arguments = bundleOf(bundleKey to id) }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = UserDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = requireArguments().getInt(bundleKey)
        setUserDetails(userId)
        with (binding) {
            editUserButton.setOnClickListener {
                User(userId,
                    userName.text.toString(),
                    userSurname.text.toString(),
                    userPhoneNumber.text.toString(),
                    userImageUrl.text.toString()).apply {
                        val u = UserList.users.find { it.id == this.id }
                        val index = UserList.users.indexOf(u)
                        UserList.users[index] = this
                }
                setFragmentResult(requestKey, bundleOf(bundleKey to userId))
                (activity as MainActivity).goBackToFragment(UsersListFragment::class.simpleName!!, 0)
            }
        }
    }

    private fun setUserDetails(userId: Int) {
        val user = UserList.users.find { it.id == userId }!!
        with (binding) {
            userName.setText(user.name)
            userSurname.setText(user.surname)
            userPhoneNumber.setText(user.phoneNumber)
            userImageUrl.apply {
                setText(user.imageUrl)
                addTextChangedListener(object: TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
                    override fun afterTextChanged(p0: Editable?) {
                        if (URLUtil.isValidUrl(p0.toString())) {
                            loadImage(p0.toString())
                        }
                    }

                })
            }
            loadImage(user.imageUrl)
        }

    }

    private fun loadImage(s: String) {
        Picasso
            .get()
            .load(s)
            .error(R.drawable.ic_launcher_background)
            .into(binding.userDetailsPicture)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}