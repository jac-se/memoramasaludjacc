package cisneros.memoramasaludjacc

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cisneros.memoramasaludjacc.data.ThemeRepository
import cisneros.memoramasaludjacc.playgames.PlayGamesSidekick
import cisneros.memoramasaludjacc.playgames.findActivity
import cisneros.memoramasaludjacc.ui.GameBoard
import cisneros.memoramasaludjacc.ui.LevelIntroScreen
import cisneros.memoramasaludjacc.ui.theme.MemoramaSaludJaccTheme
import cisneros.memoramasaludjacc.util.ProgressStore
import kotlinx.coroutines.launch

private val difficultyNames = listOf("Facil", "Normal", "Dificil", "Experto")

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

@Composable
fun LevelItem(
    level: ThemeRepository.Level,
    unlocked: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val bestMoves by ProgressStore.bestMovesFlow(context, level.number).collectAsState(initial = null)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = unlocked, onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Level Number Indicator
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(
                        if (unlocked) level.pack.themeColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = level.number.toString(),
                    fontWeight = FontWeight.Bold,
                    color = if (unlocked) Color.Black.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.width(8.dp))

            // Level Text Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = level.title.substringAfter(" - "), // Remove the prefix "Nivel X - "
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = if (unlocked) {
                        if (bestMoves != null) "🏆 Récord: $bestMoves movs." else "Sin jugar"
                    } else {
                        "Bloqueado"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = if (unlocked && bestMoves != null) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Trailing icon
            if (!unlocked) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "Bloqueado",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
                    modifier = Modifier.size(16.dp)
                )
            } else if (bestMoves != null) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Completado",
                    tint = Color(0xFF2E7D32),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
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
    var selectedLevelNumber by rememberSaveable { mutableStateOf(1) }
    var playingLevelNumber by rememberSaveable { mutableStateOf<Int?>(null) }
    var introLevelNumber by rememberSaveable { mutableStateOf<Int?>(null) }
    var selectedDifficulty by rememberSaveable { mutableStateOf(1) }
    var adaptiveMessage by rememberSaveable { mutableStateOf("Completa un nivel para desbloquear el siguiente.") }
    var playGamesSignedIn by rememberSaveable { mutableStateOf(false) }

    if (selectedLevelNumber > unlockedLevel) {
        selectedLevelNumber = levels.first { it.number <= unlockedLevel }.number
    }
    val selectedLevel = levels.firstOrNull { it.number == selectedLevelNumber } ?: levels.first()
    val playingLevel = playingLevelNumber?.let { number -> levels.firstOrNull { it.number == number } }
    val introLevel = introLevelNumber?.let { number -> levels.firstOrNull { it.number == number } }

    LaunchedEffect(sidekick) {
        if (sidekick != null) playGamesSignedIn = sidekick.isSignedIn()
    }
    LaunchedEffect(unlockedLevel, playGamesSignedIn, sidekick) {
        if (playGamesSignedIn) sidekick?.unlockProgressAchievements(unlockedLevel)
    }

    MemoramaSaludJaccTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Memorama Salud JACC") },
                    navigationIcon = {
                        if (playingLevel != null || introLevel != null) {
                            IconButton(onClick = {
                                playingLevelNumber = null
                                introLevelNumber = null
                            }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = "Regresar")
                            }
                        }
                    }
                )
            }
        ) { inner ->
            Surface(modifier = Modifier.fillMaxSize().padding(inner)) {
                if (introLevel != null) {
                    val currentIntro = introLevel
                    LevelIntroScreen(
                        level = currentIntro,
                        difficulty = selectedDifficulty,
                        onStart = {
                            playingLevelNumber = currentIntro.number
                            introLevelNumber = null
                        },
                        onSkip = {
                            playingLevelNumber = currentIntro.number
                            introLevelNumber = null
                        }
                    )
                } else if (playingLevel == null) {
                    val configuration = LocalConfiguration.current
                    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                    val columns = if (isLandscape) 3 else 2

                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        // Header info card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
                            ),
                            shape = MaterialTheme.shapes.large
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        "Selecciona Nivel",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        "Desbloqueados: $unlockedLevel de ${levels.size}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }

                                Button(
                                    onClick = {
                                        if (sidekick == null) return@Button
                                        scope.launch {
                                            playGamesSignedIn = sidekick.signIn()
                                        }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (playGamesSignedIn) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier.size(18.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(
                                        if (playGamesSignedIn) "Conectado" else "Play Games",
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "Dificultad",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(6.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            difficultyNames.forEachIndexed { index, label ->
                                Button(
                                    onClick = { selectedDifficulty = index },
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selectedDifficulty == index) {
                                            MaterialTheme.colorScheme.primary
                                        } else {
                                            MaterialTheme.colorScheme.secondaryContainer
                                        },
                                        contentColor = if (selectedDifficulty == index) {
                                            MaterialTheme.colorScheme.onPrimary
                                        } else {
                                            MaterialTheme.colorScheme.onSecondaryContainer
                                        }
                                    )
                                ) {
                                    Text(label, fontSize = 11.sp, maxLines = 1)
                                }
                            }
                        }

                        Spacer(Modifier.height(10.dp))

                        // Grid of levels
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(columns),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            items(levels) { level ->
                                val unlocked = level.number <= unlockedLevel
                                LevelItem(
                                    level = level,
                                    unlocked = unlocked,
                                    isSelected = selectedLevelNumber == level.number,
                                    onClick = { selectedLevelNumber = level.number }
                                )
                            }
                        }

                        Spacer(Modifier.height(10.dp))
                        Text(
                            text = adaptiveMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                        )
                        Spacer(Modifier.height(8.dp))

                        // Bottom play controls
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            // Random Play Button
                            Button(
                                onClick = {
                                    val unlockedCount = unlockedLevel.coerceAtLeast(1)
                                    val candidateLevels = levels.filter { it.number <= unlockedCount }
                                    if (candidateLevels.isNotEmpty()) {
                                        val randomLvl = candidateLevels.random()
                                        selectedLevelNumber = randomLvl.number
                                        introLevelNumber = randomLvl.number
                                    }
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Shuffle,
                                    contentDescription = "Jugar Aleatorio",
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text("Jugar Aleatorio", fontSize = 14.sp)
                            }

                            // Start Selected Level Button
                            Button(
                                onClick = { introLevelNumber = selectedLevel.number },
                                modifier = Modifier.weight(1.2f)
                            ) {
                                Text("Jugar Nivel ${selectedLevel.number}", fontSize = 14.sp)
                            }
                        }
                    }
                } else {
                    val current = playingLevel
                    GameBoard(
                        level = current,
                        levels = levels,
                        unlockedLevelLimit = unlockedLevel,
                        difficulty = selectedDifficulty,
                        onNavigateToLevel = {
                            playingLevelNumber = it.number
                            selectedLevelNumber = it.number
                        },
                        onExit = { playingLevelNumber = null },
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
                                    selectedLevelNumber = levels[currentNumber].number
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
