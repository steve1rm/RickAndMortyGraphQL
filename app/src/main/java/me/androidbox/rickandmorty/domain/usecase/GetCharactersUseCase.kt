package me.androidbox.rickandmorty.domain.usecase

import me.androidbox.rickandmorty.domain.model.CharactersModel

fun interface GetCharactersUseCase {
    suspend fun execute(): List<CharactersModel>
}