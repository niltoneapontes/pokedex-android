package br.com.niltoneapontes.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.pokedex_android.R
import br.com.niltoneapontes.pokedex_android.api.PokemonRepository
import br.com.niltoneapontes.pokedex_android.domain.Pokemon
import br.com.niltoneapontes.pokedex_android.domain.PokemonType
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var loading = true
        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        val loaderView = findViewById<ProgressBar>(R.id.progressBar)

        Thread(Runnable {
            loadPokemons(recyclerView, loaderView)
        }).start()
    }
    private fun loadPokemons(
        recyclerView: RecyclerView,
        loaderView: ProgressBar
    ) {
        val pokemonsApiResult = PokemonRepository.getPokemons(50)
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

    fun goToDetails(view: View) {
        val pokemonNumber = this.findViewById<TextView>(R.id.tvNumber).text.toString().replace("No ", "").toInt()

        val pokemonDetailsView = R.layout.pokemon_details

        setContentView(pokemonDetailsView)
    }

    fun goBack(view: View) {
        setContentView(R.layout.activity_main)
    }
}