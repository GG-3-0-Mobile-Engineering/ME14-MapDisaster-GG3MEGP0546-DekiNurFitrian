package com.example.finalprojectgg.ui.screens.main.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FilterList
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.finalprojectgg.ui.components.CustomTextField
import com.example.finalprojectgg.ui.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarView(
    searchEnabled: Boolean,
    onSearchClicked: () -> Unit,
    onLeadingIconClicked: () -> Unit,
    onTrailingIconClicked: () -> Unit,
    onSearchValueChange: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    Row(
        Modifier
            .height(57.dp)
            .clip(ShapeDefaults.ExtraLarge)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(interactionSource = interactionSource, indication = null) {
                onSearchClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onLeadingIconClicked() }, enabled = searchEnabled) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
        var text by remember { mutableStateOf("") }

        if (searchEnabled) {
            CustomTextField(
                value = text,
                textStyle = MaterialTheme.typography.bodyLarge,
                onValueChange = {
                    text = it
                    onSearchValueChange(it)
                },
                placeholder = { Text("Search") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .weight(1f),
                readOnly = false
            )

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }
        } else {
            Text(
                text = "Search",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        AnimatedVisibility(visible = searchEnabled, enter = fadeIn(), exit = fadeOut()) {
            IconButton(onClick = {
                onTrailingIconClicked()
                focusManager.clearFocus()
            }) {
                Icon(imageVector = Icons.Outlined.FilterList, contentDescription = null)
            }
        }
    }
}