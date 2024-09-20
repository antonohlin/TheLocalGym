package com.app.thelocalgym.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClickableListItem(
    text: String,
    onClick: () -> Unit,
) {
    LocalGymButton(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(10),
        text = text,
        type = ButtonType.PRIMARY
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
            onClick = {}
        )
    }
}