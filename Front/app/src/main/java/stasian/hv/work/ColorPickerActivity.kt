package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import stasian.hv.work.ui.theme.FrontTheme

@Composable
fun ColorPicker() {
    // Состояния для RGB
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }

    // Состояние для отображения цвета
    val color = Color(red = red, green = green, blue = blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Полоса для выбора красного
        Text("Red")
        Slider(
            value = red,
            onValueChange = { red = it },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        // Полоса для выбора зеленого
        Text("Green")
        Slider(
            value = green,
            onValueChange = { green = it },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        // Полоса для выбора синего
        Text("Blue")
        Slider(
            value = blue,
            onValueChange = { blue = it },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Показ выбранного цвета
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color, RoundedCornerShape(8.dp))
        )
    }
}
