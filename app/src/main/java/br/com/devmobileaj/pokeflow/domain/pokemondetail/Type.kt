package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Type(
    val slot: Int,
    val type: TypeX
): Serializable