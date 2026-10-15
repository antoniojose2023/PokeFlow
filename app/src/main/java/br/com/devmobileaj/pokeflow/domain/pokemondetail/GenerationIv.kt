package br.com.devmobileaj.pokeflow.domain.pokemondetail

import java.io.Serializable

data class GenerationIv(
    val diamond_pearl: DiamondPearl,
    val heartgold_soulsilver: HeartgoldSoulsilver,
    val icons: Icons,
    val platinum: Platinum
): Serializable