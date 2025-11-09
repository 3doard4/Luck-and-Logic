package com.example.luckandlogic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.luckandlogic.data.AppDatabase
import com.example.luckandlogic.data.repository.GameResultRepository
import com.example.luckandlogic.ui.screens.*
import com.example.luckandlogic.viewmodel.GameResultViewModel
import com.example.luckandlogic.viewmodel.GameResultViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o banco Room (se você não estiver usando ainda, isso não quebra; apenas comente)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "luckandlogic_db"
        ).build()

        // Cria repository + viewModel (se você já tiver GameResultDao / Repository)
        val repository = GameResultRepository(db.gameResultDao())
        val viewModelFactory = GameResultViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[GameResultViewModel::class.java]

        setContent {
            // estado de navegação simples
            var telaAtual by remember { mutableStateOf("login") }

            when (telaAtual) {
                "login" -> TelaLogin(
                    aoLogar = { telaAtual = "menu" },
                    irParaCadastro = { telaAtual = "cadastro" }
                )

                "cadastro" -> TelaCadastro(
                    aoCadastrar = { telaAtual = "menu" },
                    aoLogar = { telaAtual = "login" }
                )

                "menu" -> TelaMenu(
                    aoIrParaBlackjack = { telaAtual = "blackjack" },
                    aoIrParaRoleta = { telaAtual = "roleta" },
                    aoIrParaDados = { telaAtual = "dados" },
                    aoIrParaHistorico = { telaAtual = "historico" },
                    aoVoltar = { telaAtual = "login" }
                )

                "blackjack" -> TelaBlackjack(
                    aoVoltarMenu = { telaAtual = "menu" },
                    viewModel = viewModel
                )

                "roleta" -> TelaRoleta(
                    aoVoltarMenu = { telaAtual = "menu" },
                    viewModel = viewModel
                )

                "dados" -> TelaJogoDosDados(
                    aoVoltar = { telaAtual = "menu" },
                    viewModel = viewModel
                )

                "historico" -> TelaHistorico(
                    aoVoltar = { telaAtual = "menu" },
                    viewModel = viewModel
                )

                else -> {
                    // Fallback: volta para login
                    TelaLogin(
                        aoLogar = { telaAtual = "menu" },
                        irParaCadastro = { telaAtual = "cadastro" }
                    )
                }
            }
        }
    }
}