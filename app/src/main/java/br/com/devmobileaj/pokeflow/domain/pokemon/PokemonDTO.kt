package br.com.devmobileaj.pokeflow.domain.pokemon

import java.io.Serializable

data class PokemonDTO(
    val count: Int,
    val next: String? = null,
    val previous: Any? = null,
    val results: List<Pokemon>
): Serializable