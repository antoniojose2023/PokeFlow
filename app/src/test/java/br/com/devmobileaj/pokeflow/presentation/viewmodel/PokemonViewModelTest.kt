package br.com.devmobileaj.pokeflow.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.devmobileaj.pokeflow.domain.paginapokemon.PaginaPokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Cries
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Species
import br.com.devmobileaj.pokeflow.domain.pokemondetail.Sprites
import br.com.devmobileaj.pokeflow.domain.repository.IPokemonRepository
import br.com.devmobileaj.pokeflow.util.MainDispatcherRule
import br.com.devmobileaj.pokeflow.util.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PokemonViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var repository : IPokemonRepository
    lateinit var pokemonViewModel : PokemonViewModel


    @Test
    fun proximaPagina_retornaProximaPagina_quandoSucesso() = runTest {
        val pokemonDetail = getPokemonDetailMock()[0]

        Mockito.`when`(
            repository.getPokemons(0, 20)
        ).thenReturn(
                    PaginaPokemon(
                    listOf(pokemonDetail),
                    true
                )

        )


        pokemonViewModel = PokemonViewModel(repository)

        val state = pokemonViewModel.state.getOrAwaitValue()

        val sucesso = state as States.OnSeucesso
        assertThat(sucesso.pokemons).isNotEmpty()
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