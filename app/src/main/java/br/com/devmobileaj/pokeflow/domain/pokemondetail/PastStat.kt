package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class PastStat(
    val generation: Generation,
    val stats: List<StatXX>
): Serializable