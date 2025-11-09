package com.example.luckandlogic.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.luckandlogic.R
import com.example.luckandlogic.viewmodel.GameResultViewModel
import kotlin.random.Random

@Composable
fun TelaRoleta(
    aoVoltarMenu: () -> Unit,
    viewModel: GameResultViewModel
) {
    var numeroEscolhido by remember { mutableStateOf(0) }
    var numeroSorteado by remember { mutableStateOf(-1) }
    var mensagem by remember { mutableStateOf("") }

    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_bold))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF450000),
                        Color(0xFF7A0000),
                        Color(0xFF1A0000)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "🎡 Roleta 🎡",
                fontFamily = fonteCassino,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    numeroEscolhido = Random.nextInt(0, 36)
                    numeroSorteado = Random.nextInt(0, 36)
                    if (numeroEscolhido == numeroSorteado) {
                        mensagem = "🎉 Você venceu! Número $numeroSorteado"
                        viewModel.salvarResultado("Roleta", "Vitória")
                    } else {
                        mensagem = "💀 Você perdeu! Saiu o número $numeroSorteado"
                        viewModel.salvarResultado("Roleta", "Derrota")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(2.dp, Color(0xFFFFD700)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .defaultMinSize(minWidth = 180.dp)
                    .height(55.dp)
            ) {
                Text(
                    "Girar Roleta",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = mensagem,
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = aoVoltarMenu,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(2.dp, Color(0xFFFFD700)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .defaultMinSize(minWidth = 160.dp)
                    .height(55.dp)
            ) {
                Text(
                    text = "Voltar ao Menu",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}