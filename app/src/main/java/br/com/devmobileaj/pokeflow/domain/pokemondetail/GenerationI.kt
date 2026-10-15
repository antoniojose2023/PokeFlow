package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class GenerationI(
    val red_and_blue: RedAndBlue,
    val red_and_green: RedAndGreen
): Serializable