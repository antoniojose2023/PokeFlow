package br.com.devmobileaj.pokeflow.data

import br.com.devmobileaj.pokeflow.data.remote.ApiPokemon
import br.com.devmobileaj.pokeflow.data.remote.RetrofitHelper
import br.com.devmobileaj.pokeflow.domain.paginapokemon.PaginaPokemon
import br.com.devmobileaj.pokeflow.domain.pokemon.Pokemon
import br.com.devmobileaj.pokeflow.domain.pokemon.PokemonDTO
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Cries
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Species
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Sprites
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import net.bytebuddy.agent.VirtualMachine
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PokemonRepositoryImplTest {
    @Mock
    private lateinit var apiPokemon: ApiPokemon
    private lateinit var pokemonRepositoryImpl : PokemonRepositoryImpl

    @Before
    fun setUp() {
          MockitoAnnotations.openMocks(this)
          pokemonRepositoryImpl = PokemonRepositoryImpl(apiPokemon)

    }

    @Test
    fun getPokemons() = runTest {

        val pokemonDTO = PokemonDTO(
            count = 100,
            next = "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20",
            previous = "",
            results = listOf(
                Pokemon(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
                //Pokemon(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
            ))

        val pokemonDetail = getPokemonDetailMock()[0]

        Mockito.
            `when`(apiPokemon.getPokemons(0, 20))
            .thenReturn(
                Response.success(
                    pokemonDTO
                )
             )

        Mockito.
        `when`(apiPokemon.getPokemonDetail(1))
            .thenReturn(
                Response.success(
                    pokemonDetail
                )
            )



       val paginaPokemon =  pokemonRepositoryImpl.getPokemons(0,20)
       val pokemons = paginaPokemon.pokemons
       val nextPage = paginaPokemon.nextPage
       assertThat(pokemons).isNotEmpty()
       assertThat(nextPage).isTrue()
    }

    @Test
    fun getPokemonDetail() = runTest {

        val pokemonDetail = getPokemonDetailMock()[0]

        Mockito
            .`when`(apiPokemon.getPokemonDetail(1))
            .thenReturn(
                Response.success( pokemonDetail )
            )

        val detail = pokemonRepositoryImpl.getPokemonDetail(1)
        assertThat( detail.name ).isEqualTo("bulbasaur")

    }


    fun getPokemonDetailMock() = listOf(

    PokemonDetail(
    abilities = emptyList(),
    base_experience = 64,
    cries = Cries(latest = "latest.ogg", legacy = "legacy.ogg"),
    forms = emptyList(),
    game_indices = emptyList(),
    height = 7,
    held_items = emptyList(),
    id = 1,
    is_default = true,
    location_area_encounters = "",
    moves = emptyList(),
    name = "bulbasaur",
    order = 1,
    past_abilities = emptyList(),
    past_stats = emptyList(),
    past_types = emptyList(),
    species = Species(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon-species/1/"),
    sprites = Mockito.mock(Sprites::class.java),
    stats = emptyList(),
    types = emptyList(),
    weight = 69
    )

  )

}