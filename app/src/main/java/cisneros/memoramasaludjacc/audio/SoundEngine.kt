// app/src/main/java/cisneros/memoramasaludjacc/audio/SoundEngine.kt
package cisneros.memoramasaludjacc.audio

import android.content.Context
import android.media.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import kotlin.math.PI
import kotlin.math.sin

class SoundEngine(private val context: Context) {
    private var loopPlayer: MediaPlayer? = null
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var focusReq: AudioFocusRequest? = null
    private var cachedWav: File? = null

    // === API pública ===
    fun playBeep() {
        ensureWav()
        val f = cachedWav ?: return
        val fis = FileInputStream(f)
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA) // más “aceptado” por MIUI
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        // Foco TRANSIENT para beep corto
        requestFocus(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
        MediaPlayer().apply {
            setAudioAttributes(attrs)
            setDataSource(fis.fd)
            setOnPreparedListener { it.start() }
            setOnCompletionListener { mp ->
                mp.release()
                abandonFocus()
                try { fis.close() } catch (_: Exception) {}
            }
            prepareAsync()
        }
    }

    fun startLoop() {
        ensureWav()
        val f = cachedWav ?: return
        stopLoop()
        requestFocus(AudioManager.AUDIOFOCUS_GAIN) // foco normal para loop
        val fis = FileInputStream(f)
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        loopPlayer = MediaPlayer().apply {
            setAudioAttributes(attrs)
            isLooping = true
            setDataSource(fis.fd)
            setOnPreparedListener { it.start() }
            setOnCompletionListener { /* loop */ }
            setOnErrorListener { _, _, _ ->
                try { fis.close() } catch (_: Exception) {}
                true
            }
            prepareAsync()
        }
    }

    fun stopLoop() {
        try { loopPlayer?.stop() } catch (_: Exception) {}
        try { loopPlayer?.release() } catch (_: Exception) {}
        loopPlayer = null
        abandonFocus()
    }

    // === Internos ===
    private fun requestFocus(gain: Int): Boolean {
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()
        val req = AudioFocusRequest.Builder(gain)
            .setAudioAttributes(attrs)
            .setOnAudioFocusChangeListener { /* opcional */ }
            .build()
        focusReq = req
        return audioManager.requestAudioFocus(req) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    private fun abandonFocus() {
        focusReq?.let { audioManager.abandonAudioFocusRequest(it) }
        focusReq = null
    }

    private fun ensureWav() {
        if (cachedWav != null && cachedWav!!.exists()) return
        val out = File(context.cacheDir, "tone_440_1s.wav")
        val wav = makeWav(
            sampleRate = 44100,
            freqHz = 880.0,    // 880Hz más fácil de oír en parlantes chicos
            seconds = 1.0,
            amplitude = 0.9f
        )
        FileOutputStream(out).use { it.write(wav) }
        cachedWav = out
    }

    // WAV PCM16 mono
    private fun makeWav(sampleRate: Int, freqHz: Double, seconds: Double, amplitude: Float): ByteArray {
        val n = (sampleRate * seconds).toInt()
        val data = ByteArray(44 + n * 2)
        fun putI(off: Int, v: Int) { data[off]=(v).toByte(); data[off+1]=(v ushr 8).toByte(); data[off+2]=(v ushr 16).toByte(); data[off+3]=(v ushr 24).toByte() }
        fun putS(off: Int, v: Int) { data[off]=(v).toByte(); data[off+1]=(v ushr 8).toByte() }

        data[0]='R'.code.toByte(); data[1]='I'.code.toByte(); data[2]='F'.code.toByte(); data[3]='F'.code.toByte()
        putI(4, 36 + n*2)
        data[8]='W'.code.toByte(); data[9]='A'.code.toByte(); data[10]='V'.code.toByte(); data[11]='E'.code.toByte()
        data[12]='f'.code.toByte(); data[13]='m'.code.toByte(); data[14]='t'.code.toByte(); data[15]=' '.code.toByte()
        putI(16,16); putS(20,1); putS(22,1); putI(24,sampleRate); putI(28,sampleRate*2); putS(32,2); putS(34,16)
        data[36]='d'.code.toByte(); data[37]='a'.code.toByte(); data[38]='t'.code.toByte(); data[39]='a'.code.toByte()
        putI(40, n*2)

        var w = 44
        val twoPiF = 2.0 * PI * freqHz
        for (i in 0 until n) {
            val t = i.toDouble() / sampleRate
            val s = (sin(twoPiF * t) * amplitude).coerceIn(-1.0, 1.0)
            val pcm = (s * Short.MAX_VALUE).toInt()
            data[w++] = (pcm and 0xff).toByte()
            data[w++] = ((pcm shr 8) and 0xff).toByte()
        }
        return data
    }
}
