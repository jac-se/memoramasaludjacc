package cisneros.memoramasaludjacc.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val KEY_UNLOCKED_LEVEL = intPreferencesKey("unlocked_level")
private val KEY_STORY_LEVEL    = intPreferencesKey("story_current_level")
private val KEY_DIFFICULTY     = intPreferencesKey("adaptive_difficulty") // 0=Easy,1=Normal,2=Hard,3=Expert

object ProgressStore {

    // ─── Unlocked Levels ──────────────────────────────────────────────────────

    fun unlockedLevelFlow(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs -> prefs[KEY_UNLOCKED_LEVEL] ?: 1 }

    suspend fun unlockUpTo(context: Context, level: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_UNLOCKED_LEVEL] ?: 1
            if (level > current) prefs[KEY_UNLOCKED_LEVEL] = level
        }
    }

    // ─── Best Moves ───────────────────────────────────────────────────────────

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

    // ─── Story Mode — Current Level ───────────────────────────────────────────

    /** The level the player is currently on in Story Mode (1-indexed). */
    fun storyLevelFlow(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs -> prefs[KEY_STORY_LEVEL] ?: 1 }

    suspend fun saveStoryLevel(context: Context, level: Int) {
        context.dataStore.edit { prefs -> prefs[KEY_STORY_LEVEL] = level }
    }

    // ─── Adaptive Difficulty ──────────────────────────────────────────────────

    /**
     * Returns the current adaptive difficulty as a 0-3 integer:
     *   0 = Easy (3 pairs / 6 cards)
     *   1 = Normal (4 pairs / 8 cards)
     *   2 = Hard (6 pairs / 12 cards — cross-level mix)
     *   3 = Expert (8 pairs / 16 cards — cross-level mix)
     */
    fun difficultyFlow(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs -> prefs[KEY_DIFFICULTY] ?: 1 } // default: Normal

    suspend fun saveDifficulty(context: Context, difficulty: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_DIFFICULTY] = difficulty.coerceIn(0, 3)
        }
    }

    /**
     * Adjusts the difficulty based on the player's performance after completing a level.
     * @param moves       number of moves used by the player
     * @param targetMoves optimal target moves for this level/difficulty
     */
    suspend fun updateAdaptiveDifficulty(context: Context, moves: Int, targetMoves: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[KEY_DIFFICULTY] ?: 1
            val next = when {
                moves < targetMoves - 1 -> (current + 1).coerceAtMost(3) // excellent → harder
                moves > targetMoves     -> (current - 1).coerceAtLeast(0) // poor → easier
                else                    -> current                          // average → same
            }
            prefs[KEY_DIFFICULTY] = next
        }
    }
}
