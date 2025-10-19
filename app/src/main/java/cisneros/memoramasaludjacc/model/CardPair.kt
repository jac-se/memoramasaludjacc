package com.jacc.memoramasalud.model

/**
 * Representa un par de cartas relacionadas en el memorama.
 * Puede usarse para texto, imágenes o combinaciones.
 */

data class CardPair(
    val id: String = java.util.UUID.randomUUID().toString(),
    val left: String,
    val right: String
)



