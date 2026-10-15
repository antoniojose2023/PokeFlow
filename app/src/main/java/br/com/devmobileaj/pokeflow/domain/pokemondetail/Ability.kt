package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
): Serializable