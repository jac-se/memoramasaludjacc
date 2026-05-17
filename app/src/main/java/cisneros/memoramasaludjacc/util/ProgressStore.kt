package cisneros.memoramasaludjacc.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val KEY_UNLOCKED_LEVEL = intPreferencesKey("unlocked_level")

object ProgressStore {
    fun unlockedLevelFlow(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs -> prefs[KEY_UNLOCKED_LEVEL] ?: 1 }

    suspend fun unlockUpTo(context: Context, level: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_UNLOCKED_LEVEL] ?: 1
            if (level > current) prefs[KEY_UNLOCKED_LEVEL] = level
        }
    }

    private fun bestMovesKey(levelNumber: Int) = intPreferencesKey("best_moves_level_$levelNumber")

    fun bestMovesFlow(context: Context, levelNumber: Int): Flow<Int?> =
        context.dataStore.data.map { prefs -> prefs[bestMovesKey(levelNumber)] }

    suspend fun saveBestMoves(context: Context, levelNumber: Int, moves: Int) {
        context.dataStore.edit { prefs ->
            val key = bestMovesKey(levelNumber)
            val currentBest = prefs[key]
            if (currentBest == null || moves < currentBest) {
                prefs[key] = moves
            }
        }
    }
}
