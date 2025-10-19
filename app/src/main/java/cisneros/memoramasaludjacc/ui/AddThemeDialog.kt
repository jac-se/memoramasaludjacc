package cisneros.memoramasaludjacc.data

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack

@Composable
fun AddThemeDialog(
    onCancel: () -> Unit,
    onSave: (ThemePack) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var left  by remember { mutableStateOf("") }
    var right by remember { mutableStateOf("") }
    val pairs = remember { mutableStateListOf<CardPair>() } // lista con estado Compose

    AlertDialog(
        onDismissRequest = onCancel,
        title = { Text("Nuevo tema") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título del tema") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = left,
                        onValueChange = { left = it },
                        label = { Text("Carta A (ej. 🥕 Zanahoria)") },
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = right,
                        onValueChange = { right = it },
                        label = { Text("Carta B (ej. Cuida la vista)") },
                        modifier = Modifier.weight(1f)
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {
                        val l = left.trim()
                        val r = right.trim()
                        if (l.isNotEmpty() && r.isNotEmpty()) {
                            // CardPair debe tener id con valor por defecto en su data class.
                            pairs.add(CardPair(left = l, right = r))
                            left = ""; right = ""
                        }
                    }) { Text("Agregar par") }

                    OutlinedButton(onClick = { left = ""; right = "" }) { Text("Limpiar campos") }
                }

                if (pairs.isNotEmpty()) {
                    Spacer(Modifier.height(8.dp))
                    Divider()
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        pairs.forEachIndexed { i, p ->
                            Text("${i + 1}. ${p.left} ↔ ${p.right}")
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.isNotBlank() && pairs.size >= 3) {
                    val id = title.lowercase()
                        .replace(" ", "_")
                        .replace("[^a-z0-9_-]".toRegex(), "")
                    onSave(
                        ThemePack(
                            title = title.trim(),
                            pairs = pairs.toList(),
                            id = id
                        )
                    )
                }
            }) { Text("Guardar") }
        },
        dismissButton = { OutlinedButton(onClick = onCancel) { Text("Cancelar") } }
    )
}
