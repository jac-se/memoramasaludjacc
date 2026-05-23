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
            "Hola, soy el Dr. César Leonardo👨‍⚕️. Hoy hablaremos del agua 💧, la base de la vida. ¿Sabias que cuando hace mucho calor 🥵 tu cuerpo pierde liquidos? El suero oral 🧂 recupera electrolitos y revisar que tu orina sea clara 🚽 es señal de buena hidratacion. ¡Encuentra los pares correctos!",
            Color(0xFF81D4FA), listOf(
            CardPair(left = "💧 Agua", right = "Hidrata el cuerpo"),
            CardPair(left = "🧂 Suero oral", right = "Recupera electrolitos"),
            CardPair(left = "🥵 Calor", right = "Aumenta deshidratacion"),
            CardPair(left = "🚽 Orina clara", right = "Senal de hidratacion")
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
            "👨‍⚕️ Tu sonrisa es tu carta de presentacion. El cepillado 🪥 debe hacerse 2 veces al dia para mantener dientes limpios. El hilo dental 🧵 limpia los espacios donde el cepillo no llega. El azucar 🍬 es el principal enemigo de tus dientes. ¡Y no olvides visitar al dentista 🦷 regularmente!",
            Color(0xFF80CBC4), listOf(
            CardPair(left = "🪥 Cepillado", right = "2 veces al dia"),
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
            "👨‍⚕️ El cuerpo humano esta disenado para moverse. Caminar 🏃 mejora la circulacion sanguinea. Estirar 🤸 los musculos reduce la rigidez. El ejercicio 💓 fortalece el corazon. El sedentarismo 🪑 aumenta el riesgo de enfermedades cronicas. ¡Muevete y encuentra los pares correctos!",
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
        // === NUEVOS NIVELES 21-30 ===
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
            "👨‍⚕️ ¡Llegaste al nivel final, nivel 30! La epidemiologia estudia como se distribuyen y controlan las enfermedades en la poblacion. Una endemia 📍 es una enfermedad que se mantiene en una region especifica. Una epidemia 📈 ocurre cuando hay un aumento inusual de casos. Una pandemia 🌍 afecta a multiples paises o continentes. Y la vigilancia epidemiologica 🔭 permite detectar brotes a tiempo. ¡Eres un experto en salud!",
            Color(0xFFE0F2F1), listOf(
            CardPair(left = "📍 Endemia", right = "Region especifica"),
            CardPair(left = "📈 Epidemia", right = "Aumento inusual"),
            CardPair(left = "🌍 Pandemia", right = "Extension mundial"),
            CardPair(left = "🔭 Vigilancia", right = "Detecta brotes")
        ))
    )
}
