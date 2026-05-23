package cisneros.memoramasaludjacc.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Composable
fun CardItem(
    text: String,
    faceUp: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Animate rotation to create a 3D flip effect
    val rotation by animateFloatAsState(
        targetValue = if (faceUp) 180f else 0f,
        animationSpec = tween(durationMillis = 400),
        label = "CardFlipRotation"
    )

    Card(
        modifier = modifier
            .aspectRatio(0.85f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .clickable(enabled = !faceUp, onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = if (rotation <= 90f) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surfaceVariant
            }
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (rotation <= 90f) 4.dp else 1.dp
        ),
        border = if (rotation > 90f) BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant) else null
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (rotation <= 90f) {
                // Back of the card (boca abajo)
                Text(
                    text = "🂠",
                    fontSize = 36.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            } else {
                // Front of the card (boca arriba) - rotate by 180f to avoid mirroring the text
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            rotationY = 180f
                        },
                    contentAlignment = Alignment.Center
                ) {
                    FittingCardText(text = text)
                }
            }
        }
    }
}

@Composable
private fun FittingCardText(text: String) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val scrollState = rememberScrollState()
        val fontSize = when {
            maxWidth < 56.dp || maxHeight < 64.dp -> 10.sp
            text.length > 28 -> 11.sp
            text.length > 20 -> 12.sp
            else -> 13.sp
        }
        val lineHeight = fontSize * 1.15f

        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = lineHeight,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(end = if (scrollState.maxValue > 0) 5.dp else 0.dp)
        )

        if (scrollState.maxValue > 0) {
            CardScrollIndicator(
                scrollOffset = scrollState.value,
                maxScroll = scrollState.maxValue,
                viewportHeight = maxHeight
            )
        }
    }
}

@Composable
private fun CardScrollIndicator(
    scrollOffset: Int,
    maxScroll: Int,
    viewportHeight: Dp
) {
    val density = LocalDensity.current
    val viewportPx = with(density) { viewportHeight.toPx() }
    val contentPx = viewportPx + maxScroll
    val thumbHeight = (viewportPx / contentPx * viewportPx)
        .coerceIn(with(density) { 14.dp.toPx() }, viewportPx)
    val travelPx = (viewportPx - thumbHeight).coerceAtLeast(0f)
    val thumbOffset = if (maxScroll > 0) travelPx * scrollOffset / maxScroll else 0f

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(viewportHeight)
                .background(
                    MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f),
                    RoundedCornerShape(2.dp)
                )
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(y = with(density) { thumbOffset.roundToInt().toDp() })
                .width(2.dp)
                .height(with(density) { thumbHeight.toDp() })
                .background(
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                    RoundedCornerShape(2.dp)
                )
        )
    }
}
