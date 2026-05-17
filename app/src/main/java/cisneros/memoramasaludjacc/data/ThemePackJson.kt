package cisneros.memoramasaludjacc.data

import androidx.compose.ui.graphics.Color
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack

object ThemeRepository {

    data class Level(
        val number: Int,
        val title: String,
        val description: String,
        val pack: ThemePack
    )

    private fun buildLevel(
        number: Int,
        title: String,
        description: String,
        themeColor: Color,
        pairs: List<CardPair>
    ) = Level(
        number = number,
        title = "Nivel $number - $title",
        description = description,
        pack = ThemePack(title = title, pairs = pairs, themeColor = themeColor)
    )

    val levels = listOf(
        buildLevel(1, "Hidratacion", "Reconoce habitos basicos para mantener buena hidratacion.", Color(0xFF81D4FA), listOf(
            CardPair(left = "💧 Agua", right = "Hidrata el cuerpo"),
            CardPair(left = "🧂 Suero oral", right = "Recupera electrolitos"),
            CardPair(left = "🥵 Calor", right = "Aumenta deshidratacion"),
            CardPair(left = "🚽 Orina clara", right = "Senal de hidratacion")
        )),
        buildLevel(2, "Alimentacion Sana", "Identifica elecciones saludables en la comida diaria.", Color(0xFFA5D6A7), listOf(
            CardPair(left = "🍎 Fruta", right = "Fibra y vitaminas"),
            CardPair(left = "🥦 Verduras", right = "Bajas en calorias"),
            CardPair(left = "🥤 Refresco", right = "Exceso de azucar"),
            CardPair(left = "🍽️ Porciones", right = "Evitan exceso")
        )),
        buildLevel(3, "Higiene de Manos", "Aplica pasos y momentos clave del lavado de manos.", Color(0xFFB39DDB), listOf(
            CardPair(left = "🖐️ Lavar manos", right = "20 segundos"),
            CardPair(left = "🧼 Jabon", right = "Remueve microbios"),
            CardPair(left = "🍽️ Antes de comer", right = "Momento clave"),
            CardPair(left = "🚻 Despues de bano", right = "Previene contagio")
        )),
        buildLevel(4, "Higiene Bucal", "Relaciona habitos dentales con prevencion de caries.", Color(0xFF80CBC4), listOf(
            CardPair(left = "🪥 Cepillado", right = "2 veces al dia"),
            CardPair(left = "🧵 Hilo dental", right = "Limpia espacios"),
            CardPair(left = "🍬 Azucar", right = "Aumenta caries"),
            CardPair(left = "🦷 Dentista", right = "Revision periodica")
        )),
        buildLevel(5, "Sueno Saludable", "Diferencia rutinas que mejoran el descanso.", Color(0xFF9FA8DA), listOf(
            CardPair(left = "😴 Dormir 8h", right = "Mejor recuperacion"),
            CardPair(left = "📵 Sin pantallas", right = "Facilita dormir"),
            CardPair(left = "☕ Cafe tarde", right = "Dificulta sueno"),
            CardPair(left = "🕘 Horario fijo", right = "Regula descanso")
        )),
        buildLevel(6, "Actividad Fisica", "Asocia movimiento diario con beneficios de salud.", Color(0xFFFFCC80), listOf(
            CardPair(left = "🏃 Caminar", right = "Mejora circulacion"),
            CardPair(left = "🤸 Estirar", right = "Reduce rigidez"),
            CardPair(left = "💓 Ejercicio", right = "Fortalece corazon"),
            CardPair(left = "🪑 Sedentarismo", right = "Aumenta riesgo")
        )),
        buildLevel(7, "Postura", "Reconoce posturas que previenen dolor muscular.", Color(0xFF90CAF9), listOf(
            CardPair(left = "🪑 Espalda recta", right = "Evita dolor lumbar"),
            CardPair(left = "📱 Cuello neutro", right = "Menos tension"),
            CardPair(left = "💻 Altura pantalla", right = "Cuida vista"),
            CardPair(left = "⏱️ Pausas activas", right = "Descanso muscular")
        )),
        buildLevel(8, "Vision", "Cuida tus ojos con habitos simples diarios.", Color(0xFFFFAB91), listOf(
            CardPair(left = "👀 Regla 20-20-20", right = "Descansa la vista"),
            CardPair(left = "💡 Buena luz", right = "Menos fatiga ocular"),
            CardPair(left = "📱 Brillo alto", right = "Irrita ojos"),
            CardPair(left = "🕶️ Lentes UV", right = "Protegen del sol")
        )),
        buildLevel(9, "Proteccion Solar", "Previene dano en piel por exposicion al sol.", Color(0xFFFFE082), listOf(
            CardPair(left = "🧴 Bloqueador", right = "Proteccion UV"),
            CardPair(left = "🕶️ Sombrero", right = "Sombra facial"),
            CardPair(left = "🌞 Mediodia", right = "Mayor radiacion"),
            CardPair(left = "🕒 Reaplicar", right = "Cada 2 horas")
        )),
        buildLevel(10, "Prevencion de Resfriados", "Refuerza medidas para reducir contagios.", Color(0xFFB0BEC5), listOf(
            CardPair(left = "😷 Cubrebocas", right = "Reduce gotas"),
            CardPair(left = "🤧 Toser codo", right = "Evita dispersar"),
            CardPair(left = "🪟 Ventilar", right = "Renueva aire"),
            CardPair(left = "🤝 No compartir", right = "Menos contagio")
        )),
        buildLevel(11, "Vacunacion", "Comprende beneficios de mantener vacunas al dia.", Color(0xFFCE93D8), listOf(
            CardPair(left = "💉 Vacuna", right = "Activa defensas"),
            CardPair(left = "📅 Esquema", right = "Dosis completas"),
            CardPair(left = "👶 Infancia", right = "Etapa clave"),
            CardPair(left = "🛡️ Inmunidad", right = "Previene enfermedad")
        )),
        buildLevel(12, "Primeros Auxilios", "Aprende respuestas iniciales ante accidentes comunes.", Color(0xFFEF9A9A), listOf(
            CardPair(left = "🩹 Herida", right = "Lavar y cubrir"),
            CardPair(left = "🔥 Quemadura", right = "Agua fria"),
            CardPair(left = "🧊 Golpe", right = "Hielo envuelto"),
            CardPair(left = "📞 Emergencia", right = "Pedir ayuda")
        )),
        buildLevel(13, "Salud Mental", "Identifica practicas para bienestar emocional.", Color(0xFFFFCCBC), listOf(
            CardPair(left = "🫁 Respirar", right = "Baja ansiedad"),
            CardPair(left = "🗣️ Hablar", right = "Alivia tension"),
            CardPair(left = "🧠 Pausas", right = "Descanso mental"),
            CardPair(left = "🤝 Apoyo", right = "Red de ayuda")
        )),
        buildLevel(14, "Manejo de Estres", "Relaciona tecnicas simples para controlar el estres.", Color(0xFFDCEDC8), listOf(
            CardPair(left = "📓 Escribir", right = "Ordena ideas"),
            CardPair(left = "🎵 Musica", right = "Relaja cuerpo"),
            CardPair(left = "🚶 Caminar", right = "Libera tension"),
            CardPair(left = "🛌 Descanso", right = "Recupera energia")
        )),
        buildLevel(15, "Seguridad Vial", "Asocia medidas que previenen lesiones en traslados.", Color(0xFFB3E5FC), listOf(
            CardPair(left = "🚗 Cinturon", right = "Protege en choque"),
            CardPair(left = "⛑️ Casco", right = "Cuida cabeza"),
            CardPair(left = "🚦 Semaforo", right = "Orden de paso"),
            CardPair(left = "📵 No celular", right = "Evita distraccion")
        )),
        buildLevel(16, "Salud Respiratoria", "Reconoce cuidados para pulmones sanos.", Color(0xFF80DEEA), listOf(
            CardPair(left = "🚭 No fumar", right = "Pulmones fuertes"),
            CardPair(left = "🌬️ Aire limpio", right = "Mejor respiracion"),
            CardPair(left = "😮‍💨 Falta aire", right = "Buscar atencion"),
            CardPair(left = "🏃 Cardio", right = "Capacidad pulmonar")
        )),
        buildLevel(17, "Salud Digestiva", "Relaciona habitos que favorecen buena digestion.", Color(0xFFC5E1A5), listOf(
            CardPair(left = "🥗 Fibra", right = "Mejor transito"),
            CardPair(left = "💧 Agua", right = "Ayuda digestion"),
            CardPair(left = "🍟 Grasas altas", right = "Pesadez"),
            CardPair(left = "🍽️ Comer lento", right = "Mejor absorcion")
        )),
        buildLevel(18, "Salud del Corazon", "Refuerza practicas para proteger sistema cardiovascular.", Color(0xFFFFCDD2), listOf(
            CardPair(left = "❤️ Corazon", right = "Bombea sangre"),
            CardPair(left = "🧂 Menos sal", right = "Controla presion"),
            CardPair(left = "🏃 Ejercicio", right = "Cuida arterias"),
            CardPair(left = "😌 Menos estres", right = "Menor carga cardiaca")
        )),
        buildLevel(19, "Salud Comunitaria", "Aprende acciones colectivas para entornos saludables.", Color(0xFFD1C4E9), listOf(
            CardPair(left = "🧹 Limpieza", right = "Menos focos infeccion"),
            CardPair(left = "🚮 Basura cerrada", right = "Evita plagas"),
            CardPair(left = "💬 Informar riesgos", right = "Prevencion comun"),
            CardPair(left = "🤲 Colaborar", right = "Mejora comunidad")
        )),
        buildLevel(20, "Habitos Integrales", "Integra practicas clave para una salud completa.", Color(0xFFFFF59D), listOf(
            CardPair(left = "💧 Hidratarse", right = "Funcion corporal"),
            CardPair(left = "🥦 Comer balanceado", right = "Nutrientes completos"),
            CardPair(left = "😴 Dormir bien", right = "Recuperacion total"),
            CardPair(left = "🏃 Moverse diario", right = "Energia y bienestar")
        ))
    )
}
