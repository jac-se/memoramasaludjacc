// AudioForce.kt
package cisneros.memoramasaludjacc.audio

import android.content.Context
import android.media.*
import android.util.Log

object AudioForce {
    private const val TAG = "AudioForce"

    fun toSpeaker(context: Context) {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        try { am.stopBluetoothSco(); am.isBluetoothScoOn = false } catch (_: Exception) {}
        try { am.isSpeakerphoneOn = true } catch (_: Exception) {}
        try { am.mode = AudioManager.MODE_NORMAL } catch (_: Exception) {}

        // Sube un paso y muestra UI
        am.adjustStreamVolume(
            AudioManager.STREAM_MUSIC,
            AudioManager.ADJUST_RAISE,
            AudioManager.FLAG_SHOW_UI
        )

        // “Unmute” defensivo (algunos OEMs)
        try { am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0) } catch (_: Exception) {}
        try { am.adjustVolume(AudioManager.ADJUST_UNMUTE, 0) } catch (_: Exception) {}

        Log.i(TAG, "Altavoz forzado. speakerOn=${am.isSpeakerphoneOn} mode=${am.mode}")
    }

    fun preferredSpeakerFor(player: AudioRouting, context: Context) {
        val am = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val speaker = am.getDevices(AudioManager.GET_DEVICES_OUTPUTS).firstOrNull {
            it.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER
        }
        if (speaker != null) {
            try {
                // Disponible en MediaPlayer, SoundPool y AudioTrack (todos implementan AudioRouting)
                player.preferredDevice = speaker
                Log.i(TAG, "preferredDevice=BUILTIN_SPEAKER set OK")
            } catch (e: Throwable) {
                Log.w(TAG, "preferredDevice set FAILED: ${e.message}")
            }
        } else {
            Log.w(TAG, "No se encontró BUILTIN_SPEAKER en outputs")
        }
    }
}
