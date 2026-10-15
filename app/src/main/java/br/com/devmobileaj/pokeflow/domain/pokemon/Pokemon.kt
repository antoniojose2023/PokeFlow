package br.com.devmobileaj.pokeflow.domain.pokemon

import java.io.Serializable


data class Pokemon(
    val name: String,
    val url: String
): Serializable