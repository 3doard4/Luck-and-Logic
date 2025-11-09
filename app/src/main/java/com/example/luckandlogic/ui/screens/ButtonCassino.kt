package com.example.luckandlogic.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.luckandlogic.R

@Composable
fun ButtonCassino(texto: String, onClick: () -> Unit) {
    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_regular))

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = ButtonDefaults.outlinedButtonBorder,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            texto,
            color = Color(0xFFFFD700),
            fontFamily = fonteCassino,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}