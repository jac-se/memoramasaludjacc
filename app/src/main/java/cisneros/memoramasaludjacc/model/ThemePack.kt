package com.jacc.memoramasalud.model

import androidx.compose.ui.graphics.Color
import java.util.UUID

/**
 * Paquete temático del memorama.
 */
data class ThemePack(
    val title: String,
    val pairs: List<CardPair>,
    val themeColor: Color = Color(0xFF4CAF50),
    val id: String = UUID.randomUUID().toString()
)
