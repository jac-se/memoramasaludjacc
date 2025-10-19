package cisneros.memoramasaludjacc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setVolumeControlStream(android.media.AudioManager.STREAM_MUSIC) // <—
        setContent { App() }
    }


}
