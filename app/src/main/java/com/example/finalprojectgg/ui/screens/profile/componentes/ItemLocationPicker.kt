package com.example.finalprojectgg.ui.screens.profile.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SheetContentLocationPicker() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .width(24.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.outline)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "Search Location",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10) {
                ItemLocationPicker()
            }
        }
    }
}

@Composable
fun ItemLocationPicker() {
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Nama Lokasi", style = MaterialTheme.typography.bodySmall)
            Checkbox(checked = true, onCheckedChange = {})
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline)
        )
    }
}