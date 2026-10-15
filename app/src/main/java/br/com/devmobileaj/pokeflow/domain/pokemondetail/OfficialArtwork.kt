package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String,
    val versions: Versions
): Serializable