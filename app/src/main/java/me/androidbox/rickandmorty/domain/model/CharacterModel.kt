package me.androidbox.rickandmorty.domain.model

import me.androidbox.rickandmorty.CharacterQuery

data class CharacterModel(
    val id: String,
    val image: String,
    val species: String,
    val status: String,
    val location: CharacterQuery.Location?,
    val episode: List<String>
)
