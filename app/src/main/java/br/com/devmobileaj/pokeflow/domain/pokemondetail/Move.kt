package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
): Serializable