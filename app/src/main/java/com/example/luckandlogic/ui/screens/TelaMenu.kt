package com.example.luckandlogic.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun TelaMenu(
    aoIrParaBlackjack: () -> Unit,
    aoIrParaRoleta: () -> Unit,
    aoIrParaDados: () -> Unit,
    aoIrParaHistorico: () -> Unit,
    aoVoltar: () -> Unit
) {
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
            modifier = Modifier.padding(30.dp)
        ) {
            Text(
                text = "Luck and Logic",
                fontFamily = fonteCassino,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(40.dp))

            ButtonCassino(texto = "Jogo de Blackjack", aoClicar = aoIrParaBlackjack)
            ButtonCassino(texto = "Jogo da Roleta", aoClicar = aoIrParaRoleta)
            ButtonCassino(texto = "Jogo dos Dados", aoClicar = aoIrParaDados)
            ButtonCassino(texto = "Histórico de Resultados", aoClicar = aoIrParaHistorico)
            ButtonCassino(texto = "Sair", aoClicar = aoVoltar)
        }
    }
}

@Composable
fun ButtonCassino(texto: String, aoClicar: () -> Unit) {
    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_bold))
    Button(
        onClick = aoClicar,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(2.dp, Color(0xFFFFD700)),
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ) {
        Text(
            text = texto,
            color = Color(0xFFFFD700),
            fontFamily = fonteCassino,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}