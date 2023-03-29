package br.com.niltoneapontes.pokedex_android.api

import android.util.Log
import br.com.niltoneapontes.pokedex_android.api.model.PokemonApiResult
import br.com.niltoneapontes.pokedex_android.api.model.PokemonsApiResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    private val service: PokemonService
    init {
        val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(PokemonService::class.java)
    }

    fun getPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()

//        call?.enqueue(object: Callback<PokemonsApiResult> {
//            override fun onResponse(
//                call: Call<PokemonsApiResult>,
//                response: Response<PokemonsApiResult>
//            ) {
//                if(response.isSuccessful) {
//                    val body = response.body()
//
//                    body?.results?.let {
//                        Log.d("FIRST POKEMON IS: ", it[0].name)
//                    }
//                }
//
//
//                Log.d("POKEMON API ", "SUCCESS")
//            }
//
//            override fun onFailure(call: Call<PokemonsApiResult>, t: Throwable) {
//                Log.e("POKEMON API", "ERROR LOADING POKEMON")
//            }
//
//        })
    }

    fun getPokemonDetails(id: Int): PokemonApiResult? {
        val call = service.getPokemon(id)

        return call.execute().body()
    }

}