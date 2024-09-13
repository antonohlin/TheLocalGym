package com.app.thelocalgym.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
            Text(text = text, modifier = Modifier.padding(10.dp))
        }
        Divider()
    }
}

@Composable
@Preview(showBackground = true)
private fun ClickableListItemPreview() {
    ClickableListItem(
        text = "Sample Text",
        onClick = {}
    )
}