package me.androidbox.rickandmorty.domain.usecase.imp

import me.androidbox.rickandmorty.domain.CharacterClient
import me.androidbox.rickandmorty.domain.model.CharactersModel
import me.androidbox.rickandmorty.domain.usecase.GetCharactersUseCase
import javax.inject.Inject

class GetCharactersUseCaseImp @Inject constructor(
    private val characterClient: CharacterClient
) : GetCharactersUseCase {
    override suspend fun execute(): List<CharactersModel> {
        return characterClient
            .getCharacters()
            .sortedBy { characterModel ->
                characterModel.name
            }
    }
}