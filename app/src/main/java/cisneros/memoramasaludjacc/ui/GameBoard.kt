package cisneros.memoramasaludjacc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jacc.memoramasalud.model.Card as GameCardModel
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameBoard(
    pack: ThemePack,
    onCompleted: (moves: Int) -> Unit = {}
) {
    var seed by remember { mutableStateOf(0) }
    var opened by remember(seed) { mutableStateOf<List<GameCardModel>>(emptyList()) }
    var matched by remember(seed) { mutableStateOf<Set<Int>>(emptySet()) }
    var moves by remember(seed) { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    val cardsAll = remember(pack, seed) {
        buildList {
            var idx = 0
            pack.pairs.forEachIndexed { i, pair: CardPair ->
                add(GameCardModel(id = idx++, text = pair.left, pairId = i))
                add(GameCardModel(id = idx++, text = pair.right, pairId = i))
            }
        }.shuffled()
    }

    val isCompleted = matched.size == pack.pairs.size
    LaunchedEffect(isCompleted, moves, seed) {
        if (isCompleted) onCompleted(moves)
    }

    Column(Modifier.fillMaxSize().padding(horizontal = 12.dp, vertical = 8.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(pack.title, style = MaterialTheme.typography.titleLarge)
            Text("Movimientos: $moves", fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.size(8.dp))

        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            val columns = when {
                maxWidth < 360.dp -> 2
                maxWidth < 680.dp -> 3
                else -> 4
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(columns),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(cardsAll, key = { it.id xor seed }) { card ->
                    val faceUp = opened.any { it.id == card.id } || matched.contains(card.pairId)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(0.86f)
                            .clickable {
                                if (matched.contains(card.pairId)) return@clickable
                                if (opened.size == 2 || faceUp) return@clickable

                                opened = opened + card
                                if (opened.size == 2) {
                                    moves++
                                    val (a, b) = opened
                                    if (a.pairId == b.pairId) {
                                        matched = matched + a.pairId
                                        opened = emptyList()
                                    } else {
                                        scope.launch {
                                            delay(650)
                                            opened = emptyList()
                                        }
                                    }
                                }
                            }
                    ) {
                        Box(
                            Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (faceUp) card.text else "🂠",
                                style = if (columns <= 2) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodyMedium,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.size(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { seed++ }) { Text("Reiniciar") }
            if (isCompleted) {
                Text("Completado", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
            }
        }
    }
}
