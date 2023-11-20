package maria.incyberspace.fragmentstask.userlist

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import maria.incyberspace.fragmentstask.R
import maria.incyberspace.fragmentstask.databinding.UserItemBinding

class UsersAdapter(onItemClickListener: (User) -> Unit) : ListDelegationAdapter<List<User>>(
    userItemAdapterDelegate(onItemClickListener)
)

fun userItemAdapterDelegate(onItemClickListener: (User) -> Unit) = adapterDelegateViewBinding<User, User, UserItemBinding>({
    inflater, root -> UserItemBinding.inflate(inflater, root, false)
}) {
    bind {
        itemView.setOnClickListener { onItemClickListener(item) }
        binding.apply {
            val fullName = "${item.name} ${item.surname}"
            userItemName.text = fullName
            userItemPhoneNumber.text = item.phoneNumber
            Picasso.get()
                .load(item.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(userItemPicture)
        }
    }
}