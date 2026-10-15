package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class AnimatedX(
    val back_default: String,
    val back_shiny: String,
    val front_default: String,
    val front_shiny: String
): Serializable