package me.androidbox.rickandmorty.data

import com.apollographql.apollo3.ApolloClient
import me.androidbox.rickandmorty.CharacterQuery
import me.androidbox.rickandmorty.CharactersQuery
import me.androidbox.rickandmorty.domain.CharacterClient
import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.model.CharactersModel
import me.androidbox.rickandmorty.domain.mappers.toCharacterModel
import me.androidbox.rickandmorty.domain.mappers.toCharactersModel

class ApolloCharacterClientImp(
    private val apolloClient: ApolloClient
): CharacterClient {

    override suspend fun getCharacters(): List<CharactersModel> {
        return apolloClient
            .query(CharactersQuery())
            .execute()
            .data?.characters?.results?.mapNotNull { result ->
               result?.toCharactersModel()
            } ?: emptyList()
    }

    override suspend fun getCharacter(id: String): CharacterModel? {
        return apolloClient
            .query(CharacterQuery(id))
            .execute()
            .data?.character?.toCharacterModel()
    }
}