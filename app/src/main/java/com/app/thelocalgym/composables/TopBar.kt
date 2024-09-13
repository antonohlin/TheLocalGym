package com.app.thelocalgym.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar() {
    Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primary), horizontalArrangement = Arrangement.Center) {
        Text(text = "The Local Gym", modifier = Modifier.padding(10.dp))
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar()
}