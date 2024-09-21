package com.app.thelocalgym.composables.generics

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LocalGymButton(
    modifier: Modifier = Modifier,
    text: String?,
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    type: ButtonType,
    icon: ImageVector? = null,
) {
    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        colors = when (type) {
            ButtonType.PRIMARY -> {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            }

            ButtonType.SECONDARY -> {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            }

            ButtonType.TERTIARY -> {
                ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            text?.let {
                Text(text = text)
            }
            HorizontalSpacer(width = 10)
            icon?.let {
                Icon(imageVector = icon, contentDescription = "Button icon")
            }
        }
    }
}

enum class ButtonType {
    PRIMARY,
    SECONDARY,
    TERTIARY
}

@Preview
@Composable
private fun LocalGymButtonPreview() {
    LocalGymButton(
        text = "Click me!",
        type = ButtonType.PRIMARY,
        icon = Icons.Default.AddCircle
    )
}