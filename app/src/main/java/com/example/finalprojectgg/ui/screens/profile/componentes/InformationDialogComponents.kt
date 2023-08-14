package com.example.finalprojectgg.ui.screens.profile.componentes

import androidx.appcompat.widget.DialogTitle
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.R

@Composable
fun InformationDialogView(
    title: String,
    icon: @Composable () -> Unit,
    text: String,
    informationDialogState: Boolean,
    confirmButton: @Composable () -> Unit,
    onDismiss: () -> Unit

    ) {
    if (informationDialogState) {
        AlertDialog(
            title = { Text(text = title) },
            icon = icon,
            text = {
                Text(text = text)
            },
            onDismissRequest = onDismiss ,
            confirmButton = confirmButton
        )
    }
}

@Preview
@Composable
fun InformationDialogPreview() {
    InformationDialogView(
        title = "Notification",
        informationDialogState = true,
        confirmButton = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Try Send Notification")
            }
        },
        text = "This is should be setting for notification, whether it's active or not. " +
                "But unfortunately our API currently not working. You can try send notification to simulate that behavior  ",
        icon = {

        },
        onDismiss = {}
    )
}