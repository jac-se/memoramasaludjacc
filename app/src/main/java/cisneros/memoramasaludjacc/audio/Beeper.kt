// app/src/main/java/cisneros/memoramasaludjacc/audio/Beeper.kt
package cisneros.memoramasaludjacc.audio

import android.media.AudioManager
import android.media.ToneGenerator

object Beeper {
    fun beep(ms: Int = 400) {
        val tg = ToneGenerator(AudioManager.STREAM_MUSIC, 100) // 100 = volumen
        tg.startTone(ToneGenerator.TONE_PROP_BEEP, ms)
    }
}
