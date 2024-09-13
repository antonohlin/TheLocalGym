package com.app.thelocalgym.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text( // TODO: Replace with icon
            "Back",
            modifier = Modifier.clickable { onLeftClick() },
            color = Color.White,
        )
        Text( // TODO: Always centered
            text = "The Local Gym",
            modifier = Modifier.padding(10.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text( // TODO: Replace with icon
            "",
            modifier = Modifier.clickable { onRightClick() },
        )
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar()
}