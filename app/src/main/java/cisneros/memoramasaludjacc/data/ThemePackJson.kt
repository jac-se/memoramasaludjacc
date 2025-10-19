package cisneros.memoramasaludjacc.data

import androidx.compose.ui.graphics.Color
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack

/**
 * Repositorio de temas predefinidos para el memorama.
 * Usa el mismo modelo ThemePack que GameBoard.kt.
 */
object ThemeRepository {

    val saludBasica = ThemePack(
        title = "Salud y Alimentación",
        pairs = listOf(
            CardPair(id = "1", left = "🍎 Manzana", right = "Rica en fibra y vitaminas"),
            CardPair(id = "2", left = "🥕 Zanahoria", right = "Buena para la vista"),
            CardPair(id = "3", left = "💧 Agua", right = "Hidratación esencial"),
            CardPair(id = "4", left = "🏃‍♂️ Ejercicio", right = "Fortalece el corazón"),
            CardPair(id = "5", left = "🪥 Cepillo dental", right = "Higiene bucal diaria"),
            CardPair(id = "6", left = "😴 Dormir bien", right = "Favorece el sistema inmune")
        ),
        themeColor = Color(0xFF81C784)
    )

    val primerosAuxilios = ThemePack(
        title = "Primeros Auxilios",
        pairs = listOf(
            CardPair(id = "1", left = "🩹 Vendaje", right = "Detiene el sangrado"),
            CardPair(id = "2", left = "🍶 Alcohol", right = "Desinfecta heridas"),
            CardPair(id = "3", left = "🧰 Botiquín", right = "Guarda materiales médicos"),
            CardPair(id = "4", left = "🌡️ Fiebre", right = "Usar termómetro"),
            CardPair(id = "5", left = "🔥 Quemadura", right = "Aplicar agua fría"),
            CardPair(id = "6", left = "😵 Desmayo", right = "Colocar en posición lateral")
        ),
        themeColor = Color(0xFF64B5F6)
    )

    val defaultPacks = listOf(saludBasica, primerosAuxilios)
}
