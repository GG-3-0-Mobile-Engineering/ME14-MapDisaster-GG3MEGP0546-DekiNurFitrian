package com.example.finalprojectgg.ui.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.finalprojectgg.ui.components.CustomTextField
import com.example.finalprojectgg.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    searchEnabled: Boolean,
    navController: NavHostController
) {
    Row(
        Modifier
            .clip(ShapeDefaults.ExtraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }, enabled = false) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
        var text by remember { mutableStateOf("") }
        CustomTextField(
            value = text,
            textStyle = MaterialTheme.typography.bodyLarge,
            onValueChange = { text = it },
            placeholder = { Text("Search") },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth(),
            readOnly = searchEnabled,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                navController.navigate(Screens.MapDisasterSearch.route)
                            }
                        }
                    }
                }
        )
    }
}