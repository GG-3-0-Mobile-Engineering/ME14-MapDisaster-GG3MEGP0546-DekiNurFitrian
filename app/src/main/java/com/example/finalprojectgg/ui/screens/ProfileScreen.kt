package com.example.finalprojectgg.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.ui.components.DisasterItem
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.States
import com.kanyidev.searchable_dropdown.SearchableExpandedDropDownMenu


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen() {
    BottomSheetScaffold(sheetContent = {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {

        }
    }) {
        Box(
            modifier = Modifier
                .padding(bottom = it.calculateBottomPadding())
                .fillMaxWidth()
        )
    }
}

@Composable
fun DropDownItem(test: Sport) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .wrapContentSize()
    ) {
        Text(text = test.emoji)
        Spacer(modifier = Modifier.width(12.dp))
        Text(test.name)
    }
}

data class Sport(
    val name: String,
    val emoji: String
) {
    override fun toString(): String {
        return "$emoji $name"
    }
}