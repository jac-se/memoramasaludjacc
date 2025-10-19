package cisneros.memoramasaludjacc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CardItem(text: String, faceUp: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                if (faceUp) Color(0xFFFFF8E1) else Color(0xFF1E88E5),
                shape = MaterialTheme.shapes.medium
            )
            .clickable(enabled = !faceUp, onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        if (faceUp) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color(0xFF37474F),
                lineHeight = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        } else {
            Text("?", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}