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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.material3.OutlinedButton
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

private enum class HomeScreen {
    LevelPicker,
    PlayGames,
    MainMenu
}

private fun leaderboardIdForLevel(context: android.content.Context, levelNumber: Int): String? {
    val resourceId = context.resources.getIdentifier(
        "leaderboard_level_$levelNumber",
        "string",
        context.packageName
    )
    if (resourceId == 0) return null
    return context.getString(resourceId).takeUnless { it.startsWith("CgkI_REEMPLAZA") || it.isBlank() }
}

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

@Composable
private fun DifficultySelector(
    selectedDifficulty: Int,
    onSelectDifficulty: (Int) -> Unit
) {
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
                onClick = { onSelectDifficulty(index) },
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
}

@Composable
private fun CompactLevelPickerScreen(
    levels: List<ThemeRepository.Level>,
    unlockedLevel: Int,
    selectedLevelNumber: Int,
    onSelectLevel: (Int) -> Unit,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "Elige tu nivel",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Desbloqueados: $unlockedLevel de ${levels.size}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(levels) { level ->
                val unlocked = level.number <= unlockedLevel
                LevelItem(
                    level = level,
                    unlocked = unlocked,
                    isSelected = selectedLevelNumber == level.number,
                    onClick = { onSelectLevel(level.number) }
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onSkip,
                modifier = Modifier.weight(1f)
            ) {
                Text("Omitir")
            }
            Button(
                onClick = onContinue,
                modifier = Modifier.weight(1.3f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text("Continuar")
            }
        }
    }
}

@Composable
private fun CompactMainMenuScreen(
    levels: List<ThemeRepository.Level>,
    unlockedLevel: Int,
    selectedLevel: ThemeRepository.Level,
    selectedDifficulty: Int,
    adaptiveMessage: String,
    onBackToLevels: () -> Unit,
    onSelectDifficulty: (Int) -> Unit,
    onRandomPlay: () -> Unit,
    onStartSelected: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            "Nivel ${selectedLevel.number}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            selectedLevel.title.substringAfter(" - "),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    OutlinedButton(onClick = onBackToLevels) {
                        Text("Cambiar")
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))
        DifficultySelector(
            selectedDifficulty = selectedDifficulty,
            onSelectDifficulty = onSelectDifficulty
        )
        Spacer(Modifier.height(12.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = selectedLevel.pack.themeColor.copy(alpha = 0.16f)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    adaptiveMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    "Has desbloqueado $unlockedLevel de ${levels.size} niveles.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = onRandomPlay,
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
                Text("Aleatorio", fontSize = 14.sp)
            }

            Button(
                onClick = onStartSelected,
                modifier = Modifier.weight(1.2f)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(4.dp))
                Text("Jugar", fontSize = 14.sp)
            }
        }
    }
}

@Composable
private fun CompactPlayGamesScreen(
    playGamesSignedIn: Boolean,
    onBack: () -> Unit,
    onConnect: () -> Unit,
    onContinue: () -> Unit,
    onSkip: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.35f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    "Play Games",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    if (playGamesSignedIn) "Tu cuenta ya está conectada." else "Puedes conectarte ahora o continuar sin iniciar sesión.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = if (playGamesSignedIn) {
                    Color(0xFF2E7D32).copy(alpha = 0.12f)
                } else {
                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
                }
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onConnect,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (playGamesSignedIn) Color(0xFF2E7D32) else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(4.dp))
                    Text(if (playGamesSignedIn) "Conectado" else "Conectar Play Games")
                }

                Text(
                    if (playGamesSignedIn) "Ya puedes usar marcadores y progreso ligado a tu cuenta."
                    else "Si lo omites, el juego seguirá funcionando localmente.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {
                Text("Atrás")
            }
            OutlinedButton(
                onClick = onSkip,
                modifier = Modifier.weight(1f)
            ) {
                Text("Omitir")
            }
            Button(
                onClick = onContinue,
                modifier = Modifier.weight(1.2f)
            ) {
                Text("Seguir")
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
    val configuration = LocalConfiguration.current
    val isCompactScreen = configuration.smallestScreenWidthDp < 600
    var homeScreen by rememberSaveable(isCompactScreen) {
        mutableStateOf(if (isCompactScreen) HomeScreen.LevelPicker else HomeScreen.MainMenu)
    }

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
            contentWindowInsets = WindowInsets.safeDrawing,
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Memorama Salud JACC") },
                    navigationIcon = {
                        if (playingLevel != null || introLevel != null) {
                            IconButton(onClick = {
                                playingLevelNumber = null
                                introLevelNumber = null
                                if (isCompactScreen) {
                                    homeScreen = HomeScreen.MainMenu
                                }
                            }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar")
                            }
                        }
                    }
                )
            }
        ) { inner ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inner),
                color = MaterialTheme.colorScheme.background
            ) {
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
                    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
                    val columns = if (isLandscape) 3 else 2

                    if (isCompactScreen) {
                        when (homeScreen) {
                            HomeScreen.LevelPicker -> CompactLevelPickerScreen(
                                levels = levels,
                                unlockedLevel = unlockedLevel,
                                selectedLevelNumber = selectedLevelNumber,
                                onSelectLevel = { selectedLevelNumber = it },
                                onContinue = { homeScreen = HomeScreen.PlayGames },
                                onSkip = { homeScreen = HomeScreen.PlayGames }
                            )

                            HomeScreen.PlayGames -> CompactPlayGamesScreen(
                                playGamesSignedIn = playGamesSignedIn,
                                onBack = { homeScreen = HomeScreen.LevelPicker },
                                onConnect = {
                                    if (sidekick != null) {
                                        scope.launch {
                                            playGamesSignedIn = sidekick.signIn()
                                        }
                                    }
                                },
                                onContinue = { homeScreen = HomeScreen.MainMenu },
                                onSkip = { homeScreen = HomeScreen.MainMenu }
                            )

                            HomeScreen.MainMenu -> CompactMainMenuScreen(
                                levels = levels,
                                unlockedLevel = unlockedLevel,
                                selectedLevel = selectedLevel,
                                selectedDifficulty = selectedDifficulty,
                                adaptiveMessage = adaptiveMessage,
                                onBackToLevels = { homeScreen = HomeScreen.LevelPicker },
                                onSelectDifficulty = { selectedDifficulty = it },
                                onRandomPlay = {
                                    val unlockedCount = unlockedLevel.coerceAtLeast(1)
                                    val candidateLevels = levels.filter { it.number <= unlockedCount }
                                    if (candidateLevels.isNotEmpty()) {
                                        val randomLvl = candidateLevels.random()
                                        selectedLevelNumber = randomLvl.number
                                        introLevelNumber = randomLvl.number
                                    }
                                },
                                onStartSelected = { introLevelNumber = selectedLevel.number }
                            )
                        }
                    } else {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        ) {
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

                            DifficultySelector(
                                selectedDifficulty = selectedDifficulty,
                                onSelectDifficulty = { selectedDifficulty = it }
                            )

                            Spacer(Modifier.height(10.dp))

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

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
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

                                Button(
                                    onClick = { introLevelNumber = selectedLevel.number },
                                    modifier = Modifier.weight(1.2f)
                                ) {
                                    Text("Jugar Nivel ${selectedLevel.number}", fontSize = 14.sp)
                                }
                            }
                        }
                    }
                } else {
                    val current = playingLevel
                    val leaderboardId = leaderboardIdForLevel(ctx, current.number)
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
                        onCompleted = { moves, elapsedMillis ->
                            val currentNumber = current.number
                            val nextLevel = (currentNumber + 1).coerceAtMost(levels.size)
                            val target = targetMovesFor(currentNumber, current.pack.pairs.size)

                            scope.launch {
                                ProgressStore.saveBestMoves(ctx, currentNumber, moves)
                                ProgressStore.unlockUpTo(ctx, nextLevel)
                                if (playGamesSignedIn && sidekick != null && leaderboardId != null) {
                                    sidekick.submitLeaderboardTime(leaderboardId, elapsedMillis)
                                }
                            }

                            adaptiveMessage = if (currentNumber < levels.size) {
                                if (moves <= target) {
                                    selectedLevelNumber = levels[currentNumber].number
                                    "Muy bien: $moves movimientos en ${elapsedMillis / 1000}s. Avance sugerido al nivel $nextLevel."
                                } else {
                                    "Nivel $nextLevel desbloqueado en ${elapsedMillis / 1000}s. Meta adaptiva: bajar de $target movimientos."
                                }
                            } else {
                                "Completaste los 20 niveles. Repite para mejorar puntajes."
                            }
                        },
                        canShowLeaderboard = playGamesSignedIn && leaderboardId != null,
                        onShowLeaderboard = if (playGamesSignedIn && sidekick != null && leaderboardId != null && activity != null) {
                            {
                                scope.launch {
                                    sidekick.leaderboardIntent(leaderboardId)?.let(activity::startActivity)
                                }
                            }
                        } else {
                            null
                        }
                    )
                }
            }
        }
    }
}
