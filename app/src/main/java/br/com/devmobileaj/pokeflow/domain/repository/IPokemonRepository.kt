package br.com.devmobileaj.pokeflow.domain.repository

import br.com.devmobileaj.pokeflow.domain.pokemon.Pokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail

interface IPokemonRepository {
    suspend fun getPokemons(): List<PokemonDetail>
    suspend fun getPokemonDetail(id: Int): PokemonDetail

}