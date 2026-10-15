package br.com.devmobileaj.pokeflow.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.devmobileaj.pokeflow.R
import br.com.devmobileaj.pokeflow.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private val binding by lazy{ ActivitySplashScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        carregarTelaListagem()

    }
    private fun carregarTelaListagem(){
        Handler(Looper.getMainLooper()).postDelayed({
              startActivity(Intent( this, ListagemPokemonActivity::class.java))
              overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        },2000)
    }
}