package cisneros.memoramasaludjacc.playgames

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import cisneros.memoramasaludjacc.R
import com.google.android.gms.games.PlayGames
import kotlinx.coroutines.tasks.await

class PlayGamesSidekick(private val activity: Activity) {

    suspend fun isSignedIn(): Boolean {
        return runCatching {
            PlayGames.getGamesSignInClient(activity).isAuthenticated.await().isAuthenticated
        }.getOrDefault(false)
    }

    suspend fun signIn(): Boolean {
        return runCatching {
            PlayGames.getGamesSignInClient(activity).signIn().await()
            true
        }.getOrDefault(false)
    }

    fun unlockProgressAchievements(unlockedLevel: Int) {
        val client = PlayGames.getAchievementsClient(activity)
        if (unlockedLevel >= 5) {
            client.unlock(activity.getString(R.string.achievement_unlock_5_levels))
        }
        if (unlockedLevel >= 10) {
            client.unlock(activity.getString(R.string.achievement_unlock_10_levels))
        }
        if (unlockedLevel >= 20) {
            client.unlock(activity.getString(R.string.achievement_unlock_20_levels))
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
