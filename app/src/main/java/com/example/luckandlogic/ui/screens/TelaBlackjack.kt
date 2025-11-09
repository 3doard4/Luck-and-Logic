package com.example.luckandlogic.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun TelaBlackjack(
    aoVoltarMenu: () -> Unit,
    viewModel: GameResultViewModel
) {
    var jogador by remember { mutableStateOf(0) }
    var dealer by remember { mutableStateOf(0) }
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
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = "🃏 Blackjack 🃏",
                fontFamily = fonteCassino,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Text(
                text = "Sua pontuação: $jogador",
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino,
                fontSize = 20.sp
            )

            Text(
                text = "Pontuação do dealer: $dealer",
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino,
                fontSize = 20.sp
            )

            if (mensagem.isNotEmpty()) {
                Text(
                    text = mensagem,
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ButtonCassino("Pegar carta") {
                    jogador += Random.nextInt(1, 11)
                    if (jogador > 21) {
                        mensagem = "Você estourou! Dealer vence!"
                        viewModel.salvarResultado("Blackjack", "Derrota")
                    }
                }

                ButtonCassino("Parar") {
                    dealer = Random.nextInt(15, 23)
                    mensagem = when {
                        dealer > 21 || jogador > dealer -> {
                            viewModel.salvarResultado("Blackjack", "Vitória")
                            "Você venceu!"
                        }
                        dealer == jogador -> {
                            viewModel.salvarResultado("Blackjack", "Empate")
                            "Empate!"
                        }
                        else -> {
                            viewModel.salvarResultado("Blackjack", "Derrota")
                            "Dealer venceu!"
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            ButtonCassino("Voltar ao menu", aoVoltarMenu)
        }
    }
}