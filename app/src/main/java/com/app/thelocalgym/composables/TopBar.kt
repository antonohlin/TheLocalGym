package com.app.thelocalgym.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
    showLeftIcon: Boolean = false,
    showRightIcon: Boolean = false,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showLeftIcon) {
            Text(
                // TODO: Replace with icon
                "Back",
                modifier = Modifier
                    .clickable { onLeftClick() },
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text( // TODO: Always centered
            text = "The Local Gym",
            fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        if (showRightIcon) {
            Text(
                // TODO: Replace with icon
                "Other",
                modifier = Modifier
                    .clickable { onRightClick() },
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    Column {
        TopBar(
            onLeftClick = {},
            onRightClick = {},
            showLeftIcon = true,
            showRightIcon = true
        )
        TopBar(
            onLeftClick = {},
            onRightClick = {},
            showLeftIcon = true,
            showRightIcon = false
        )
        TopBar(
            onLeftClick = {},
            onRightClick = {},
            showLeftIcon = false,
            showRightIcon = true
        )
        TopBar(
            onLeftClick = {},
            onRightClick = {},
            showLeftIcon = false,
            showRightIcon = false
        )
    }
}