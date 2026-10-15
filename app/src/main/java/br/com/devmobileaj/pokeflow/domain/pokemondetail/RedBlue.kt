package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class RedBlue(
    val back_default: String,
    val back_gray: String,
    val back_transparent: String,
    val back_transparent_gray: String,
    val front_default: String,
    val front_gray: String,
    val front_transparent: String,
    val front_transparent_gray: String
): Serializable