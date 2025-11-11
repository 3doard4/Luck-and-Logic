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
fun TelaJogoDosDados(
    aoVoltar: () -> Unit,
    viewModel: GameResultViewModel
) {
    var dado1 by remember { mutableStateOf(1) }
    var dado2 by remember { mutableStateOf(1) }
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
                text = "🎲 Jogo dos Dados 🎲",
                fontFamily = fonteCassino,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    dado1 = Random.nextInt(1, 7)
                    dado2 = Random.nextInt(1, 7)
                    val soma = dado1 + dado2
                    mensagem = when (soma) {
                        7, 11 -> {
                            viewModel.salvarResultado("Jogo dos Dados", "Vitória")
                            "🎉 Você venceu com soma $soma!"
                        }
                        2, 3, 12 -> {
                            viewModel.salvarResultado("Jogo dos Dados", "Derrota")
                            "💀 Você perdeu com soma $soma!"
                        }
                        else -> "🎯 Continue jogando..."
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
                    "Lançar Dados",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Dado 1: $dado1  |  Dado 2: $dado2",
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino,
                fontSize = 20.sp
            )

            Text(
                text = mensagem,
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = aoVoltar,
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