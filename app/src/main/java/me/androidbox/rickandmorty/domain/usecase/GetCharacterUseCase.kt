package me.androidbox.rickandmorty.domain.usecase

import me.androidbox.rickandmorty.domain.model.CharacterModel

fun interface GetCharacterUseCase {
    suspend fun execute(id: String): CharacterModel?
}