package br.com.devmobileaj.pokeflow.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.devmobileaj.pokeflow.domain.pokemon.Pokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import br.com.devmobileaj.pokeflow.domain.repository.IPokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel(private val repository: IPokemonRepository): ViewModel() {
      private val _state = MutableLiveData<States>()
      val state: LiveData<States> = _state

      private var listaCompletaPokemons = mutableListOf<PokemonDetail>()
      private var busca = ""



      init {
          getPokemons()
      }

      fun getPokemons(){
            _state.value = States.Loader

            viewModelScope.launch {
                val pokemons =  repository.getPokemons()
                if(pokemons.isNotEmpty()){
                       listaCompletaPokemons = pokemons.toMutableList()
                       _state.value = States.OnSeucesso(pokemons)
                }else{
                       _state.value = States.OnError("Erro ao buscar pokemons")
                }
            }
      }

      fun pesquisarPokemon(textoPesquisa: String){
             busca = textoPesquisa
             filtarLista()
      }

      private fun filtarLista(){

            val pokemons = if(busca.isNullOrEmpty()){
                listaCompletaPokemons
            }else{
                listaCompletaPokemons.filter { it->
                       it.name.contains( busca, ignoreCase = true )
                }.toMutableList()
            }

            _state.value = States.OnSeucesso(pokemons)
      }


}

class ViewModelFactory(private val repository: IPokemonRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonViewModel(repository) as T
    }
}

open class States(){
    object Loader: States()
    class OnSeucesso(val pokemons: List<PokemonDetail>): States()
    class OnError(val mensage: String): States()

}