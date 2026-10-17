package br.com.devmobileaj.pokeflow.data

import android.util.Log
import br.com.devmobileaj.pokeflow.data.remote.ApiPokemon
import br.com.devmobileaj.pokeflow.domain.paginapokemon.PaginaPokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import br.com.devmobileaj.pokeflow.domain.repository.IPokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlin.collections.emptyList

class PokemonRepositoryImpl(
    val apiPokemon: ApiPokemon,
): IPokemonRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): PaginaPokemon = withContext(Dispatchers.IO){

        try{
            val reponse = apiPokemon.getPokemons(offset, limit)
            if(reponse.isSuccessful) {
                val pokemonDTO = reponse.body()
                val pokemons = pokemonDTO?.results
                val listPokemonDetail = pokemons!!.map { it ->
                    async {
                        val id = it.url.trimEnd('/').split("/").last().toIntOrNull() ?: 0

                        if (id > 0) {
                            try{
                                getPokemonDetail(id)
                            }catch (ex: Exception){
                                Log.i("TAG", "getPokemons: ${ex.message}")
                                null
                            }
                        } else {
                            null
                        }
                    }
                }.awaitAll().filterNotNull()

                val paginaPokemon = PaginaPokemon(
                       listPokemonDetail,
                       pokemonDTO.next != null
                )

                return@withContext paginaPokemon

            }

        }catch (ex: Exception){
            Log.i("TAG", "getPokemons: ${ex.message}")
            return@withContext PaginaPokemon(emptyList(), true)

        }

        return@withContext PaginaPokemon(emptyList(), true)
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
             return apiPokemon.getPokemonDetail(id).body()!!
    }
}