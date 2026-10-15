package br.com.devmobileaj.pokeflow.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.canScrollVertically
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.devmobileaj.pokeflow.R
import br.com.devmobileaj.pokeflow.data.PokemonRepositoryImpl
import br.com.devmobileaj.pokeflow.data.remote.RetrofitHelper
import br.com.devmobileaj.pokeflow.databinding.ActivityListagemPokemonBinding
import br.com.devmobileaj.pokeflow.presentation.PokemonAdapter
import br.com.devmobileaj.pokeflow.presentation.viewmodel.PokemonViewModel
import br.com.devmobileaj.pokeflow.presentation.viewmodel.States
import br.com.devmobileaj.pokeflow.presentation.viewmodel.ViewModelFactory
import kotlin.collections.get
import kotlin.getValue

class ListagemPokemonActivity : AppCompatActivity() {

    private val binding by lazy{ ActivityListagemPokemonBinding.inflate(layoutInflater) }

    private val apiPokemon = RetrofitHelper().apiPokemon
    private val repositoryImpl = PokemonRepositoryImpl(apiPokemon)
    private val viewModelPokemon by viewModels<PokemonViewModel> {
          ViewModelFactory(repositoryImpl)
    }

    private val pokemonAdapter by lazy { PokemonAdapter() }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        configurarRecyclerView()

        viewModelPokemon.state.observe(this){ state ->

                    when(state){
                        is States.Loader -> {
                              // binding.progressBarPokemon.visibility = View.VISIBLE
                        }
                        is States.OnSeucesso -> {
                            val pokemons = state.pokemons
                            pokemonAdapter.addLista( pokemons )
                           // binding.progressBarPokemon.visibility = View.GONE
                        }
                        is States.OnError -> {
                            Log.i("TAG", "error: ${state.mensage}")
                           // binding.progressBarPokemon.visibility = View.GONE
                        }
                    }
        }


        pokemonAdapter.onClick = { pokemonDetail ->
              val intent = Intent(this, DetalhePokemonActivity::class.java)
              intent.putExtra("pokemonDetail", pokemonDetail)
              startActivity( intent )
              overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        binding.editPesquisa.setOnEditorActionListener { view, i, event ->
              val textoPesquisa = view.text.toString()
              viewModelPokemon.pesquisarPokemon( textoPesquisa )
              true
        }


//        binding.rvPokemons.setOnScrollChangeListener { _, _, _, _, _ ->
//              val proximo = canScrollVertically(binding.rvPokemons, 1)
//              if(!proximo)
//                   // viewModelPokemon.getPokemons()
//        }

    }


    fun configurarRecyclerView(){
          binding.rvPokemons.layoutManager = LinearLayoutManager(this)
          binding.rvPokemons.adapter = pokemonAdapter

    }
}