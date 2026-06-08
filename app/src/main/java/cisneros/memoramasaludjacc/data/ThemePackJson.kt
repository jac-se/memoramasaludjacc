package cisneros.memoramasaludjacc.data

import androidx.compose.ui.graphics.Color
import com.jacc.memoramasalud.model.CardPair
import com.jacc.memoramasalud.model.ThemePack

object ThemeRepository {

    data class Level(
        val number: Int,
        val title: String,
        val description: String,
        val story: String,
        val pack: ThemePack
    )

    private fun buildLevel(
        number: Int,
        title: String,
        description: String,
        story: String,
        themeColor: Color,
        pairs: List<CardPair>
    ) = Level(
        number = number,
        title = "Nivel $number - $title",
        description = description,
        story = story,
        pack = ThemePack(title = title, pairs = pairs, themeColor = themeColor)
    )

    val levels = listOf(
        buildLevel(1, "Hidratacion", "Reconoce habitos basicos para mantener buena hidratacion.",
            "Hola, soy el Dr. César 👨‍⚕️. Hoy hablaremos del agua 💧, la base de la vida. ¿Sabias que cuando hace mucho calor 🥵 tu cuerpo pierde liquidos? El suero oral 🧂 recupera electrolitos y revisar que tu orina sea clara 🚽 es señal de buena hidratacion. ¡Encuentra los pares correctos!",
            Color(0xFF81D4FA), listOf(
            CardPair(left = "💧 Agua", right = "Hidrata el cuerpo"),
            CardPair(left = "🧂 Suero oral", right = "Recupera electrolitos"),
            CardPair(left = "🥵 Calor", right = "Aumenta deshidratacion"),
            CardPair(left = "🚽 Orina clara", right = "Señal de hidratacion")
        )),
        buildLevel(2, "Alimentacion Sana", "Identifica elecciones saludables en la comida diaria.",
            "¡Bienvenido de vuelta! 👨‍⚕️ Hoy hablamos de lo que comes. Las frutas 🍎 tienen fibra y vitaminas esenciales. Las verduras 🥦 son bajas en calorias pero llenas de nutrientes. Los refrescos 🥤 tienen demasiada azucar. Controlar las porciones 🍽️ te ayuda a no excederte. ¡Conecta cada alimento con su beneficio!",
            Color(0xFFA5D6A7), listOf(
            CardPair(left = "🍎 Fruta", right = "Fibra y vitaminas"),
            CardPair(left = "🥦 Verduras", right = "Bajas en calorias"),
            CardPair(left = "🥤 Refresco", right = "Exceso de azucar"),
            CardPair(left = "🍽️ Porciones", right = "Evitan exceso")
        )),
        buildLevel(3, "Higiene de Manos", "Aplica pasos y momentos clave del lavado de manos.",
            "👨‍⚕️ ¿Sabias que las manos son la principal via de contagio de enfermedades? Lavar las manos 🖐️ durante 20 segundos con jabon 🧼 elimina microbios. El momento mas importante es antes de comer 🍽️ y despues de ir al baño 🚻. ¡Descubre cada par y aprende a protegerte!",
            Color(0xFFB39DDB), listOf(
            CardPair(left = "🖐️ Lavar manos", right = "20 segundos"),
            CardPair(left = "🧼 Jabon", right = "Remueve microbios"),
            CardPair(left = "🍽️ Antes de comer", right = "Momento clave"),
            CardPair(left = "🚻 Despues de bano", right = "Previene contagio")
        )),
        buildLevel(4, "Higiene Bucal", "Relaciona habitos dentales con prevencion de caries.",
            "👩️‍⚕️¡Hola! Soy la Dra. Gaby 👩,⚕️ Tu sonrisa es tu carta de presentacion. El cepillado 🪥 debe hacerse 3 veces al dia para mantener dientes limpios. El hilo dental 🧵 limpia los espacios donde el cepillo no llega. El azucar 🍬 es el principal enemigo de tus dientes. ¡Y no olvides visitar al dentista 🦷 regularmente!",
            Color(0xFF80CBC4), listOf(
            CardPair(left = "🪥 Cepillado", right = "3 veces al dia"),
            CardPair(left = "🧵 Hilo dental", right = "Limpia espacios"),
            CardPair(left = "🍬 Azucar", right = "Aumenta caries"),
            CardPair(left = "🦷 Dentista", right = "Revision periodica")
        )),
        buildLevel(5, "Sueno Saludable", "Diferencia rutinas que mejoran el descanso.",
            "👨‍⚕️ Dormir bien es tan importante como comer bien. Dormir 8 horas 😴 permite que tu cuerpo se recupere. Evitar las pantallas 📵 antes de dormir facilita el sueno. El cafe ☕ por las tardes dificulta conciliar el sueno. Mantener un horario fijo 🕘 regula tu reloj biologico. ¡Conecta cada habito con su efecto!",
            Color(0xFF9FA8DA), listOf(
            CardPair(left = "😴 Dormir 8h", right = "Mejor recuperacion"),
            CardPair(left = "📵 Sin pantallas", right = "Facilita dormir"),
            CardPair(left = "☕ Cafe tarde", right = "Dificulta sueno"),
            CardPair(left = "🕘 Horario fijo", right = "Regula descanso")
        )),
        buildLevel(6, "Actividad Fisica", "Asocia movimiento diario con beneficios de salud.",
            "👨‍⚕️ ️¡Hola! Soy la Dra. Celeste 👩 El cuerpo humano esta disenado para moverse. Caminar 🏃 mejora la circulacion sanguinea. Estirar 🤸 los musculos reduce la rigidez. El ejercicio 💓 fortalece el corazon. El sedentarismo 🪑 aumenta el riesgo de enfermedades cronicas. ¡Muevete y encuentra los pares correctos!",
            Color(0xFFFFCC80), listOf(
            CardPair(left = "🏃 Caminar", right = "Mejora circulacion"),
            CardPair(left = "🤸 Estirar", right = "Reduce rigidez"),
            CardPair(left = "💓 Ejercicio", right = "Fortalece corazon"),
            CardPair(left = "🪑 Sedentarismo", right = "Aumenta riesgo")
        )),
        buildLevel(7, "Postura", "Reconoce posturas que previenen dolor muscular.",
            "👨‍⚕️ ¿Pasas muchas horas frente a una pantalla? Mantener la espalda recta 🪑 evita el dolor lumbar. Tener el cuello en posicion neutra 📱 reduce la tension cervical. La altura de la pantalla 💻 cuida tu vista. Las pausas activas ⏱️ son esenciales para el descanso muscular. ¡Aprende a cuidar tu postura!",
            Color(0xFF90CAF9), listOf(
            CardPair(left = "🪑 Espalda recta", right = "Evita dolor lumbar"),
            CardPair(left = "📱 Cuello neutro", right = "Menos tension"),
            CardPair(left = "💻 Altura pantalla", right = "Cuida vista"),
            CardPair(left = "⏱️ Pausas activas", right = "Descanso muscular")
        )),
        buildLevel(8, "Vision", "Cuida tus ojos con habitos simples diarios.",
            "👨‍⚕️ Tus ojos trabajan sin parar. La regla 20-20-20 👀 te indica descansar la vista cada 20 minutos. Una buena iluminacion 💡 reduce la fatiga ocular. El brillo alto del telefono 📱 irrita los ojos con el tiempo. Los lentes con filtro UV 🕶️ protegen tus ojos del sol. ¡Cuida tu vision!",
            Color(0xFFFFAB91), listOf(
            CardPair(left = "👀 Regla 20-20-20", right = "Descansa la vista"),
            CardPair(left = "💡 Buena luz", right = "Menos fatiga ocular"),
            CardPair(left = "📱 Brillo alto", right = "Irrita ojos"),
            CardPair(left = "🕶️ Lentes UV", right = "Protegen del sol")
        )),
        buildLevel(9, "Proteccion Solar", "Previene dano en piel por exposicion al sol.",
            "👨‍⚕️ El sol es fuente de vida, pero tambien puede dañar tu piel. El bloqueador solar 🧴 ofrece proteccion UV esencial. Un sombrero 🕶️ da sombra a tu rostro. El mediodia 🌞 es el momento de mayor radiacion. Reaplicar el bloqueador 🕒 cada 2 horas mantiene la proteccion activa. ¡Protegete del sol!",
            Color(0xFFFFE082), listOf(
            CardPair(left = "🧴 Bloqueador", right = "Proteccion UV"),
            CardPair(left = "🕶️ Sombrero", right = "Sombra facial"),
            CardPair(left = "🌞 Mediodia", right = "Mayor radiacion"),
            CardPair(left = "🕒 Reaplicar", right = "Cada 2 horas")
        )),
        buildLevel(10, "Prevencion de Resfriados", "Refuerza medidas para reducir contagios.",
            "👨‍⚕️ Los resfriados se contagian facil, pero tambien se previenen. El cubrebocas 😷 reduce las gotas respiratorias. Toser con el codo 🤧 evita dispersar germenes. Ventilar los espacios 🪟 renueva el aire. Evitar compartir utensilios 🤝 disminuye el contagio. ¡Aprende a protegerte y proteger a los demas!",
            Color(0xFFB0BEC5), listOf(
            CardPair(left = "😷 Cubrebocas", right = "Reduce gotas"),
            CardPair(left = "🤧 Toser codo", right = "Evita dispersar"),
            CardPair(left = "🪟 Ventilar", right = "Renueva aire"),
            CardPair(left = "🤝 No compartir", right = "Menos contagio")
        )),
        buildLevel(11, "Vacunacion", "Comprende beneficios de mantener vacunas al dia.",
            "👨‍⚕️ Las vacunas son uno de los grandes avances de la medicina. Una vacuna 💉 activa las defensas de tu cuerpo. Seguir el esquema de vacunacion 📅 con dosis completas es vital. La infancia 👶 es la etapa clave para inmunizarse. La inmunidad 🛡️ que generan previene enfermedades graves. ¡Mantente al dia!",
            Color(0xFFCE93D8), listOf(
            CardPair(left = "💉 Vacuna", right = "Activa defensas"),
            CardPair(left = "📅 Esquema", right = "Dosis completas"),
            CardPair(left = "👶 Infancia", right = "Etapa clave"),
            CardPair(left = "🛡️ Inmunidad", right = "Previene enfermedad")
        )),
        buildLevel(12, "Primeros Auxilios", "Aprende respuestas iniciales ante accidentes comunes.",
            "👨‍⚕️ En emergencias, los primeros auxilios pueden salvar vidas. Ante una herida 🩹 debes lavar y cubrir bien. Una quemadura 🔥 requiere agua fria inmediatamente. Un golpe 🧊 se trata con hielo envuelto en tela. Y ante cualquier emergencia 📞 lo primero es pedir ayuda. ¡Aprende a actuar!",
            Color(0xFFEF9A9A), listOf(
            CardPair(left = "🩹 Herida", right = "Lavar y cubrir"),
            CardPair(left = "🔥 Quemadura", right = "Agua fria"),
            CardPair(left = "🧊 Golpe", right = "Hielo envuelto"),
            CardPair(left = "📞 Emergencia", right = "Pedir ayuda")
        )),
        buildLevel(13, "Salud Mental", "Identifica practicas para bienestar emocional.",
            "👨‍⚕️ La salud mental es tan importante como la fisica. Respirar profundo 🫁 baja la ansiedad en momentos de estres. Hablar con alguien 🗣️ alivia la tension emocional. Tomar pausas 🧠 da descanso mental necesario. Buscar apoyo 🤝 en una red de personas de confianza es fundamental. ¡Cuida tu mente!",
            Color(0xFFFFCCBC), listOf(
            CardPair(left = "🫁 Respirar", right = "Baja ansiedad"),
            CardPair(left = "🗣️ Hablar", right = "Alivia tension"),
            CardPair(left = "🧠 Pausas", right = "Descanso mental"),
            CardPair(left = "🤝 Apoyo", right = "Red de ayuda")
        )),
        buildLevel(14, "Manejo de Estres", "Relaciona tecnicas simples para controlar el estres.",
            "👨‍⚕️ El estres es parte de la vida, pero podemos manejarlo. Escribir en un diario 📓 ordena los pensamientos. Escuchar musica 🎵 relaja el cuerpo. Caminar 🚶 libera tension acumulada. Y descansar bien 🛌 recupera la energia perdida. ¡Encuentra cada tecnica con su beneficio!",
            Color(0xFFDCEDC8), listOf(
            CardPair(left = "📓 Escribir", right = "Ordena ideas"),
            CardPair(left = "🎵 Musica", right = "Relaja cuerpo"),
            CardPair(left = "🚶 Caminar", right = "Libera tension"),
            CardPair(left = "🛌 Descanso", right = "Recupera energia")
        )),
        buildLevel(15, "Seguridad Vial", "Asocia medidas que previenen lesiones en traslados.",
            "👨‍⚕️ Los accidentes viales son prevenibles. El cinturon de seguridad 🚗 protege en caso de choque. El casco ⛑️ cuida la cabeza de ciclistas y motociclistas. Respetar el semaforo 🚦 mantiene el orden en el trafico. Y usar el celular 📵 mientras manejas causa accidentes graves. ¡Maneja seguro!",
            Color(0xFFB3E5FC), listOf(
            CardPair(left = "🚗 Cinturon", right = "Protege en choque"),
            CardPair(left = "⛑️ Casco", right = "Cuida cabeza"),
            CardPair(left = "🚦 Semaforo", right = "Orden de paso"),
            CardPair(left = "📵 No celular", right = "Evita distraccion")
        )),
        buildLevel(16, "Salud Respiratoria", "Reconoce cuidados para pulmones sanos.",
            "👨‍⚕️ Tus pulmones merecen aire limpio. No fumar 🚭 mantiene los pulmones fuertes por mas tiempo. El aire limpio 🌬️ mejora la calidad de cada respiracion. Si sientes falta de aire 😮‍💨 busca atencion medica inmediata. El ejercicio cardiovascular 🏃 aumenta tu capacidad pulmonar. ¡Respira bien, vive mejor!",
            Color(0xFF80DEEA), listOf(
            CardPair(left = "🚭 No fumar", right = "Pulmones fuertes"),
            CardPair(left = "🌬️ Aire limpio", right = "Mejor respiracion"),
            CardPair(left = "😮‍💨 Falta aire", right = "Buscar atencion"),
            CardPair(left = "🏃 Cardio", right = "Capacidad pulmonar")
        )),
        buildLevel(17, "Salud Digestiva", "Relaciona habitos que favorecen buena digestion.",
            "👨‍⚕️ Tu sistema digestivo procesa todo lo que comes. La fibra 🥗 de frutas y verduras mejora el transito intestinal. El agua 💧 es esencial para una buena digestion. Las comidas muy grasosas 🍟 generan pesadez y malestar. Comer despacio 🍽️ mejora la absorcion de nutrientes. ¡Cuida tu digestion!",
            Color(0xFFC5E1A5), listOf(
            CardPair(left = "🥗 Fibra", right = "Mejor transito"),
            CardPair(left = "💧 Agua", right = "Ayuda digestion"),
            CardPair(left = "🍟 Grasas altas", right = "Pesadez"),
            CardPair(left = "🍽️ Comer lento", right = "Mejor absorcion")
        )),
        buildLevel(18, "Salud del Corazon", "Refuerza practicas para proteger sistema cardiovascular.",
            "👨‍⚕️ Tu corazon ❤️ late unas 100,000 veces al dia bombeando sangre. Reducir la sal 🧂 ayuda a controlar la presion arterial. El ejercicio regular 🏃 cuida las arterias. Y manejar el estres 😌 reduce la carga sobre el corazon. ¡Cuida el motor de tu cuerpo y encuentra los pares!",
            Color(0xFFFFCDD2), listOf(
            CardPair(left = "❤️ Corazon", right = "Bombea sangre"),
            CardPair(left = "🧂 Menos sal", right = "Controla presion"),
            CardPair(left = "🏃 Ejercicio", right = "Cuida arterias"),
            CardPair(left = "😌 Menos estres", right = "Menor carga cardiaca")
        )),
        buildLevel(19, "Salud Comunitaria", "Aprende acciones colectivas para entornos saludables.",
            "👨‍⚕️ La salud no es solo individual, es comunitaria. Mantener la limpieza 🧹 del entorno reduce focos de infeccion. Tirar la basura en contenedores cerrados 🚮 evita plagas. Informar sobre riesgos 💬 a los vecinos es prevencion colectiva. Y colaborar 🤲 juntos mejora la salud de toda la comunidad. ¡Unidos somos mas fuertes!",
            Color(0xFFD1C4E9), listOf(
            CardPair(left = "🧹 Limpieza", right = "Menos focos infeccion"),
            CardPair(left = "🚮 Basura cerrada", right = "Evita plagas"),
            CardPair(left = "💬 Informar riesgos", right = "Prevencion comun"),
            CardPair(left = "🤲 Colaborar", right = "Mejora comunidad")
        )),
        buildLevel(20, "Habitos Integrales", "Integra practicas clave para una salud completa.",
            "👨‍⚕️ ¡Felicidades por llegar al nivel 20! Este es el nivel de integracion. Hidratarse 💧 es funcion corporal basica. Comer balanceado 🥦 aporta nutrientes completos. Dormir bien 😴 permite la recuperacion total. Y moverse diariamente 🏃 genera energia y bienestar. ¡Demuestra todo lo que aprendiste!",
            Color(0xFFFFF59D), listOf(
            CardPair(left = "💧 Hidratarse", right = "Funcion corporal"),
            CardPair(left = "🥦 Comer balanceado", right = "Nutrientes completos"),
            CardPair(left = "😴 Dormir bien", right = "Recuperacion total"),
            CardPair(left = "🏃 Moverse diario", right = "Energia y bienestar")
        )),
        // === NUEVOS NIVELES 21-40 ===
        buildLevel(21, "Diabetes", "Reconoce senales y controles de glucosa en sangre.",
            "👨‍⚕️ La diabetes es una condicion silenciosa pero manejable. Una glucosa 🩸 muy alta en sangre puede provocar sed extrema y cansancio. La insulina 💉 es la hormona que regula el azucar. El ejercicio 🏃 mejora la sensibilidad a la insulina. Y llevar un control medico 🩺 regular es fundamental. ¡Aprende a identificar las señales!",
            Color(0xFFFFE0B2), listOf(
            CardPair(left = "🩸 Glucosa alta", right = "Sed extrema"),
            CardPair(left = "💉 Insulina", right = "Regula azucar"),
            CardPair(left = "🏃 Ejercicio", right = "Mejora sensibilidad"),
            CardPair(left = "🩺 Control medico", right = "Revision glucosa")
        )),
        buildLevel(22, "Obesidad", "Identifica habitos que previenen el sobrepeso.",
            "👨‍⚕️ La obesidad es un factor de riesgo para muchas enfermedades. El Indice de Masa Corporal o IMC ⚖️ mide la relacion peso-talla. Controlar las porciones 🍽️ evita el consumo excesivo de calorias. Los alimentos ultraprocesados 🍔 contribuyen al aumento de peso. Y la actividad fisica diaria 🚴 es clave para mantener un peso saludable. ¡Elige bien!",
            Color(0xFFFFF3E0), listOf(
            CardPair(left = "⚖️ IMC", right = "Indice masa corporal"),
            CardPair(left = "🍽️ Porciones", right = "Control calorico"),
            CardPair(left = "🍔 Ultraprocesados", right = "Aumento de peso"),
            CardPair(left = "🚴 Actividad diaria", right = "Peso saludable")
        )),
        buildLevel(23, "Hipertension", "Entiende causas y medidas de control de la presion arterial.",
            "👨‍⚕️ La hipertension o presion alta 🫀 es llamada el asesino silencioso porque no duele. El exceso de sal 🧂 eleva la presion arterial. La relajacion 🧘 ayuda a bajar la tension. Los medicamentos 💊 recetados por el medico son esenciales para controlarla. Y el monitoreo regular 📊 permite detectar cambios. ¡Controla tu presion!",
            Color(0xFFFFEBEE), listOf(
            CardPair(left = "🧂 Sal excesiva", right = "Sube presion"),
            CardPair(left = "🧘 Relajacion", right = "Baja tension"),
            CardPair(left = "💊 Medicamento", right = "Control diario"),
            CardPair(left = "📊 Monitoreo", right = "Detecta cambios")
        )),
        buildLevel(24, "Salud Infantil", "Cuidados basicos esenciales para el desarrollo de los ninos.",
            "👨‍⚕️ Los primeros años de vida son fundamentales. La lactancia materna 🤱 aporta inmunidad natural al bebe. Las visitas al pediatra 👶 permiten monitorear el crecimiento. Las vacunas infantiles 💉 protegen contra enfermedades graves. Y el juego activo 🎠 estimula el desarrollo motor e intelectual. ¡Los ninos sanos son el futuro!",
            Color(0xFFE8F5E9), listOf(
            CardPair(left = "🤱 Lactancia", right = "Inmunidad natural"),
            CardPair(left = "👶 Pediatra", right = "Control mensual"),
            CardPair(left = "💉 Vacunas infantiles", right = "Proteccion temprana"),
            CardPair(left = "🎠 Juego activo", right = "Desarrollo motor")
        )),
        buildLevel(25, "Salud Sexual", "Prevencion y educacion para una vida sexual responsable.",
            "👨‍⚕️ Hablar de salud sexual es hablar de responsabilidad y autocuidado. Los metodos anticonceptivos 🛡️ ayudan a planificar la familia. Las infecciones de transmision sexual o ITS 🦠 se previenen con proteccion. La consulta ginecologica o urologica 🩺 es parte del cuidado preventivo. Y la educacion sexual 📚 es un derecho de todos. ¡Infórmate y protegete!",
            Color(0xFFFCE4EC), listOf(
            CardPair(left = "🛡️ Anticonceptivos", right = "Planificacion familiar"),
            CardPair(left = "🦠 ITS", right = "Infeccion transmision"),
            CardPair(left = "🩺 Consulta", right = "Cuidado preventivo"),
            CardPair(left = "📚 Educacion sexual", right = "Derecho de todos")
        )),
        buildLevel(26, "Envejecimiento Saludable", "Habitos para mantener calidad de vida en la vejez.",
            "👨‍⚕️ Envejecer bien es posible con los habitos correctos. El calcio 🥛 y la vitamina D son esenciales para mantener los huesos fuertes. Caminar diariamente 🚶 preserva la movilidad. La estimulacion mental 🧩 como leer o resolver acertijos previene el deterioro cognitivo. Y las relaciones sociales 👥 contribuyen al bienestar emocional. ¡La vejez puede ser una etapa plena!",
            Color(0xFFE3F2FD), listOf(
            CardPair(left = "🥛 Calcio", right = "Huesos fuertes"),
            CardPair(left = "🚶 Caminata", right = "Movilidad diaria"),
            CardPair(left = "🧩 Estimulacion mental", right = "Previene deterioro"),
            CardPair(left = "👥 Vida social", right = "Bienestar emocional")
        )),
        buildLevel(27, "Enfermedades Cronicas", "Manejo de padecimientos de largo plazo.",
            "👨‍⚕️ Las enfermedades cronicas requieren atencion constante. La adherencia terapeutica 💊 significa tomar el medicamento exactamente como lo indico el medico. El automonitoreo 📋 en casa permite detectar cambios a tiempo. Las citas de seguimiento 📅 con el especialista son parte esencial del tratamiento. Y los grupos de apoyo 🤝 ayudan a sobrellevar el padecimiento. ¡El control es posible!",
            Color(0xFFE8EAF6), listOf(
            CardPair(left = "💊 Adherencia", right = "Tomar medicamento"),
            CardPair(left = "📋 Automonitoreo", right = "Detecta cambios"),
            CardPair(left = "📅 Citas seguimiento", right = "Control periodico"),
            CardPair(left = "🤝 Grupo de apoyo", right = "Soporte emocional")
        )),
        buildLevel(28, "Adicciones", "Consecuencias del consumo de sustancias nocivas.",
            "👨‍⚕️ Las adicciones son enfermedades que afectan el cerebro y el cuerpo. El tabaco 🚬 daña los pulmones de forma progresiva e irreversible. El alcohol en exceso 🍺 afecta gravemente el higado. Las drogas 💊 alteran el sistema nervioso y generan dependencia. Buscar ayuda especializada 🆘 es el primer paso para la recuperacion. ¡La prevencion es la mejor cura!",
            Color(0xFFFBE9E7), listOf(
            CardPair(left = "🚬 Tabaco", right = "Daña pulmones"),
            CardPair(left = "🍺 Alcohol", right = "Afecta higado"),
            CardPair(left = "💊 Drogas", right = "Dependencia nerviosa"),
            CardPair(left = "🆘 Buscar ayuda", right = "Primer paso")
        )),
        buildLevel(29, "Emergencias Medicas", "Reconoce señales de alarma que requieren atencion inmediata.",
            "👨‍⚕️ Reconocer una emergencia medica puede salvar una vida. El dolor en el pecho 💔 puede ser señal de un posible infarto. La perdida repentina del habla o paralisis facial 🧠 son señales de un derrame cerebral o ACV. La dificultad para respirar severa 😮‍💨 requiere atencion urgente. Ante cualquier señal de alarma 🚨 llama de inmediato a servicios de emergencia. ¡Cada segundo cuenta!",
            Color(0xFFFFEBEE), listOf(
            CardPair(left = "💔 Dolor pecho", right = "Posible infarto"),
            CardPair(left = "🧠 Perdida habla", right = "Señal de ACV"),
            CardPair(left = "😮‍💨 Sin respirar", right = "Atencion urgente"),
            CardPair(left = "🚨 Señal alarma", right = "Llamar emergencias")
        )),
        buildLevel(30, "Epidemiologia", "Conceptos basicos de salud publica y control de enfermedades.",
            "👨‍⚕️ ¡Llegaste al nivel 30! La epidemiologia estudia como se distribuyen y controlan las enfermedades en la poblacion. Una endemia 📍 es una enfermedad que se mantiene en una region especifica. Una epidemia 📈 ocurre cuando hay un aumento inusual de casos. Una pandemia 🌍 afecta a multiples paises o continentes. Y la vigilancia epidemiologica 🔭 permite detectar brotes a tiempo. ¡Vas excelente!",
            Color(0xFFE0F2F1), listOf(
            CardPair(left = "📍 Endemia", right = "Region especifica"),
            CardPair(left = "📈 Epidemia", right = "Aumento inusual"),
            CardPair(left = "🌍 Pandemia", right = "Extension mundial"),
            CardPair(left = "🔭 Vigilancia", right = "Detecta brotes")
        )),
        buildLevel(31, "EcoSalud: Agua Segura", "Identifica practicas para consumir agua segura y evitar enfermedades.",
            "👨‍⚕️ Nuevo tema: EcoSalud 🌿. Empezamos con agua segura 🚰. Hervir el agua 🔥 reduce microbios. Guardarla en recipiente limpio 🫙 evita contaminacion. Usar cloro 💧 cuando sea necesario ayuda a desinfectar. Y lavarte las manos 🧼 antes de preparar bebidas protege a tu familia.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🚰 Agua segura", right = "Evita infecciones"),
            CardPair(left = "🔥 Hervir", right = "Reduce microbios"),
            CardPair(left = "🫙 Recipiente", right = "Evita contaminacion"),
            CardPair(left = "💧 Cloro", right = "Desinfecta agua")
        )),
        buildLevel(32, "EcoSalud: Aire Limpio", "Relaciona acciones en casa con mejor calidad del aire.",
            "👨‍⚕️ El aire que respiras importa 🌬️. Ventilar la casa 🪟 ayuda a renovar el aire. Evitar humo de tabaco 🚭 protege los pulmones. Limpiar el polvo 🧽 reduce alergias. Y si hay humo o contaminacion, usar cubrebocas 😷 puede ayudarte.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🪟 Ventilar", right = "Renueva el aire"),
            CardPair(left = "🚭 Sin humo", right = "Protege pulmones"),
            CardPair(left = "🧽 Limpieza", right = "Reduce alergias"),
            CardPair(left = "😷 Cubrebocas", right = "Filtra particulas")
        )),
        buildLevel(33, "EcoSalud: Mosquitos", "Previene criaderos y reduce riesgo de enfermedades transmitidas por mosquitos.",
            "👨‍⚕️ Los mosquitos 🦟 se reproducen en agua estancada. Voltear cubetas 🪣 y tapar recipientes 🛑 corta el ciclo. Usar repelente 🧴 reduce picaduras. Colocar mosquiteros 🪟 ayuda a dormir seguro. ¡Cuidar el entorno es cuidar tu salud!",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🦟 Mosquito", right = "Se cria en agua"),
            CardPair(left = "🪣 Voltear", right = "Evita criaderos"),
            CardPair(left = "🧴 Repelente", right = "Reduce picaduras"),
            CardPair(left = "🪟 Mosquitero", right = "Bloquea entrada")
        )),
        buildLevel(34, "EcoSalud: Residuos", "Distingue practicas que mantienen limpio tu entorno y evitan plagas.",
            "👨‍⚕️ La basura mal manejada atrae plagas 🐀. Separar residuos ♻️ facilita el reciclaje. Cerrar bien las bolsas 🗑️ evita olores. Sacar la basura a tiempo ⏰ reduce moscas. Y mantener patios limpios 🧹 ayuda a prevenir enfermedades.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "♻️ Separar", right = "Facilita reciclaje"),
            CardPair(left = "🗑️ Bolsa cerrada", right = "Evita plagas"),
            CardPair(left = "⏰ A tiempo", right = "Reduce moscas"),
            CardPair(left = "🧹 Patio limpio", right = "Previene riesgos")
        )),
        buildLevel(35, "EcoSalud: Reciclaje", "Relaciona materiales comunes con su manejo correcto.",
            "👨‍⚕️ Reciclar es una accion pequena con gran impacto 🌎. El plastico 🧴 se debe enjuagar. El vidrio 🍾 se recicla mejor sin residuos. El carton 📦 se aplasta para ahorrar espacio. Y las pilas 🔋 no van con la basura comun: llévalas a un centro de acopio.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🧴 Plastico", right = "Enjuagar antes"),
            CardPair(left = "🍾 Vidrio", right = "Sin residuos"),
            CardPair(left = "📦 Carton", right = "Aplastar"),
            CardPair(left = "🔋 Pilas", right = "Centro acopio")
        )),
        buildLevel(36, "EcoSalud: Alimentos Seguros", "Previene enfermedades por alimentos con higiene y almacenamiento.",
            "👨‍⚕️ La seguridad alimentaria empieza en casa 🍲. Lavar frutas y verduras 🥬 reduce microbios. Cocinar bien 🍳 elimina bacterias. Refrigerar alimentos ❄️ evita descomposicion. Y separar crudo y cocido 🔪 reduce contaminacion cruzada.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🥬 Lavar", right = "Reduce microbios"),
            CardPair(left = "🍳 Cocinar", right = "Elimina bacterias"),
            CardPair(left = "❄️ Refrigerar", right = "Evita descomposicion"),
            CardPair(left = "🔪 Separar", right = "Evita cruce")
        )),
        buildLevel(37, "EcoSalud: Hogar Seguro", "Identifica riesgos en casa y acciones para prevenir accidentes.",
            "👨‍⚕️ Un hogar seguro previene accidentes 🏠. Guardar quimicos fuera de alcance 🧴 protege a ninos. Mantener pasillos libres 🚪 evita caidas. Revisar cables 🔌 reduce riesgos electricos. Y tener numeros de emergencia 📞 a la mano ayuda en situaciones urgentes.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🧴 Quimicos", right = "Fuera de alcance"),
            CardPair(left = "🚪 Pasillo libre", right = "Evita caidas"),
            CardPair(left = "🔌 Cables", right = "Reduce riesgo"),
            CardPair(left = "📞 Emergencia", right = "Respuesta rapida")
        )),
        buildLevel(38, "EcoSalud: Ruido", "Relaciona el ruido con salud y medidas de proteccion.",
            "👨‍⚕️ El ruido excesivo 🔊 afecta tu salud. Exposicion prolongada 👂 puede danar la audicion. Descansar en un ambiente tranquilo 😴 mejora el sueno. Bajar el volumen 🎧 protege tus oidos. Y usar proteccion auditiva 🦻 en trabajo ruidoso reduce daño.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🔊 Ruido", right = "Afecta salud"),
            CardPair(left = "👂 Exposicion", right = "Daña audicion"),
            CardPair(left = "🎧 Volumen", right = "Bajar protege"),
            CardPair(left = "🦻 Proteccion", right = "Reduce daño")
        )),
        buildLevel(39, "EcoSalud: Calor Extremo", "Reconoce medidas para prevenir golpe de calor y deshidratacion.",
            "👨‍⚕️ En dias muy calurosos ☀️, tu cuerpo necesita apoyo. Beber agua 💧 con frecuencia previene deshidratacion. Buscar sombra 🌳 reduce el riesgo. Usar ropa ligera 👕 ayuda a enfriarte. Y si hay mareo o confusion 🚑, pide ayuda de inmediato.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "☀️ Calor", right = "Riesgo golpe"),
            CardPair(left = "💧 Agua", right = "Previene deshidratacion"),
            CardPair(left = "🌳 Sombra", right = "Baja temperatura"),
            CardPair(left = "🚑 Alarma", right = "Pedir ayuda")
        )),
        buildLevel(40, "EcoSalud: Humo en Casa", "Identifica como reducir humo y proteger vias respiratorias.",
            "¡Hola! Soy la Dr Jesús👨‍⚕️ El humo en interiores 🏠 puede irritar pulmones. Cocinar con ventilacion 🪟 reduce concentracion. Evitar quemar basura 🔥 disminuye toxicos. Mantener estufa en buen estado 🛠️ mejora la combustion. Y si hay tos o irritacion 😮‍💨, sal a un lugar con aire limpio.",
            Color(0xFFB7E4C7), listOf(
            CardPair(left = "🏠 Humo", right = "Irrita pulmones"),
            CardPair(left = "🪟 Ventilacion", right = "Reduce humo"),
            CardPair(left = "🔥 No quemar", right = "Menos toxicos"),
            CardPair(left = "🛠️ Estufa", right = "Mejor combustion")
        )) ,
                buildLevel(41, "Salud Menstrual", "Reconoce cambios normales y señales de alerta en el ciclo menstrual.",
        "¡Hola! Soy la Dra. Gaby 👩‍⚕️. Hoy hablamos del ciclo menstrual 🩸, algo totalmente normal. Un ciclo regular 📅 dura entre 21 y 35 días. El dolor moderado 😣 puede aliviarse con calor y descanso. Pero un dolor muy intenso 🚨 que no cede puede ser señal de algo que revisar. Llevar registro 📓 de tu ciclo ayuda a conocer tu cuerpo. ¡Encuentra los pares!",
        Color(0xFFFCE4EC), listOf(
            CardPair(left = "🩸 Ciclo normal", right = "21 a 35 días"),
            CardPair(left = "😣 Dolor leve", right = "Calor y descanso"),
            CardPair(left = "🚨 Dolor intenso", right = "Revisar con médico"),
            CardPair(left = "📓 Registro", right = "Conoce tu cuerpo")
        )),
        buildLevel(41, "Salud Menstrual", "Reconoce cambios normales y señales de alerta en el ciclo menstrual.",
            "¡Hola! Soy la Dra. Gaby 👩‍⚕️. Hoy hablamos del ciclo menstrual 🩸, algo totalmente normal. Un ciclo regular 📅 dura entre 21 y 35 días. El dolor moderado 😣 puede aliviarse con calor y descanso. Pero un dolor muy intenso 🚨 que no cede puede ser señal de algo que revisar. Llevar registro 📓 de tu ciclo ayuda a conocer tu cuerpo. ¡Encuentra los pares!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🩸 Ciclo normal", right = "21 a 35 días"),
                CardPair(left = "😣 Dolor leve", right = "Calor y descanso"),
                CardPair(left = "🚨 Dolor intenso", right = "Revisar con médico"),
                CardPair(left = "📓 Registro", right = "Conoce tu cuerpo")
            )),
        buildLevel(42, "Embarazo Saludable", "Identifica cuidados clave durante el embarazo.",
            "👩‍⚕️ ¡Bienvenida de vuelta! El embarazo es una etapa increíble llena de cambios. El ácido fólico 💊 antes y durante el embarazo reduce defectos del tubo neural. Las consultas prenatales 🩺 monitorean el desarrollo del bebé. Evitar el alcohol 🚫 protege al bebé desde el primer día. Y descansar bien 😴 es una necesidad, no un lujo. ¡Cuida cada etapa!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "💊 Ácido fólico", right = "Previene defectos"),
                CardPair(left = "🩺 Prenatal", right = "Control del bebé"),
                CardPair(left = "🚫 Sin alcohol", right = "Protege al bebé"),
                CardPair(left = "😴 Descanso", right = "Necesidad real")
            )),
        buildLevel(43, "Lactancia Materna", "Relaciona beneficios de la lactancia para mamá y bebé.",
            "👩‍⚕️ La Dra. Gaby aquí. La lactancia materna 🤱 es mucho más que alimentar: es vínculo, protección e inmunidad. La leche materna 🍼 cambia su composición según las necesidades del bebé. Amamantar reduce el riesgo de cáncer de mama 💪 en la mamá. La posición correcta 🪑 evita dolor y mejora el agarre. ¡Y pedir apoyo 🤝 a una especialista es completamente válido!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🤱 Lactancia", right = "Vínculo e inmunidad"),
                CardPair(left = "🍼 Leche materna", right = "Se adapta al bebé"),
                CardPair(left = "💪 Amamantar", right = "Reduce cáncer mama"),
                CardPair(left = "🪑 Posición", right = "Evita dolor")
            )),
        buildLevel(44, "Menopausia", "Comprende los cambios del climaterio y cómo manejarlos.",
            "👩‍⚕️ ¡Hola! La menopausia no es el fin, es una nueva etapa 🌸. Los bochornos 🥵 son oleadas de calor súbitas muy comunes. El calcio y vitamina D 🥛 son más importantes que nunca para los huesos. El ejercicio regular 🏃 ayuda a manejar cambios de humor y peso. Y hablar con tu médica 🩺 sobre síntomas fuertes es siempre la mejor decisión. ¡Tú puedes con esto!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🥵 Bochornos", right = "Calor súbito"),
                CardPair(left = "🥛 Calcio", right = "Protege huesos"),
                CardPair(left = "🏃 Ejercicio", right = "Regula cambios"),
                CardPair(left = "🩺 Consulta", right = "Manejo de síntomas")
            )),
        buildLevel(45, "Cáncer de Mama", "Reconoce señales tempranas y la importancia de la detección.",
            "👩‍⚕️ Soy la Dra. Gaby y este tema es muy importante. El autoexamen 🖐️ mensual te ayuda a conocer cómo es tu cuerpo normalmente. Cambios en la piel 🔍 o bultos nuevos deben revisarse sin demora. La mastografía 🩻 es clave a partir de los 40 años. Detectar a tiempo 📅 hace una gran diferencia en el tratamiento. ¡La detección temprana salva vidas!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🖐️ Autoexamen", right = "Mensual"),
                CardPair(left = "🔍 Cambios piel", right = "Revisar pronto"),
                CardPair(left = "🩻 Mastografía", right = "Desde los 40"),
                CardPair(left = "📅 Detección", right = "A tiempo salva")
            )),
        buildLevel(46, "Salud Ósea", "Identifica hábitos que fortalecen huesos y previenen osteoporosis.",
            "👩‍⚕️ ¡La Dra. Gaby contigo! Los huesos necesitan atención todos los días, no solo cuando duelen. El calcio 🥛 presente en lácteos y verduras de hoja verde es fundamental. La vitamina D ☀️ que produce el sol ayuda a absorberlo. El ejercicio de impacto 🏃 como caminar o saltar fortalece la densidad ósea. Y el tabaco 🚭 y alcohol debilitan los huesos. ¡Cuídalos hoy!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🥛 Calcio", right = "Fortalece huesos"),
                CardPair(left = "☀️ Vitamina D", right = "Absorción calcio"),
                CardPair(left = "🏃 Impacto", right = "Densidad ósea"),
                CardPair(left = "🚭 Tabaco", right = "Debilita huesos")
            )),
        buildLevel(47, "Anemia", "Relaciona causas y soluciones para niveles bajos de hierro.",
            "👩‍⚕️ ¿Te sientes cansada sin razón? Podría ser anemia 😴. El hierro 🥩 presente en carnes, legumbres y espinacas es esencial para producir glóbulos rojos. La vitamina C 🍊 ayuda a absorber mejor el hierro de los alimentos. El café y té ☕ tomados con las comidas reducen esa absorción. Y un análisis de sangre 🩸 confirma si hay deficiencia. ¡Cuida tu energía!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🥩 Hierro", right = "Glóbulos rojos"),
                CardPair(left = "🍊 Vitamina C", right = "Mejora absorción"),
                CardPair(left = "☕ Café con comida", right = "Reduce absorción"),
                CardPair(left = "🩸 Análisis", right = "Detecta deficiencia")
            )),
        buildLevel(48, "Salud Pélvica", "Reconoce hábitos que cuidan el suelo pélvico.",
            "👩‍⚕️ Soy la Dra. Gaby y hablemos de algo que pocas veces se menciona: el suelo pélvico 💪. Los ejercicios de Kegel 🏋️ fortalecen los músculos pélvicos. Evitar cargar peso excesivo ⚠️ protege esta zona. La hidratación 💧 previene infecciones urinarias frecuentes. Y consultar ante escapes de orina 🚽 es completamente normal y tiene solución. ¡Sin tabúes, con salud!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🏋️ Kegel", right = "Fortalece músculo"),
                CardPair(left = "⚠️ Peso excesivo", right = "Evitar cargar"),
                CardPair(left = "💧 Hidratación", right = "Previene infección"),
                CardPair(left = "🚽 Escape orina", right = "Tiene solución")
            )),
        buildLevel(49, "Tiroides", "Identifica señales de alteración tiroidea y su impacto en el cuerpo.",
            "👩‍⚕️ ¡La Dra. Gaby de nuevo! La tiroides 🦋 es una glándula pequeña con un trabajo enorme. El hipotiroidismo 🐢 hace que todo vaya más lento: cansancio, frío y aumento de peso. El hipertiroidismo ⚡ acelera todo: nerviosismo, calor y pérdida de peso. Un análisis de sangre 🩸 mide los niveles hormonales. Y el tratamiento médico 💊 restablece el equilibrio. ¡Conoce tu tiroides!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🐢 Hipotiroidismo", right = "Todo más lento"),
                CardPair(left = "⚡ Hipertiroidismo", right = "Todo acelerado"),
                CardPair(left = "🩸 Análisis", right = "Mide hormonas"),
                CardPair(left = "💊 Tratamiento", right = "Restablece equilibrio")
            )),
        buildLevel(50, "Salud Integral de la Mujer", "Integra los cuidados clave aprendidos en este bloque.",
            "👩‍⚕️ ¡Felicidades! Llegaste al nivel 50 con la Dra. Gaby 🎉. Has aprendido que tu cuerpo merece atención en cada etapa. El autoconocimiento 🔍 es la base del autocuidado. Las consultas preventivas 🩺 detectan problemas antes de que crezcan. Hablar sin tabúes 💬 con tu médica hace toda la diferencia. ¡Y recordar que pedir ayuda 🤝 es un acto de fortaleza, no de debilidad!",
            Color(0xFFFCE4EC), listOf(
                CardPair(left = "🔍 Autoconocimiento", right = "Base del cuidado"),
                CardPair(left = "🩺 Preventivo", right = "Detecta a tiempo"),
                CardPair(left = "💬 Sin tabúes", right = "Comunicación abierta"),
                CardPair(left = "🤝 Pedir ayuda", right = "Acto de fortaleza")
            )),
        buildLevel(51, "Ansiedad", "Reconoce síntomas de ansiedad y técnicas para manejarla.",
            "Hola, soy el Dr. Mateo 🧠👨‍⚕️, psicólogo de salud. Hoy hablamos de algo que muchos sienten pero pocos nombran: la ansiedad 😰. El corazón acelerado ❤️‍🔥 y los pensamientos en espiral son señales frecuentes. La respiración diafragmática 🫁 activa el sistema de calma del cuerpo. El ejercicio 🏃 libera tensión acumulada. Y pedir ayuda profesional 🛋️ no es debilidad, es inteligencia emocional.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "😰 Ansiedad", right = "Pensamientos espiral"),
                CardPair(left = "🫁 Respiración", right = "Activa calma"),
                CardPair(left = "🏃 Ejercicio", right = "Libera tensión"),
                CardPair(left = "🛋️ Apoyo profesional", right = "Inteligencia emocional")
            )),
        buildLevel(52, "Depresión", "Diferencia tristeza normal de depresión y conoce sus señales.",
            "👨‍⚕️ Soy el Dr. Mateo. La depresión 😔 no es tristeza pasajera, es una condición real que afecta el cerebro. Perder interés en actividades que antes disfrutabas 🎨 es una señal importante. El aislamiento social 🚪 puede empeorar el cuadro. La terapia psicológica 🛋️ ha demostrado ser muy efectiva. Y los medicamentos 💊 recetados por un especialista ayudan en casos moderados o severos.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "😔 Depresión", right = "Condición real"),
                CardPair(left = "🎨 Sin interés", right = "Señal de alerta"),
                CardPair(left = "🚪 Aislamiento", right = "Empeora cuadro"),
                CardPair(left = "🛋️ Terapia", right = "Muy efectiva")
            )),
        buildLevel(53, "Autoestima", "Relaciona hábitos que fortalecen la imagen y el amor propio.",
            "👨‍⚕️ Dr. Mateo aquí. La autoestima 💛 no es vanidad, es la base de tu bienestar mental. Hablar con compasión 🗣️ contigo mismo cambia cómo te relacionas con el mundo. Reconocer tus logros 🏅 por pequeños que sean alimenta la confianza. Establecer límites 🛑 con personas que te agotan es un acto de amor propio. Y rodearte de vínculos sanos 👥 nutre tu salud emocional.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "💛 Autoestima", right = "Base del bienestar"),
                CardPair(left = "🗣️ Compasión", right = "Habla bien de ti"),
                CardPair(left = "🏅 Logros", right = "Alimenta confianza"),
                CardPair(left = "🛑 Límites", right = "Amor propio")
            )),
        buildLevel(54, "Duelo", "Comprende las etapas del duelo y cómo acompañar el proceso.",
            "👨‍⚕️ El Dr. Mateo contigo en este tema delicado. El duelo 💔 no es solo por la muerte, también ocurre ante pérdidas de relaciones, empleos o etapas de vida. La negación 😶 suele ser la primera reacción natural. El llanto 😢 es una forma sana de procesar el dolor, no una señal de debilidad. El tiempo y el apoyo 🤝 permiten sanar. Y buscar ayuda profesional 🛋️ acelera el proceso de manera saludable.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "💔 Duelo", right = "Proceso natural"),
                CardPair(left = "😶 Negación", right = "Primera etapa"),
                CardPair(left = "😢 Llanto", right = "Procesa el dolor"),
                CardPair(left = "🛋️ Apoyo", right = "Facilita sanar")
            )),
        buildLevel(55, "Burnout", "Reconoce señales de agotamiento extremo por trabajo o responsabilidades.",
            "👨‍⚕️ Soy el Dr. Mateo. ¿Sientes que das todo y ya no te queda nada? Eso se llama burnout 🔥. El agotamiento extremo 😩 que no mejora con dormir es una señal clara. La despersonalización 🤖, sentirte como robot sin emoción, aparece en casos avanzados. Poner límites al trabajo 🛑 no es flojera, es sobrevivencia. Y desconectarte digitalmente 📵 aunque sea una hora al día ayuda mucho.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "🔥 Burnout", right = "Agotamiento extremo"),
                CardPair(left = "😩 Sin recuperar", right = "Señal clara"),
                CardPair(left = "🛑 Límites trabajo", right = "Es necesario"),
                CardPair(left = "📵 Desconexión", right = "Restaura energía")
            )),
        buildLevel(56, "Mindfulness", "Asocia técnicas de atención plena con sus beneficios.",
            "👨‍⚕️ Dr. Mateo aquí. Mindfulness 🧘 es simplemente prestar atención al momento presente sin juzgar. La meditación guiada 🎧 entrena el cerebro para calmarse. Observar la respiración 🫁 durante 5 minutos reduce el cortisol. Comer sin pantallas 🍽️ es una forma sencilla de practicar atención plena. Y la constancia 📅 de pocos minutos diarios genera cambios reales en el cerebro.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "🧘 Mindfulness", right = "Momento presente"),
                CardPair(left = "🎧 Meditación", right = "Calma el cerebro"),
                CardPair(left = "🍽️ Sin pantallas", right = "Atención plena"),
                CardPair(left = "📅 Constancia", right = "Cambio real")
            )),
        buildLevel(57, "Relaciones Tóxicas", "Identifica señales de vínculos dañinos y cómo protegerte.",
            "👨‍⚕️ El Dr. Mateo contigo. No todas las relaciones nos hacen bien. El control excesivo 🔒 de otra persona sobre tu vida es una señal de alerta. La manipulación emocional 🎭 incluye hacerte sentir culpable constantemente. Alejarte de quien te hace sentir mal 🚶 es válido y necesario. Y reconstruir tu red de apoyo 👥 con personas que sumen es parte de sanar.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "🔒 Control", right = "Señal de alerta"),
                CardPair(left = "🎭 Manipulación", right = "Culpa constante"),
                CardPair(left = "🚶 Alejarte", right = "Es válido"),
                CardPair(left = "👥 Red sana", right = "Parte de sanar")
            )),
        buildLevel(58, "Sueño y Mente", "Relaciona el descanso con el equilibrio emocional y mental.",
            "👨‍⚕️ Soy el Dr. Mateo. Dormir mal no solo cansa el cuerpo, también afecta tu mente 🧠. La privación de sueño 😵 aumenta la irritabilidad y la ansiedad. Crear una rutina nocturna 🌙 le avisa al cerebro que es hora de descansar. El magnesio 🌿 en nueces y semillas favorece la relajación. Y apagar dispositivos 📵 30 minutos antes de dormir mejora la calidad del sueño.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "😵 Sin dormir", right = "Ansiedad e irritación"),
                CardPair(left = "🌙 Rutina nocturna", right = "Avisa al cerebro"),
                CardPair(left = "🌿 Magnesio", right = "Favorece relax"),
                CardPair(left = "📵 Sin dispositivos", right = "Mejor sueño")
            )),
        buildLevel(59, "Inteligencia Emocional", "Reconoce y gestiona tus emociones para mejorar tu bienestar.",
            "👨‍⚕️ Dr. Mateo aquí. La inteligencia emocional 💛 es la habilidad de entender y manejar lo que sientes. Identificar la emoción 🏷️ con nombre propio reduce su intensidad. Hacer una pausa ⏸️ antes de reaccionar evita conflictos. La empatía 👂 hacia los demás fortalece las relaciones. Y expresar lo que sientes 🗣️ con claridad es más poderoso que explotar o guardar silencio.",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "🏷️ Nombrar emoción", right = "Reduce intensidad"),
                CardPair(left = "⏸️ Pausar", right = "Evita conflicto"),
                CardPair(left = "👂 Empatía", right = "Fortalece vínculo"),
                CardPair(left = "🗣️ Expresar", right = "Más poderoso")
            )),
        buildLevel(60, "Bienestar Mental Integral", "Integra las prácticas clave de salud mental aprendidas.",
            "👨‍⚕️ ¡Llegaste al nivel 60 con el Dr. Mateo! 🎉 Has recorrido un camino valioso. El autocuidado emocional 💛 es una práctica diaria, no un destino. Pedir ayuda 🛋️ cuando lo necesitas es una fortaleza. Las conexiones humanas 👥 son el mejor antídoto contra el malestar mental. Y tú, que llegaste hasta aquí 🏅, ya demostraste que te importa tu bienestar. ¡Eso es lo más importante!",
            Color(0xFFE8EAF6), listOf(
                CardPair(left = "💛 Autocuidado", right = "Práctica diaria"),
                CardPair(left = "🛋️ Pedir ayuda", right = "Es fortaleza"),
                CardPair(left = "👥 Conexión", right = "Antídoto mental"),
                CardPair(left = "🏅 Compromiso", right = "Lo más importante")
            )),
        buildLevel(61, "El Diente y Tú", "Conoce las partes del diente y su función.",
            "¡Hola! Soy la Dra. Gaby 👩‍⚕️ y hoy me pongo el delantal de odontóloga 🦷✨. ¿Sabías que un diente tiene varias capas? El esmalte 🛡️ es la parte más dura del cuerpo humano, más que los huesos. La dentina 🦴 está debajo y es más sensible. La pulpa ❤️ contiene nervios y vasos sanguíneos. ¡Encuentra los pares y conoce tu sonrisa por dentro!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🛡️ Esmalte", right = "Capa más dura"),
                CardPair(left = "🦴 Dentina", right = "Capa sensible"),
                CardPair(left = "❤️ Pulpa", right = "Nervios y vasos"),
                CardPair(left = "🦷 Raíz", right = "Fija el diente")
            )),
        buildLevel(62, "Cepillado Perfecto", "Aprende la técnica correcta de cepillado dental.",
            "👩‍⚕️ ¡La Dra. Gaby con más secretos dentales! Cepillarse no es solo frotar y ya 😅. La técnica Bass 🪥 consiste en mover el cepillo en círculos pequeños junto a la encía. Dos minutos ⏱️ es el tiempo mínimo para un buen cepillado. La lengua 👅 también acumula bacterias y necesita limpieza. Y cambiar el cepillo 🔄 cada 3 meses mantiene las cerdas efectivas. ¡A emparejar!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🪥 Técnica Bass", right = "Círculos en encía"),
                CardPair(left = "⏱️ 2 minutos", right = "Tiempo mínimo"),
                CardPair(left = "👅 Lengua", right = "Limpiar también"),
                CardPair(left = "🔄 Cada 3 meses", right = "Cambiar cepillo")
            )),
        buildLevel(63, "Hilo y Enjuague", "Completa tu rutina con hilo dental y enjuague bucal.",
            "👩‍⚕️ ¡Dra. Gaby aquí! El cepillo solo limpia el 60% de tu boca. El 40% restante vive entre tus dientes 😱. El hilo dental 🧵 elimina placa y restos donde el cepillo no llega. Úsalo antes de cepillarte 📋 para que el flúor llegue mejor. El enjuague bucal 🫧 no reemplaza el cepillado, pero complementa. Y el flúor 💎 fortalece el esmalte y previene caries. ¡Completa tu rutina!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🧵 Hilo dental", right = "40% restante"),
                CardPair(left = "📋 Antes de cepillar", right = "Mejor absorción"),
                CardPair(left = "🫧 Enjuague", right = "Complementa rutina"),
                CardPair(left = "💎 Flúor", right = "Fortalece esmalte")
            )),
        buildLevel(64, "Caries: El Enemigo", "Entiende cómo se forma la caries y cómo detenerla.",
            "👩‍⚕️ Soy la Dra. Gaby y hoy presentamos al villano número uno de la boca: la caries 🦠. Se forma cuando las bacterias 🦠 se alimentan del azúcar y producen ácido. Ese ácido destruye el esmalte ⚠️ poco a poco sin que lo notes al principio. La placa bacteriana 🟡 es la película pegajosa donde viven esas bacterias. Y una caries detectada a tiempo 🩺 se trata fácil con una pequeña obturación. ¡Conócela para vencerla!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🦠 Bacterias", right = "Producen ácido"),
                CardPair(left = "⚠️ Ácido", right = "Destruye esmalte"),
                CardPair(left = "🟡 Placa", right = "Casa de bacterias"),
                CardPair(left = "🩺 A tiempo", right = "Fácil de tratar")
            )),
        buildLevel(65, "Encías Sanas", "Reconoce señales de salud y enfermedad en las encías.",
            "👩‍⚕️ ¡La Dra. Gaby al rescate de tus encías! Las encías sanas 💚 son rosadas y firmes, no sangran al cepillarte. Si sangran al cepillar 🩸 es una señal temprana de gingivitis. La gingivitis 🔴 es inflamación de encías causada por placa acumulada. Si no se trata, avanza a periodontitis 💀 que puede afectar el hueso. La limpieza profesional 🦷 cada 6 meses previene todo esto. ¡Cuida tus encías hoy!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "💚 Encía sana", right = "Rosada y firme"),
                CardPair(left = "🩸 Sangrado", right = "Señal gingivitis"),
                CardPair(left = "🔴 Gingivitis", right = "Placa acumulada"),
                CardPair(left = "🦷 Limpieza", right = "Cada 6 meses")
            )),
        buildLevel(66, "Sensibilidad Dental", "Identifica causas y soluciones para dientes sensibles.",
            "👩‍⚕️ Dra. Gaby aquí. ¿Sientes ese dolor agudo al tomar algo frío o caliente? 🥶🔥 Eso es sensibilidad dental. El esmalte desgastado 😬 expone los túbulos de la dentina al exterior. Cepillarse muy fuerte 💪 paradójicamente daña el esmalte con el tiempo. El pasta para sensibilidad 🧴 bloquea esos túbulos y reduce el dolor. Y el bruxismo 😤 o rechinar los dientes de noche también la causa. ¡Cuida tu esmalte!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🥶 Dolor frío", right = "Sensibilidad"),
                CardPair(left = "😬 Esmalte", right = "Expone dentina"),
                CardPair(left = "🧴 Pasta especial", right = "Bloquea túbulos"),
                CardPair(left = "😤 Bruxismo", right = "Rechinar dientes")
            )),
        buildLevel(67, "Ortodoncia", "Comprende por qué y cómo se corrige la posición dental.",
            "👩‍⚕️ ¡La Dra. Gaby hablando de brackets y alineadores! La maloclusión 🦷 es cuando los dientes no encajan correctamente al morder. Los brackets metálicos 🔩 aplican presión gradual para mover los dientes. Los alineadores transparentes 🫙 son removibles y casi invisibles. La retención 🛡️ después del tratamiento evita que los dientes regresen. Y la ortodoncia no solo es estética: mejora la mordida y la limpieza. ¡Sonríe con confianza!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🦷 Maloclusión", right = "Mordida incorrecta"),
                CardPair(left = "🔩 Brackets", right = "Presión gradual"),
                CardPair(left = "🫙 Alineadores", right = "Removibles"),
                CardPair(left = "🛡️ Retención", right = "Evita regresión")
            )),
        buildLevel(68, "Alimentación y Dientes", "Relaciona alimentos con su impacto en la salud dental.",
            "👩‍⚕️ Soy la Dra. Gaby y lo que comes también afecta tu boca 🍎🦷. El queso 🧀 neutraliza el ácido bucal y protege el esmalte. Las manzanas 🍏 estimulan la producción de saliva, que limpia naturalmente. Los refrescos 🥤 son doble peligro: azúcar y ácido que atacan el esmalte. Y el agua con flúor 💧 es el mejor aliado de tus dientes todo el día. ¡Elige bien lo que entra por tu boca!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🧀 Queso", right = "Neutraliza ácido"),
                CardPair(left = "🍏 Manzana", right = "Estimula saliva"),
                CardPair(left = "🥤 Refresco", right = "Doble ataque"),
                CardPair(left = "💧 Agua flúor", right = "Mejor aliado")
            )),
        buildLevel(69, "Urgencias Dentales", "Aprende qué hacer ante una emergencia bucal.",
            "👩‍⚕️ ¡Dra. Gaby en modo urgencias! Los accidentes pasan y es mejor estar preparado. Si se cae un diente 🦷 por golpe, no lo laves con agua: guárdalo en leche 🥛 y ve al dentista en menos de una hora. Un absceso 🔴 es una infección con pus que duele mucho y requiere atención inmediata. El dolor dental intenso 😖 nunca debe ignorarse con solo analgésicos. Y una fractura dental 💔 debe cubrirse y atenderse ese mismo día. ¡Actúa rápido!",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🦷 Diente caído", right = "Guardar en leche"),
                CardPair(left = "🔴 Absceso", right = "Infección urgente"),
                CardPair(left = "😖 Dolor intenso", right = "No ignorar"),
                CardPair(left = "💔 Fractura", right = "Atender ese día")
            )),
        buildLevel(70, "Sonrisa para Toda la Vida", "Integra los hábitos dentales clave de este bloque.",
            "👩‍⚕️ ¡Llegaste al nivel 70 con la Dra. Gaby! 🎉🦷 Tu sonrisa te acompañará toda la vida y merece el mejor cuidado. El cepillado y hilo diario 🪥🧵 son la base de todo. Las visitas al dentista 🩺 cada 6 meses previenen el 80% de los problemas. Evitar azúcar en exceso 🍬 y tomar agua 💧 son hábitos que marcan la diferencia. ¡Una boca sana es salud para todo tu cuerpo! Gracias por aprender conmigo. 😁",
            Color(0xFFE0F7FA), listOf(
                CardPair(left = "🪥 Rutina diaria", right = "Base de todo"),
                CardPair(left = "🩺 Cada 6 meses", right = "Previene 80%"),
                CardPair(left = "🍬 Menos azúcar", right = "Protege esmalte"),
                CardPair(left = "💧 Agua", right = "Aliado dental")
            )),
    )
}
