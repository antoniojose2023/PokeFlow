package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class PastAbility(
    val abilities: List<AbilityXX>,
    val generation: Generation
): Serializable