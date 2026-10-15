package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Crystal(
    val animated: Animated,
    val back_default: String,
    val back_shiny: String,
    val back_shiny_transparent: String,
    val back_transparent: String,
    val front_default: String,
    val front_shiny: String,
    val front_shiny_transparent: String,
    val front_transparent: String
): Serializable