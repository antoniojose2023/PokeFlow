package br.com.devmobileaj.pokeflow.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.devmobileaj.pokeflow.R
import br.com.devmobileaj.pokeflow.presentation.PokemonAdapter
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class ListagemPokemonActivityTest {

    @get:Rule
    val scanerioRule = ActivityScenarioRule(ListagemPokemonActivity::class.java )

    @Test
    fun verificar_tela_listagem(){

        onView(withId(R.id.textView3)).check(matches(isDisplayed()))
        onView(withId(R.id.editPesquisa)).check(matches(isDisplayed()))
        onView(withId(R.id.rvPokemons)).check(matches(isDisplayed()))

        //espera para carregar a lista
        Thread.sleep(2500)

        onView(withId(R.id.rvPokemons)).perform(
            actionOnItemAtPosition<PokemonAdapter.PokemonViewHolder>(0, click())
        )

    }

}