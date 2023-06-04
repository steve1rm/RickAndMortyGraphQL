package me.androidbox.rickandmorty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.androidbox.rickandmorty.domain.usecase.GetCharacterUseCase
import me.androidbox.rickandmorty.domain.usecase.GetCharactersUseCase
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : ViewModel() {

    private val _rickAndMortyState = MutableStateFlow(RickyAndMortyState())
    val rickyAndMortyState = _rickAndMortyState.asStateFlow()

    init {
        viewModelScope.launch {
            _rickAndMortyState.update { rickyAndMortyState ->
                rickyAndMortyState.copy(
                    isLoading = true
                )
            }

            _rickAndMortyState.update { rickyAndMortyState ->
                rickyAndMortyState.copy(
                    listOfCharacter = getCharactersUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun dismissDialog() {
        _rickAndMortyState.update { rickyAndMortyState ->
            rickyAndMortyState.copy(
                character = null
            )
        }
    }

    fun selectCharacter(id: String) {
        viewModelScope.launch {
            _rickAndMortyState.update { rickyAndMortyState ->
                rickyAndMortyState.copy(
                    character = getCharacterUseCase.execute(id))
            }
        }
    }
}