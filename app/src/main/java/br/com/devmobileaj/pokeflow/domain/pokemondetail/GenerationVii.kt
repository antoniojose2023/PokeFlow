package br.com.devmobileaj.pokeflow.domain.pokemondetail

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenerationVii(
    val icons: IconsXXX,
    @SerializedName("lets-go-pikachu-lets-go-eevee")
    val lets_go_pikachu_lets_go_eevee: LetsGoPikachuLetsGoEevee,
    @SerializedName("ultra-sun-ultra-moon")
    val ultra_sun_ultra_moon: UltraSunUltraMoon
): Serializable