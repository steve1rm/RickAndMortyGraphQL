package me.androidbox.rickandmorty.domain.usecase.imp

import me.androidbox.rickandmorty.domain.CharacterClient
import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.usecase.GetCharacterUseCase
import javax.inject.Inject

class GetCharacterUseCaseImp @Inject constructor(
    private val charactersClient: CharacterClient
) : GetCharacterUseCase {

    override suspend fun execute(id: String): CharacterModel? {
        return charactersClient.getCharacter(id)
    }
}