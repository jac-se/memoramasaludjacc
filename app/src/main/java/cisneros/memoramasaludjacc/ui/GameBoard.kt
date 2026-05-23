package cisneros.memoramasaludjacc.ui

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cisneros.memoramasaludjacc.data.ThemeRepository
import cisneros.memoramasaludjacc.ui.components.CardItem
import cisneros.memoramasaludjacc.util.ProgressStore
import com.jacc.memoramasalud.model.Card as GameCardModel
import com.jacc.memoramasalud.model.CardPair
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

private val difficultyLabels = listOf("🟢 Fácil", "🟡 Normal", "🟠 Difícil", "🔴 Experto")
private val difficultyColors = listOf(
    Color(0xFF4CAF50), Color(0xFFFFC107), Color(0xFFFF9800), Color(0xFFF44336)
)

/** Returns the pair count for the given difficulty (0-3). */
private fun pairsForDifficulty(difficulty: Int) = when (difficulty) {
    0 -> 3
    1 -> 4
    2 -> 6
    else -> 8
}

private fun targetMovesFor(pairCount: Int, difficulty: Int): Int {
    val base = pairCount * 2
    val tolerance = when (difficulty) {
        0 -> 3
        1 -> 4
        2 -> 6
        else -> 8
    }
    return base + tolerance
}

/**
 * Builds the card list for the given level and difficulty.
 * Hard/Expert modes mix in pairs from other unlocked levels.
 */
private fun buildCards(
    level: ThemeRepository.Level,
    difficulty: Int,
    allLevels: List<ThemeRepository.Level>,
    unlockedLimit: Int,
    seed: Int
): List<GameCardModel> {
    val pairCount = pairsForDifficulty(difficulty)
    val levelPairs = level.pack.pairs.take(4) // always take up to 4 from current level

    val combinedPairs: List<CardPair> = when {
        difficulty <= 1 -> levelPairs.take(pairCount)
        else -> {
            // Mix pairs from other unlocked levels
            val extraNeeded = pairCount - levelPairs.size
            val otherPairs = allLevels
                .filter { it.number != level.number && it.number <= unlockedLimit }
                .flatMap { it.pack.pairs }
                .shuffled(Random(seed + 17))
                .take(extraNeeded)
            (levelPairs + otherPairs).take(pairCount)
        }
    }

    return buildList {
        var idx = 0
        combinedPairs.forEachIndexed { i, pair ->
            add(GameCardModel(id = idx++, text = pair.left, pairId = i))
            add(GameCardModel(id = idx++, text = pair.right, pairId = i))
        }
    }.shuffled(Random(seed + 31))
}

@Composable
fun GameBoard(
    level: ThemeRepository.Level,
    levels: List<ThemeRepository.Level>,
    unlockedLevelLimit: Int,
    difficulty: Int,
    onNavigateToLevel: (ThemeRepository.Level) -> Unit,
    onExit: () -> Unit,
    onCompleted: (moves: Int) -> Unit = {}
) {
    val pack = level.pack
    val themeColor = pack.themeColor
    val onTheme = if (themeColor.luminance() > 0.5f) Color.Black else Color.White

    var seed by rememberSaveable(level.number, difficulty) { mutableStateOf(0) }
    var openedIds by rememberSaveable(level.number, seed, difficulty) { mutableStateOf<List<Int>>(emptyList()) }
    var matchedPairIds by rememberSaveable(level.number, seed, difficulty) { mutableStateOf<List<Int>>(emptyList()) }
    var moves  by rememberSaveable(level.number, seed, difficulty) { mutableStateOf(0) }
    val scope  = rememberCoroutineScope()
    val context = LocalContext.current

    val bestMoves by ProgressStore.bestMovesFlow(context, level.number).collectAsState(initial = null)
    val pairCount   = pairsForDifficulty(difficulty)
    val targetLimit = targetMovesFor(pairCount, difficulty)

    val cardsAll = remember(pack, seed, difficulty) {
        buildCards(level, difficulty, levels, unlockedLevelLimit, seed)
    }

    val isCompleted = matchedPairIds.size == pairCount
    LaunchedEffect(isCompleted, moves, seed) {
        if (isCompleted) onCompleted(moves)
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val hasPrev  = level.number > 1
    val prevLevel = if (hasPrev) levels.firstOrNull { it.number == level.number - 1 } else null
    val nextLevel = if (level.number < levels.size && level.number < unlockedLevelLimit)
        levels.firstOrNull { it.number == level.number + 1 } else null

    fun shareProgress() {
        val diffLabel = difficultyLabels[difficulty.coerceIn(0, 3)]
        val msg = buildString {
            appendLine("🏥 ¡Completé el nivel ${level.number} \"${pack.title}\" en Memorama Salud JACC!")
            appendLine("🎯 Lo resolví en $moves movimientos con dificultad $diffLabel")
            appendLine("🔓 Llevo ${unlockedLevelLimit} de ${levels.size} niveles completados")
            appendLine("🎮 ¿Puedes superarme?")
            appendLine("memoramasalud://nivel?id=${level.number}")
            appendLine("https://play.google.com/store/apps/details?id=cisneros.memoramasaludjacc")

        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, msg)
            putExtra(Intent.EXTRA_SUBJECT, "¡Mi progreso en Memorama Salud JACC!")
        }
        context.startActivity(Intent.createChooser(intent, "Compartir logro"))
    }

    // ─── Sub-composables ─────────────────────────────────────────────────────

    @Composable
    fun CardGrid(modifier: Modifier = Modifier) {
        val cols = when {
            isLandscape && pairCount > 4 -> 4
            isLandscape -> 4
            pairCount <= 4 -> 2
            pairCount <= 6 -> 3
            else -> 4
        }
        val rows = (cardsAll.size + cols - 1) / cols
        val spacing = 6.dp
        val cardAspectRatio = 0.85f

        BoxWithConstraints(modifier = modifier) {
            val totalHorizontalSpacing = spacing * (cols - 1)
            val totalVerticalSpacing = spacing * (rows - 1)
            val cellWidth = (maxWidth - totalHorizontalSpacing) / cols
            val cellHeight = (maxHeight - totalVerticalSpacing) / rows
            val widthFromHeight = cellHeight * cardAspectRatio
            val cardWidth = if (widthFromHeight < cellWidth) widthFromHeight else cellWidth
            val cardHeight = cardWidth / cardAspectRatio

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(spacing)
            ) {
                repeat(rows) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .height(cellHeight)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(spacing)
                    ) {
                        repeat(cols) { colIndex ->
                            val cardIndex = rowIndex * cols + colIndex
                            if (cardIndex < cardsAll.size) {
                                val card = cardsAll[cardIndex]
                                val faceUp = openedIds.contains(card.id) || matchedPairIds.contains(card.pairId)
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CardItem(
                                        text = card.text,
                                        faceUp = faceUp,
                                        onClick = {
                                            if (matchedPairIds.contains(card.pairId)) return@CardItem
                                            if (openedIds.size == 2 || faceUp) return@CardItem
                                            val newOpenedIds = openedIds + card.id
                                            openedIds = newOpenedIds
                                            if (newOpenedIds.size == 2) {
                                                moves++
                                                val openedCards = newOpenedIds.mapNotNull { id ->
                                                    cardsAll.firstOrNull { it.id == id }
                                                }
                                                val (a, b) = openedCards
                                                if (a.pairId == b.pairId) {
                                                    matchedPairIds = matchedPairIds + a.pairId
                                                    openedIds = emptyList()
                                                } else {
                                                    scope.launch { delay(650); openedIds = emptyList() }
                                                }
                                            }
                                        },
                                        modifier = Modifier.size(cardWidth, cardHeight)
                                    )
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun StatsPanel(modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = themeColor.copy(alpha = 0.1f)
            ),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Box(
                        modifier = Modifier.size(40.dp).clip(CircleShape).background(themeColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = level.number.toString(),
                            fontWeight = FontWeight.Bold,
                            color = onTheme,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Column {
                        Text(pack.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        // Difficulty badge
                        Surface(
                            color = difficultyColors[difficulty.coerceIn(0,3)].copy(alpha = 0.15f),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Text(
                                difficultyLabels[difficulty.coerceIn(0,3)],
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = difficultyColors[difficulty.coerceIn(0,3)],
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Movimientos", style = MaterialTheme.typography.labelSmall)
                        Text(moves.toString(), style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Meta", style = MaterialTheme.typography.labelSmall)
                        Text(targetLimit.toString(), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Récord", style = MaterialTheme.typography.labelSmall)
                        Text(
                            text = bestMoves?.toString() ?: "—",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (bestMoves != null) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                AnimatedVisibility(visible = isCompleted, enter = fadeIn(), exit = fadeOut()) {
                    Surface(
                        color = Color(0xFFE8F5E9), contentColor = Color(0xFF1B5E20),
                        shape = MaterialTheme.shapes.small, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "🎉 ¡Completado! $moves movimientos",
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ControlsWidget(modifier: Modifier = Modifier) {
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { seed++ },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Reiniciar", fontSize = 12.sp)
                }
                OutlinedButton(onClick = onExit, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Home, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Menú", fontSize = 12.sp)
                }
                // Share button — always visible, highlighted when completed
                FilledTonalButton(
                    onClick = { shareProgress() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = if (isCompleted) Color(0xFF2E7D32) else MaterialTheme.colorScheme.secondaryContainer,
                        contentColor   = if (isCompleted) Color.White else MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Compartir", fontSize = 12.sp)
                }
            }

            // Level nav row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { prevLevel?.let(onNavigateToLevel) }, enabled = hasPrev) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Nivel anterior")
                }
                val unlockedCount = unlockedLevelLimit.coerceAtLeast(1)
                FilledTonalButton(
                    onClick = {
                        val candidates = levels.filter { it.number <= unlockedCount && it.number != level.number }
                        if (candidates.isNotEmpty()) onNavigateToLevel(candidates.random())
                    },
                    enabled = unlockedCount > 1,
                    modifier = Modifier.weight(0.6f)
                ) {
                    Icon(Icons.Default.Shuffle, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Aleatorio", fontSize = 12.sp)
                }
                IconButton(onClick = { nextLevel?.let(onNavigateToLevel) }, enabled = nextLevel != null) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Nivel siguiente")
                }
            }
        }
    }

    // ─── Layout (Portrait / Landscape) ───────────────────────────────────────

    if (isLandscape) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.38f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                StatsPanel(modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                ControlsWidget(modifier = Modifier.fillMaxWidth())
            }
            Box(
                modifier = Modifier.weight(0.62f).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                CardGrid(modifier = Modifier.fillMaxSize())
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            StatsPanel(modifier = Modifier.fillMaxWidth())
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth().padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                CardGrid(modifier = Modifier.fillMaxSize())
            }
            ControlsWidget(modifier = Modifier.fillMaxWidth())
        }
    }
}
