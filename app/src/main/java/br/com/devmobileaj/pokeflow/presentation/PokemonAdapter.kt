package br.com.devmobileaj.pokeflow.presentation

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import br.com.devmobileaj.pokeflow.R
import br.com.devmobileaj.pokeflow.databinding.ItemPokemonBinding
import br.com.devmobileaj.pokeflow.domain.pokemon.Pokemon
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import coil3.load
import com.bumptech.glide.Glide

class PokemonAdapter(var onClick: (PokemonDetail) -> Unit={}): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemons: List<PokemonDetail> = emptyList()

    fun addLista(lista: List<PokemonDetail>){
          pokemons = lista
          notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonViewHolder {
           val layoutInflater = LayoutInflater.from(parent.context)
           val binding = ItemPokemonBinding.inflate(layoutInflater, parent, false)
           return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PokemonViewHolder,
        position: Int
    ) {
           val pokemon = pokemons[position]
           holder.bind( pokemon )
    }

    override fun getItemCount() = pokemons.size

    inner class PokemonViewHolder(val binding: ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonDetail){
               binding.tvNomePokemon.text = pokemon.name
               binding.tvTypePokemon.text = pokemon.types[0].type.name

               val cores = listOf(R.color.amarelo, R.color.azul_claro, R.color.laranja, R.color.verde)
               val corAleatoria = cores.random()
               binding.tvTypePokemon.backgroundTintList = (ColorStateList.valueOf(getColor(binding.root.context, corAleatoria)))


               binding.tvCodigoPokemon.text = "#"+pokemon.id.toString()


               val idPokemon = pokemon.id
               val urlImagemPokemon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$idPokemon.png"


               Glide.with(binding.root.context).load(  urlImagemPokemon ).into( binding.ivPokemon )

              itemView.setOnClickListener{
                   onClick(pokemon)
              }
        }

    }

}