package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class GameIndice(
    val game_index: Int,
    val version: Version
): Serializable