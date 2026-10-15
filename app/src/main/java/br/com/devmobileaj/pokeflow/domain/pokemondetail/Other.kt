package br.com.devmobileaj.pokeflow.domain.pokemondetail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtwork,
    val showdown: Showdown
): Serializable