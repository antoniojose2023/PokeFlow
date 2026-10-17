package br.com.devmobileaj.pokeflow.presentation.ui

import android.provider.Telephony
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.devmobileaj.pokeflow.R

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashScreenActivityTest {

    @get:Rule
    val scenarioRule = ActivityScenarioRule(SplashScreenActivity::class.java)


    @Before
    fun setup(){

        Intents.init()
    }

    @Test
    fun splash_abre_tela_listagem(){

        onView(withId(R.id.ivLogoSplash)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSubTituloSplash)).check(matches(isDisplayed()))
        onView(withId(R.id.tvSubTituloSplash)).check(matches(isDisplayed()))
        onView(withId(R.id.divider)).check(matches(isDisplayed()))
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        Thread.sleep(2500)
        Intents.intended(hasComponent(ListagemPokemonActivity::class.java.name))

    }


    @After
    fun tearDown(){
        Intents.release()
    }

}