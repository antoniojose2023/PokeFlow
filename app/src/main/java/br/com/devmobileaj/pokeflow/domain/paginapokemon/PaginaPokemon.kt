package br.com.devmobileaj.pokeflow.domain.paginapokemon

import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import kotlinx.coroutines.Deferred

data class PaginaPokemon(
    val pokemons: List<PokemonDetail?>,
    val nextPage: Boolean? = null,
)