package cisneros.memoramasaludjacc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.android.gms.games.PlayGamesSdk

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PlayGamesSdk.initialize(this)
        setContent { App() }
    }
}
