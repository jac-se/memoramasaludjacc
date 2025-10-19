package cisneros.memoramasaludjacc.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cisneros.memoramasaludjacc.audio.SimpleSynth
import com.jacc.memoramasalud.model.Card as GameCardModel
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameBoard(pack: ThemePack, synth: SimpleSynth? = null) {
    var seed by remember { mutableStateOf(0) }
    var opened by remember(seed) { mutableStateOf<List<GameCardModel>>(emptyList()) }
    var matched by remember(seed) { mutableStateOf<Set<Int>>(emptySet()) }
    var moves by remember(seed) { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    val cardsAll = remember(pack, seed) {
        buildList {
            var idx = 0
            pack.pairs.forEachIndexed { i, pair: CardPair ->
                add(GameCardModel(id = idx++, text = pair.left,  pairId = i))
                add(GameCardModel(id = idx++, text = pair.right, pairId = i))
            }
        }.shuffled()
    }

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(pack.title, style = MaterialTheme.typography.titleLarge)
            Text("Movimientos: $moves", fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(cardsAll, key = { it.id xor seed }) { card ->
                val faceUp = opened.any { it.id == card.id } || matched.contains(card.pairId)
                Card(
                    modifier = Modifier.size(100.dp).clickable {
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
                    Box(Modifier.fillMaxSize().padding(8.dp), contentAlignment = Alignment.Center) {
                        Text(if (faceUp) card.text else "🂠")
                    }
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { seed++ }) { Text("Reiniciar") }
            if (matched.size == pack.pairs.size) {
                Text("¡Completado!", fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
            }
        }
    }
}
