package br.com.niltoneapontes.pokedex_android.view

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.niltoneapontes.pokedex_android.R
import br.com.niltoneapontes.pokedex_android.domain.Pokemon
import com.bumptech.glide.Glide

class PokemonAdapter(private val items: List<Pokemon?>): RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon?) = with(itemView) {
            val ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
            val tvNumber = findViewById<TextView>(R.id.tvNumber)
            val tvName = findViewById<TextView>(R.id.tvName)
            val tvType1 = findViewById<TextView>(R.id.tvType1)
            val tvType2 = findViewById<TextView>(R.id.tvType2)


            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(ivPokemon)
                tvNumber.text = "No ${it.formattedNumber}"
                tvName.text = it.name
                tvType1.text = it.types[0].name
                if(it.types.size > 1) {
                    tvType2.visibility = View.VISIBLE
                    tvType2.text = it.types[1].name
                } else {
                    tvType2.visibility = View.GONE
                }
            }

        }

    }


}