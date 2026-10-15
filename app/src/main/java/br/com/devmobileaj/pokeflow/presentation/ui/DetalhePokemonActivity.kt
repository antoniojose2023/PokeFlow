package br.com.devmobileaj.pokeflow.presentation.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobileaj.pokeflow.R
import br.com.devmobileaj.pokeflow.databinding.ActivityDetalhePokemonBinding
import br.com.devmobileaj.pokeflow.databinding.ItemStatsBinding
import br.com.devmobileaj.pokeflow.domain.pokemondetail.PokemonDetail
import coil3.Extras
import com.bumptech.glide.Glide

class DetalhePokemonActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityDetalhePokemonBinding.inflate(layoutInflater) }

    private lateinit var pokemonDetail: PokemonDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


         val bunle = intent.extras
         if(bunle != null){

             pokemonDetail = (if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                 bunle.getSerializable("pokemonDetail", PokemonDetail::class.java)
             }else{
                 bunle.getSerializable("pokemonDetail") as PokemonDetail
             })!!

             popularTelaDetalhe(pokemonDetail)

         }

         binding.ivVoltar.setOnClickListener {
              startActivity(Intent(this, ListagemPokemonActivity::class.java))
              overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
         }


    }

    private fun popularTelaDetalhe(pokemonDetail: PokemonDetail) {
            binding.tvNomePokemonDetalhe.text = pokemonDetail.name
            binding.tvCodigoPokemonDetalhe.text = "#${pokemonDetail.id}"
            binding.tvTipoPokemonDetalhe.text = pokemonDetail.types[0].type.name
            binding.tvAlturaPokemonDetalhe.text = "${pokemonDetail.height} \nAltura"
            binding.tvPesoPokemonDetalhe.text = "${pokemonDetail.weight} \nPeso"
            binding.tvExpoBsePokemonDetalhe.text = "${pokemonDetail.base_experience} \nExpo Base"

            val idPokemon = pokemonDetail.id
            val urlImagemPokemon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$idPokemon.png"

            Glide.with(this).load( urlImagemPokemon ).into(binding.ivPokemonDetalhe)

           val layoutInflaterView = LayoutInflater.from(this)
           binding.containerStats.removeAllViews()

           pokemonDetail.stats.forEach { pokemonDetail->
               val itemCardStat = ItemStatsBinding.inflate( layoutInflaterView, binding.containerStats, false )
               itemCardStat.tvStatsTitulo.text = pokemonDetail.stat.name
               itemCardStat.progressBarStat.progress = pokemonDetail.base_stat
               itemCardStat.tvBaseStat.text = pokemonDetail.base_stat.toString()

               binding.containerStats.addView(itemCardStat.root)
           }



    }
}