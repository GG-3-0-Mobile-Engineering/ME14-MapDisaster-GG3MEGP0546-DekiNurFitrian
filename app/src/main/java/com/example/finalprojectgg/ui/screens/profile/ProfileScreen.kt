package com.example.finalprojectgg.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.finalprojectgg.ui.screens.profile.componentes.SheetContentLocationPicker
import com.example.finalprojectgg.ui.screens.profile.componentes.ThemeSwitcher
import com.example.finalprojectgg.ui.screens.profile.state.ProfileScreenEvent
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    viewModel: MainViewModel
) {
    val themeState by viewModel.themeState
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val sheetState =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val corutineScope = rememberCoroutineScope()
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                AsyncImage(
                    model = "https://images.unsplash.com/photo-1530893609608-32a9af3aa95c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=764&q=80",
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                )

                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "https://images.unsplash.com/photo-1504593811423-6dd665756598?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80",
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Mubariz Mehdizadeh",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    Text(
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .graphicsLayer {
                        translationY = -100f
                    }) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Column(modifier = Modifier.padding(all = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                corutineScope.launch {
                                    sheetState.show()
                                }
                            }) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Set Default Location",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(imageVector = Icons.Default.ArrowRight, contentDescription = null)
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.outline)
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DarkMode,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Set Theme",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            ThemeSwitcher(
                                darkTheme = themeState,
                                size = 40.dp,
                                onClick = {
                                    viewModel.onProfileScreenEvent(ProfileScreenEvent.ThemeSwitchChanged)
                                }
                            )
                        }
                    }
                }
            }
        }
        SheetLocationPicker(sheetState)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetLocationPicker(
    sheetState: ModalBottomSheetState
) {
    ModalBottomSheetLayout(
        sheetContent = {
            SheetContentLocationPicker()
        },
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = MaterialTheme.colorScheme.onSurface
    ) {

    }

}