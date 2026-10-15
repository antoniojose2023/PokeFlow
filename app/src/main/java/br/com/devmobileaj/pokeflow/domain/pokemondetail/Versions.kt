package br.com.devmobileaj.pokeflow.domain.pokemondetail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Versions(
    @SerializedName("generation-i")
    val generation_i: GenerationI,
    @SerializedName("generation-ii")
    val generation_ii: GenerationIi
): Serializable