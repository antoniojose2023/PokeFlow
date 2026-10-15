package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Home(
    val front_default: String,
    val front_female: Any,
    val front_shiny: String,
    val front_shiny_female: Any
): Serializable