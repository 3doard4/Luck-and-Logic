package com.example.luckandlogic.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

@Composable
fun TelaHistorico(
    aoVoltar: () -> Unit,
    viewModel: GameResultViewModel
) {
    val resultados by viewModel.results.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadResults()
    }

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
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text = "📜 Histórico de Resultados",
                fontFamily = fonteCassino,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                items(resultados) { result ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        border = BorderStroke(1.dp, Color(0xFFFFD700)),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "🎮 ${result.gameName}",
                                color = Color(0xFFFFD700),
                                fontSize = 20.sp,
                                fontFamily = fonteCassino,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Resultado: ${result.result}",
                                color = Color(0xFFFFD700),
                                fontSize = 18.sp,
                                fontFamily = fonteCassino
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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