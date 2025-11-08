package com.example.luckandlogic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.luckandlogic.ui.screens.TelaCadastro
import com.example.luckandlogic.ui.screens.TelaLogin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var telaAtual by remember { mutableStateOf("cadastro") }

            when (telaAtual) {
                "cadastro" -> TelaCadastro(
                    aoCadastrar = { telaAtual = "login" },
                    aoLogar = { telaAtual = "login" } // 👈 troquei aqui!
                )

                "login" -> TelaLogin(
                    aoLogar = { /* depois vai pra tela principal */ },
                    irParaCadastro = { telaAtual = "cadastro" }
                )
            }
        }
    }
}
