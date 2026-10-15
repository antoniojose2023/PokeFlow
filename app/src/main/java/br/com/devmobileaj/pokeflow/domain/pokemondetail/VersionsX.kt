package br.com.devmobileaj.pokeflow.domain.pokemondetail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VersionsX(
    @SerializedName("generation-i")
    val generation_i: GenerationIX,
    @SerializedName("generation-ii")
    val generation_ii: GenerationIiX,
    @SerializedName("generation-iii")
    val generation_iii: GenerationIii,
    @SerializedName("generation-iv")
    val generation_iv: GenerationIv,
    @SerializedName("generation-ix")
    val generation_ix: GenerationIxX,
    @SerializedName("generation-v")
    val generation_v: GenerationV,
    @SerializedName("generation-vi")
    val generation_vi: GenerationVi,
    @SerializedName("generation-vii")
    val generation_vii: GenerationVii,
    @SerializedName("generation-viii")
    val generation_viii: GenerationViii
): Serializable