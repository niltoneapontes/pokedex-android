package br.com.niltoneapontes.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.pokedex_android.R
import br.com.niltoneapontes.pokedex_android.domain.Pokemon
import br.com.niltoneapontes.pokedex_android.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemons = listOf(
            Pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", 1, "Charmander", listOf(PokemonType("fire"))),
            Pokemon("https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", 2, "Charizard", listOf(PokemonType("fire")))
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}