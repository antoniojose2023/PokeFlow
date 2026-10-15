package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Animated(
    val front_default: String,
    val front_shiny: String
): Serializable