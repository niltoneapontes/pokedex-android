package br.com.niltoneapontes.pokedex_android.api.model

import br.com.niltoneapontes.pokedex_android.domain.PokemonType

data class PokemonsApiResult(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonApiResult(
    val id: Int,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: String,
    val type: PokemonType
)