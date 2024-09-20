package com.app.thelocalgym.composables.generics

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun LocalGymButton(
    modifier: Modifier = Modifier,
    text: String?,
    shape: Shape = ButtonDefaults.shape,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    type: ButtonType
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
        text?.let {
            Text(text = text)
        }
    }
}

enum class ButtonType {
    PRIMARY,
    SECONDARY,
    TERTIARY
}