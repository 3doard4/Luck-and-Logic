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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.luckandlogic.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun TelaLogin(
    aoLogar: () -> Unit,
    irParaCadastro: () -> Unit
) {
    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_bold))
    val auth = FirebaseAuth.getInstance()
    val escopo = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var carregando by remember { mutableStateOf(false) }

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
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            // Título
            Text(
                text = "Bem-vindo de volta",
                fontFamily = fonteCassino,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700),
                modifier = Modifier.padding(bottom = 6.dp)
            )

            // Linha dourada
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(2.dp)
                    .background(Color(0xFFFFD700))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nipes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                val nipes = listOf("♠", "♥", "♦", "♣")
                nipes.forEach {
                    Text(
                        text = it,
                        color = if (it == "♥" || it == "♦") Color.Red else Color.Black,
                        fontFamily = fonteCassino,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // Campos
            CampoCassino(
                valor = email,
                aoMudar = { email = it },
                textoLabel = "E-mail"
            )
            CampoCassino(
                valor = senha,
                aoMudar = { senha = it },
                textoLabel = "Senha",
                senha = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de login
            Button(
                onClick = {
                    if (email.isEmpty() || senha.isEmpty()) {
                        mensagem = "Preencha todos os campos."
                    } else {
                        carregando = true
                        escopo.launch {
                            auth.signInWithEmailAndPassword(email, senha)
                                .addOnCompleteListener { tarefa ->
                                    carregando = false
                                    if (tarefa.isSuccessful) {
                                        mensagem = "Login realizado com sucesso!"
                                        aoLogar()
                                    } else {
                                        mensagem = "Erro: ${tarefa.exception?.message}"
                                    }
                                }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                border = BorderStroke(2.dp, Color(0xFFFFD700)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = if (carregando) "Carregando..." else "ENTRAR",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mensagem
            Text(
                text = mensagem,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = fonteCassino,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botão "Ainda não tem conta?"
            TextButton(onClick = { irParaCadastro() }) {
                Text(
                    text = "Ainda não tem conta? Cadastre-se já!",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 14.sp
                )
            }
        }
    }
}
