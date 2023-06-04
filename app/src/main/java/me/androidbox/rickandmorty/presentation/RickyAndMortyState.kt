package me.androidbox.rickandmorty.presentation

import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.model.CharactersModel

data class RickyAndMortyState(
    val listOfCharacter: List<CharactersModel> = emptyList(),
    val isLoading: Boolean = false,
    val character: CharacterModel? = null
)
