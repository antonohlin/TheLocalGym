package com.app.thelocalgym.composables.generics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClickableListItem(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector? = null
) {
    LocalGymButton(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(10),
        text = text,
        type = ButtonType.PRIMARY,
        icon = icon,
    )
}

@Composable
@Preview(showBackground = true)
private fun ClickableListItemPreview() {
    Column {
        ClickableListItem(
            text = "Sample Text",
            onClick = {}
        )
        ClickableListItem(
            text = "Sample Text",
            onClick = {},
            icon = Icons.AutoMirrored.Default.List
        )
    }
}