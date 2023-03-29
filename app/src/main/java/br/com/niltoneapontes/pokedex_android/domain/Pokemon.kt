package br.com.niltoneapontes.pokedex_android.domain

data class Pokemon(
    val url: String,
    val number: Int,
    val name: String,
    val types: List<PokemonType>
) {

    val formattedNumber = number.toString().padStart(3, '0')
}

