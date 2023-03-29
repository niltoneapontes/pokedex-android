package br.com.niltoneapontes.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.pokedex_android.R
import br.com.niltoneapontes.pokedex_android.api.PokemonRepository
import br.com.niltoneapontes.pokedex_android.domain.Pokemon
import br.com.niltoneapontes.pokedex_android.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

        Thread(Runnable {
            loadPokemons(recyclerView)
        }).start()


    }

    private fun loadPokemons(
        recyclerView: RecyclerView,
    ) {
        val pokemonsApiResult = PokemonRepository.getPokemons(151)
        pokemonsApiResult?.results?.let {
            val layoutManager = LinearLayoutManager(this)

            val pokemons: List<Pokemon?> = it.map { pokemonResult ->
                val number = pokemonResult.url.replace("https://pokeapi.co/api/v2/pokemon/", "").replace("/", "").toInt()
                Log.d("NUMBER", number.toString())
                val pokemon = PokemonRepository.getPokemonDetails(number)
                pokemon?.let {
                    Pokemon(
                        pokemon.id,
                        pokemon.name,
                        pokemon.types.map { type ->
                            type.type
                        }
                    )
                }

            }
            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemons)
            }
        }
    }
}