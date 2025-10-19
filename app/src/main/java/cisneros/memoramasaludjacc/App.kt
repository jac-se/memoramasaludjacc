package cisneros.memoramasaludjacc

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cisneros.memoramasaludjacc.data.ThemeRepository
import cisneros.memoramasaludjacc.ui.GameBoard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import cisneros.memoramasaludjacc.audio.SimpleSynth


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val synth = remember { SimpleSynth() }
    var audioOn by remember { mutableStateOf(true) }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Memorama Salud Jacc") },
                    actions = {
                        IconButton(onClick = {
                            audioOn = !audioOn
                            synth.enabled = audioOn        // habilita/inhabilita el sonido
                        }) {
                            Icon(
                                imageVector = if (audioOn) Icons.Filled.VolumeUp else Icons.Filled.VolumeOff,
                                contentDescription = if (audioOn) "Sonido activado" else "Sonido desactivado"
                            )
                        }
                    }
                )
            }
        ) { inner ->
            Surface(modifier = Modifier.padding(inner)) {
                GameBoard(
                    pack = ThemeRepository.defaultPacks.first(),
                    synth = synth                     // pásalo al tablero (si lo usas)
                )
            }
        }
    }
}

