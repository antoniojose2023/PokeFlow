package br.com.devmobileaj.pokeflow.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.devmobileaj.pokeflow.R
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DetalhePokemonActivityTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(DetalhePokemonActivity::class.java)

    @Test
    fun verificar_tela_detalhe(){

        onView(withId(R.id.ivVoltar)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTituloTopoTela)).check(matches(isDisplayed()))
        onView(withId(R.id.ivPokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.cardItemTopoDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvNomePokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCodigoPokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTipoPokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvInfo)).check(matches(isDisplayed()))
        onView(withId(R.id.cardItemInformacoesDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAlturaPokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvPesoPokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvExpoBsePokemonDetalhe)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTituloStats)).check(matches(isDisplayed()))

        onView(withId(R.id.containerStats)).check(matches(not(isDisplayed())))

        onView(withId(R.id.ivVoltar)).perform(click())

    }

}