package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Cries(
    val latest: String,
    val legacy: String
): Serializable