package com.example.luckandlogic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luckandlogic.data.repository.GameResultRepository
import com.example.luckandlogic.model.GameResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameResultViewModel(private val repository: GameResultRepository) : ViewModel() {

    private val _results = MutableStateFlow<List<GameResult>>(emptyList())
    val results: StateFlow<List<GameResult>> = _results

    fun insertResult(result: GameResult) {
        viewModelScope.launch {
            repository.insertResult(result)
            loadResults()
        }
    }

    fun loadResults() {
        viewModelScope.launch {
            _results.value = repository.getAllResults()
        }
    }

    fun clearResults() {
        viewModelScope.launch {
            repository.deleteAllResults()
            _results.value = emptyList()
        }
    }

    // 👇 Função para salvar resultado de qualquer jogo
    fun salvarResultado(nomeJogo: String, resultado: String) {
        val novoResultado = GameResult(
            gameName = nomeJogo,
            result = resultado,
            points = 0, // se seu GameResult exige points; se não exigir, remova essa linha
            date = System.currentTimeMillis() // usa 'date' porque seu código já usou esse nome antes
        )
        insertResult(novoResultado)
    }
}