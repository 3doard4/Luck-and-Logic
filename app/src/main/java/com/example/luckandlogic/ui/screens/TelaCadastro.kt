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
fun TelaCadastro(
    aoCadastrar: () -> Unit,
    aoLogar: () -> Unit
) {
    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_bold))

    val auth = FirebaseAuth.getInstance()
    val escopo = rememberCoroutineScope()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
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
            Text(
                text = "Crie sua conta",
                fontFamily = fonteCassino,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFFD700),
                modifier = Modifier.padding(bottom = 6.dp)
            )

            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(2.dp)
                    .background(Color(0xFFFFD700))
            )

            Spacer(modifier = Modifier.height(16.dp))

            val nipes = listOf("♠", "♥", "♦", "♣")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                nipes.forEach { nipe ->
                    Text(
                        text = nipe,
                        color = if (nipe == "♥" || nipe == "♦") Color.Red else Color.Black,
                        fontFamily = fonteCassino,
                        fontSize = 28.sp,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            CampoCassino(valor = nome, aoMudar = { nome = it }, textoLabel = "Nome completo")
            CampoCassino(valor = email, aoMudar = { email = it }, textoLabel = "E-mail")
            CampoCassino(valor = senha, aoMudar = { senha = it }, textoLabel = "Senha", senha = true)
            CampoCassino(valor = confirmarSenha, aoMudar = { confirmarSenha = it }, textoLabel = "Confirmar senha", senha = true)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (senha != confirmarSenha) {
                        mensagem = "As senhas não coincidem."
                    } else if (email.isEmpty() || senha.isEmpty() || nome.isEmpty()) {
                        mensagem = "Preencha todos os campos."
                    } else {
                        carregando = true
                        escopo.launch {
                            auth.createUserWithEmailAndPassword(email, senha)
                                .addOnCompleteListener { tarefa ->
                                    carregando = false
                                    if (tarefa.isSuccessful) {
                                        mensagem = "Cadastro realizado com sucesso!"
                                        aoCadastrar()
                                    } else {
                                        mensagem = "Erro: ${tarefa.exception?.message}"
                                    }
                                }
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(2.dp, Color(0xFFFFD700)),
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = if (carregando) "Carregando..." else "CADASTRAR",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = mensagem,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = fonteCassino,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(onClick = { aoLogar() }) {
                Text(
                    text = "Já tem cadastro? Faça login!",
                    color = Color(0xFFFFD700),
                    fontFamily = fonteCassino,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun CampoCassino(
    valor: String,
    aoMudar: (String) -> Unit,
    textoLabel: String,
    senha: Boolean = false
) {
    val fonteCassino = FontFamily(Font(R.font.cinzeldecorative_regular))

    OutlinedTextField(
        value = valor,
        onValueChange = aoMudar,
        label = {
            Text(
                textoLabel,
                color = Color(0xFFFFD700),
                fontFamily = fonteCassino
            )
        },
        visualTransformation = if (senha) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFFFD700),
            unfocusedBorderColor = Color(0xFFFFD700),
            cursorColor = Color(0xFFFFD700),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp)
    )
}