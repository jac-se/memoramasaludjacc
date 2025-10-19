package com.jacc.memoramasalud.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

class SimpleSynth {
    private var track: AudioTrack? = null
    @Volatile private var playing = false
    private var playThread: Thread? = null

    fun start() {
        if (playing) return
        playing = true

        val sampleRate = 22050
        val notes = floatArrayOf(261.63f, 329.63f, 392.00f, 523.25f) // DO-MI-SOL-DO
        val samplesPerNote = sampleRate / 4
        val totalSamples = samplesPerNote * notes.size

        // Preparamos el buffer con un patrón simple
        val buffer = ShortArray(totalSamples)
        var idx = 0
        for (f in notes) {
            for (n in 0 until samplesPerNote) {
                val t = n / sampleRate.toFloat()
                val value = (sin(2 * Math.PI * f * t) * 0.25).toFloat()
                buffer[idx++] = (value * Short.MAX_VALUE).toInt().toShort()
            }
        }

        // Inicializamos el AudioTrack (modo streaming)
        val minBuf = AudioTrack.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        track = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
            .setAudioFormat(
                AudioFormat.Builder()
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .setSampleRate(sampleRate)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                    .build()
            )
            .setBufferSizeInBytes(minBuf)
            .build()

        track?.play()

        // Bucle de reproducción
        playThread = thread(start = true) {
            while (playing) {
                track?.write(buffer, 0, buffer.size)
            }
        }
    }

    fun stop() {
        playing = false
        try {
            playThread?.join(200)
        } catch (_: Exception) {}

        track?.apply {
            try {
                stop()
                release()
            } catch (_: Exception) {}
        }
        track = null
        playThread = null
    }
}
