package br.com.devmobileaj.pokeflow.data.remote

import br.com.devmobileaj.pokeflow.domain.pokemon.PokemonDTO
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPokemon {

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<PokemonDTO>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): Response<PokemonDetail>

}