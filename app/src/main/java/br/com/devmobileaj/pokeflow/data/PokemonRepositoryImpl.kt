package br.com.devmobileaj.pokeflow.data

import android.util.Log
import br.com.devmobileaj.pokeflow.data.remote.ApiPokemon
import br.com.devmobileaj.pokeflow.domain.pokemon.Pokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import br.com.devmobileaj.pokeflow.domain.repository.IPokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import org.jetbrains.annotations.Async

class PokemonRepositoryImpl(
    private val apiPokemon: ApiPokemon,
): IPokemonRepository {
    override suspend fun getPokemons(): List<PokemonDetail> = withContext(Dispatchers.IO){

        try{
            val reponse = apiPokemon.getPokemons()
            if(reponse.isSuccessful) {
                val pokemonDTO = reponse.body()
                val pokemons = pokemonDTO?.results
                val listPokemonDetail = pokemons!!.map { it ->
                    async {
                        val id = it.url.trimEnd('/').split("/").last().toIntOrNull() ?: 0

                        if (id > 0) {
                            getPokemonDetail(id)
                        } else {
                            null
                        }
                    }
                }

                return@withContext listPokemonDetail.awaitAll().filterNotNull()

            }

        }catch (ex: Exception){
            Log.i("TAG", "getPokemons: ${ex.message}")
            return@withContext emptyList()

        }

        return@withContext emptyList()
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
             return apiPokemon.getPokemonDetail(id).body()!!
    }
}