package maria.incyberspace.fragmentstask.userlist

data class User(
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val imageUrl: String
)

object UserList {
    var users = mutableListOf(
        User(1, "Becky", "Lewis", "(555) 234-34-74", "https://xsgames.co/randomusers/assets/avatars/female/19.jpg"),
        User(2, "Steve", "McMackie", "(555) 354-43-34", "https://xsgames.co/randomusers/assets/avatars/male/29.jpg"),
        User(3, "Finn", "Jackson", "(555) 345-76-89", "https://xsgames.co/randomusers/assets/avatars/male/54.jpg"),
        User(4, "Rory", "Tyler", "(555) 468-23-09", "https://xsgames.co/randomusers/assets/avatars/male/64.jpg")
    )
}