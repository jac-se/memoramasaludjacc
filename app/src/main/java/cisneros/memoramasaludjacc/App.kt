package cisneros.memoramasaludjacc

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cisneros.memoramasaludjacc.data.ThemeRepository
import cisneros.memoramasaludjacc.playgames.PlayGamesSidekick
import cisneros.memoramasaludjacc.playgames.findActivity
import cisneros.memoramasaludjacc.ui.GameBoard
import cisneros.memoramasaludjacc.util.ProgressStore
import kotlinx.coroutines.launch

private fun targetMovesFor(levelNumber: Int, pairs: Int): Int {
    val base = pairs * 2
    val tolerance = when {
        levelNumber <= 5 -> 4
        levelNumber <= 10 -> 5
        levelNumber <= 15 -> 6
        else -> 7
    }
    return base + tolerance
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val ctx = LocalContext.current
    val activity = ctx.findActivity()
    val scope = rememberCoroutineScope()
    val levels = ThemeRepository.levels
    val unlockedLevel by ProgressStore.unlockedLevelFlow(ctx).collectAsState(initial = 1)
    val sidekick = remember(activity) { activity?.let { PlayGamesSidekick(it) } }
    var selectedLevel by remember { mutableStateOf(levels.first()) }
    var playingLevel by remember { mutableStateOf<ThemeRepository.Level?>(null) }
    var adaptiveMessage by remember { mutableStateOf("Completa un nivel para desbloquear el siguiente.") }
    var playGamesSignedIn by remember { mutableStateOf(false) }

    if (selectedLevel.number > unlockedLevel) {
        selectedLevel = levels.first { it.number <= unlockedLevel }
    }
    LaunchedEffect(sidekick) {
        if (sidekick != null) playGamesSignedIn = sidekick.isSignedIn()
    }
    LaunchedEffect(unlockedLevel, playGamesSignedIn, sidekick) {
        if (playGamesSignedIn) sidekick?.unlockProgressAchievements(unlockedLevel)
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Memorama Salud Jacc") },
                    navigationIcon = {
                        if (playingLevel != null) {
                            IconButton(onClick = { playingLevel = null }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                            }
                        }
                    }
                )
            }
        ) { inner ->
            Surface(modifier = Modifier.fillMaxSize().padding(inner)) {
                if (playingLevel == null) {
                    Column(Modifier.fillMaxSize().padding(12.dp)) {
                        Text(
                            "Selecciona nivel",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            "Desbloqueados: $unlockedLevel de ${levels.size}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                if (sidekick == null) return@Button
                                scope.launch {
                                    playGamesSignedIn = sidekick.signIn()
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = null)
                            Text(if (playGamesSignedIn) "Play Games conectado" else "Conectar Play Games")
                        }
                        Spacer(Modifier.height(10.dp))

                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(levels) { level ->
                                val unlocked = level.number <= unlockedLevel
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { if (unlocked) selectedLevel = level }
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(Modifier.weight(1f).padding(end = 8.dp)) {
                                            Text(
                                                level.title,
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = if (selectedLevel == level) FontWeight.Bold else FontWeight.Medium
                                            )
                                            Text(
                                                if (unlocked) level.description else "Completa niveles anteriores para desbloquear.",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                        if (!unlocked) {
                                            Icon(Icons.Filled.Lock, contentDescription = "Bloqueado")
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        Text(adaptiveMessage, style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = { playingLevel = selectedLevel },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Jugar ${selectedLevel.title}")
                        }
                    }
                } else {
                    val current = playingLevel!!
                    GameBoard(
                        pack = current.pack,
                        onCompleted = { moves ->
                            val currentNumber = current.number
                            val nextLevel = (currentNumber + 1).coerceAtMost(levels.size)
                            val target = targetMovesFor(currentNumber, current.pack.pairs.size)

                            scope.launch {
                                ProgressStore.saveBestMoves(ctx, currentNumber, moves)
                                ProgressStore.unlockUpTo(ctx, nextLevel)
                            }

                            adaptiveMessage = if (currentNumber < levels.size) {
                                if (moves <= target) {
                                    selectedLevel = levels[currentNumber]
                                    "Muy bien: $moves movimientos. Avance sugerido al nivel $nextLevel."
                                } else {
                                    "Nivel $nextLevel desbloqueado. Meta adaptiva: bajar de $target movimientos."
                                }
                            } else {
                                "Completaste los 20 niveles. Repite para mejorar puntajes."
                            }
                        }
                    )
                }
            }
        }
    }
}
