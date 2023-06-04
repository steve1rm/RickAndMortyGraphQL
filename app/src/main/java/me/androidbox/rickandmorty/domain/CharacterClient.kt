package me.androidbox.rickandmorty.domain

import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.model.CharactersModel

interface CharacterClient {
    suspend fun getCharacters(): List<CharactersModel>
    suspend fun getCharacter(id: String): CharacterModel?
}