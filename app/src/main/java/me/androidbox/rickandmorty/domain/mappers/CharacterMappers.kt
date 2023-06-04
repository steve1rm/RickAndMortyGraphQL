package me.androidbox.rickandmorty.domain.mappers

import me.androidbox.rickandmorty.CharacterQuery
import me.androidbox.rickandmorty.CharactersQuery
import me.androidbox.rickandmorty.domain.model.CharacterModel
import me.androidbox.rickandmorty.domain.model.CharactersModel

fun CharactersQuery.Result.toCharactersModel(): CharactersModel {
    return CharactersModel(
        id = this.id.orEmpty(),
        image = this.image.orEmpty(),
        name = this.name.orEmpty(),
        species = this.species.orEmpty()
    )
}

fun CharacterQuery.Character.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = this.id.orEmpty(),
        name = this.name.orEmpty(),
        image = this.image.orEmpty(),
        species = this.species.orEmpty(),
        status = this.status.orEmpty(),
        gender = this.gender.orEmpty(),
        location = this.location,
        episode = this.episode.mapNotNull { it?.name.orEmpty()} )
}

