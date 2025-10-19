package cisneros.memoramasaludjacc.audio

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.math.PI
import kotlin.math.sin

class SimpleSynth {
    @Volatile var enabled: Boolean = true

    fun playClick(freq: Double = 880.0, ms: Int = 80) {
        if (!enabled) return
        val sampleRate = 22050
        val count = (ms / 1000.0 * sampleRate).toInt()
        val buffer = ShortArray(count) { i ->
            val t = 2.0 * PI * freq * i / sampleRate
            (sin(t) * 0.25 * Short.MAX_VALUE).toInt().toShort()
        }
        val track = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            buffer.size * 2,
            AudioTrack.MODE_STATIC
        )
        track.write(buffer, 0, buffer.size)
        track.play()
        Thread {
            try { Thread.sleep(ms.toLong()) } finally { track.release() }
        }.start()
    }
}
