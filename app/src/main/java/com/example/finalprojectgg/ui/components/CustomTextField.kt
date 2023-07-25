package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    readOnly:Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable () -> Unit,
    textStyle: TextStyle,
    colors: TextFieldColors,
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        modifier = modifier,
        readOnly = readOnly,
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = value,
            innerTextField = it,
            enabled = true,
            placeholder = placeholder,
            singleLine = true,
            colors = colors,
            contentPadding = PaddingValues(vertical = 0.dp, horizontal = 0.dp),
            visualTransformation = VisualTransformation.None,
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}