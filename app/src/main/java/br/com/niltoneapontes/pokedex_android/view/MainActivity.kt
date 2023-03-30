package br.com.niltoneapontes.pokedex_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
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

    fun goToDetails(view: View) {
        val pokemonNumber = this.findViewById<TextView>(R.id.tvNumber).text.toString().replace("No ", "").toInt()
        Log.d("ID: ", pokemonNumber.toString())
        val pokemonDetailsView = R.layout.pokemon_details
        val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        val tvNumber = findViewById<TextView>(R.id.tvNumber)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvType1 = findViewById<TextView>(R.id.tvType1)
        val tvType2 = findViewById<TextView>(R.id.tvType2)

        Glide.with(pokemonDetailsView).load(it.imageUrl).into(ivPokemon)
        tvNumber.text = "No ${it.formattedNumber}"
        tvName.text = it.name
        tvType1.text = it.types[0].name
        if(it.types.size > 1) {
            tvType2.visibility = View.VISIBLE
            tvType2.text = it.types[1].name
        } else {
            tvType2.visibility = View.GONE
        }

        setContentView(pokemonDetailsView)

    }

    fun goBack(view: View) {
        setContentView(R.layout.activity_main)
    }
}