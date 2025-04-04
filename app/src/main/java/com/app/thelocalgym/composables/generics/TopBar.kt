package com.app.thelocalgym.composables.generics

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
    leftIcon: ImageVector = Icons.AutoMirrored.Default.ArrowBack,
    rightIcon: ImageVector = Icons.Default.Menu,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopStart) {
            if (showLeftIcon) {
                Icon(
                    imageVector = leftIcon,
                    contentDescription = "Left topbar icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.clickable { onLeftClick() }
                )
            }
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(
                text = "The Local Gym",
                fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,
                color = Color.White,
            )
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopEnd) {
            if (showRightIcon) {
                Icon(
                    imageVector = rightIcon,
                    contentDescription = "Right topbar icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.clickable { onRightClick() }
                )
            }
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