package cisneros.memoramasaludjacc.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cisneros.memoramasaludjacc.data.ThemeRepository
import kotlinx.coroutines.delay

private val difficultyLabels = listOf("🟢 Fácil", "🟡 Normal", "🟠 Difícil", "🔴 Experto")
private val difficultyCards  = listOf(6, 8, 12, 16)
private val difficultyColors = listOf(
    Color(0xFF4CAF50), Color(0xFFFFC107), Color(0xFFFF9800), Color(0xFFF44336)
)

/**
 * Two-phase intro screen:
 *   Phase 1 — Dr. Jacc narrative dialog with typewriter effect.
 *   Phase 2 — Hint/preview screen with card emoji hints & difficulty indicator.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LevelIntroScreen(
    level: ThemeRepository.Level,
    difficulty: Int,           // 0=Easy 1=Normal 2=Hard 3=Expert
    onStart: () -> Unit,
    onSkip: () -> Unit
) {
    val themeColor = level.pack.themeColor
    val onTheme = if (themeColor.luminance() > 0.5f) Color.Black else Color.White
    val story = level.story

    // Phase: 0 = story dialog, 1 = hints screen
    var phase by rememberSaveable(level.number, difficulty) { mutableIntStateOf(0) }

    // Typewriter state
    var displayedText by rememberSaveable(level.number, difficulty) { mutableStateOf("") }
    var typewriterDone by rememberSaveable(level.number, difficulty) { mutableStateOf(false) }

    LaunchedEffect(phase) {
        if (phase == 0) {
            val startIndex = displayedText.length.coerceIn(0, story.length)
            for (i in startIndex until story.length) {
                displayedText = story.substring(0, i + 1)
                delay(18L)
            }
            typewriterDone = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(themeColor.copy(alpha = 0.15f))
    ) {
        AnimatedContent(
            targetState = phase,
            transitionSpec = {
                (fadeIn(tween(400)) + slideInVertically { it / 4 }) togetherWith fadeOut(tween(200))
            },
            label = "IntroPhase"
        ) { currentPhase ->
            if (currentPhase == 0) {
                // ── Phase 1: Story Dialog ─────────────────────────────────────
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .clickable {
                            if (!typewriterDone) {
                                displayedText = story
                                typewriterDone = true
                            }
                        },
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(16.dp))

                    // Level badge
                    Surface(
                        color = themeColor,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = level.title,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = onTheme
                        )
                    }

                    // Dr. Jacc avatar
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("👨‍⚕️", fontSize = 52.sp)
                    }

                    Spacer(Modifier.height(8.dp))

                    // Speech bubble
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        elevation = CardDefaults.cardElevation(4.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "Dr. César:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = displayedText,
                                style = MaterialTheme.typography.bodyMedium,
                                lineHeight = 22.sp
                            )
                            AnimatedVisibility(typewriterDone) {
                                Text(
                                    text = "▸ Toca para continuar",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(top = 10.dp)
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onSkip,
                            modifier = Modifier.weight(1f)
                        ) { Text("Omitir") }

                        Button(
                            onClick = { if (typewriterDone) phase = 1 else { displayedText = story; typewriterDone = true } },
                            modifier = Modifier.weight(2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = themeColor,
                                contentColor = onTheme
                            )
                        ) { Text("Ver pistas →") }
                    }
                }
            } else {
                // ── Phase 2: Hints Screen ─────────────────────────────────────
                val pairs = level.pack.pairs
                val hintCount = when (difficulty) {
                    0 -> 2
                    1 -> pairs.size
                    else -> pairs.size
                }
                val showText = difficulty <= 1

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "¡Pistas del nivel!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(4.dp))

                    // Difficulty chip
                    Surface(
                        color = difficultyColors[difficulty.coerceIn(0, 3)].copy(alpha = 0.15f),
                        shape = RoundedCornerShape(50),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                difficultyLabels[difficulty.coerceIn(0, 3)],
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = difficultyColors[difficulty.coerceIn(0, 3)]
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "${difficultyCards[difficulty.coerceIn(0, 3)]} cartas",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(Modifier.height(12.dp))

                    // Hint cards
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = if (showText) "Conceptos que aparecerán:" else "Emojis que verás:",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                pairs.take(hintCount).forEach { pair ->
                                    val emoji = pair.left.split(" ").firstOrNull() ?: "?"
                                    Surface(
                                        color = themeColor.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(
                                            text = if (showText) pair.left else emoji,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                            if (difficulty >= 2) {
                                Spacer(Modifier.height(10.dp))
                                Text(
                                    text = "⚠️ También habrá pares de otros niveles para repasar.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = { phase = 0 },
                            modifier = Modifier.weight(1f)
                        ) { Text("← Atrás") }

                        Button(
                            onClick = onStart,
                            modifier = Modifier.weight(2f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = themeColor,
                                contentColor = onTheme
                            )
                        ) { Text("¡Empezar! 🎮") }
                    }
                }
            }
        }
    }
}
