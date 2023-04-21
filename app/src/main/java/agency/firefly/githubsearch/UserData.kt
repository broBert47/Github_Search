package agency.firefly.githubsearch

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData (
    val login: String,
    val bio: String?,
    val type: String,
    val avatar_url: String
        ) : Parcelable