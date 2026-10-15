package br.com.devmobileaj.pokeflow.domain.pokemondetail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationViii(
    @SerializedName("brilliant-diamond-shining-pearl")
    val brilliant_diamond_shining_pearl: BrilliantDiamondShiningPearl,
    val icons: IconsXXX,
    @SerializedName("legends-arceus")
    val legends_arceus: LegendsArceus,
    @SerializedName("sword-shield")
    val sword_shield: SwordShield
): Serializable