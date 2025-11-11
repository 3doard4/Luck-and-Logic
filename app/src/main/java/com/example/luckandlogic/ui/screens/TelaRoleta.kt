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
import com.example.luckandlogic.ui.components.ButtonCassino
import com.example.luckandlogic.viewmodel.GameResultViewModel
import kotlin.random.Random

@Composable
fun TelaRoleta(
    aoVoltarMenu: () -> Unit,
    viewModel: GameResultViewModel
) {
    var corEscolhida by remember { mutableStateOf<String?>(null) }
    var corSorteada by remember { mutableStateOf<String?>(null) }
    var mensagem by remember { mutableStateOf("") }

    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_bold))
    val cores = listOf("Vermelho", "Preto")

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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "🎡 ROLETA 🎡",
                fontFamily = fonteCassino,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ESCOLHA UMA COR:",
                fontFamily = fonteCassino,
                color = Color(0xFFFFD700),
                fontSize = 20.sp
            )

            // 🔹 Botões empilhados (um embaixo do outro)
            ButtonCassino("Vermelho") {
                corEscolhida = "Vermelho"
                corSorteada = cores.random()

                if (corEscolhida == corSorteada) {
                    mensagem = "🎉 Você venceu! Saiu $corSorteada!"
                    viewModel.salvarResultado("Roleta", "Vitória")
                } else {
                    mensagem = "💀 Você perdeu! Saiu $corSorteada!"
                    viewModel.salvarResultado("Roleta", "Derrota")
                }
            }

            ButtonCassino("Preto") {
                corEscolhida = "Preto"
                corSorteada = cores.random()

                if (corEscolhida == corSorteada) {
                    mensagem = "🎉 Você venceu! Saiu $corSorteada!"
                    viewModel.salvarResultado("Roleta", "Vitória")
                } else {
                    mensagem = "💀 Você perdeu! Saiu $corSorteada!"
                    viewModel.salvarResultado("Roleta", "Derrota")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (mensagem.isNotEmpty()) {
                Text(
                    text = mensagem,
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            ButtonCassino("Voltar ao Menu", aoVoltarMenu)
        }
    }
}