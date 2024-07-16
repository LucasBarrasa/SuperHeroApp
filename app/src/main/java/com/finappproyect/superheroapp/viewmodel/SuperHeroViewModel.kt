package com.finappproyect.superheroapp.viewmodel

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.finappproyect.superheroapp.databinding.ItemSuperherolistBinding
import com.finappproyect.superheroapp.model.SuperHeroItemDataResponse
import com.squareup.picasso.Picasso

class SuperHeroViewModel(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperherolistBinding.bind(view)

    fun bind(superHeroItem: SuperHeroItemDataResponse, onItemSelected: (String) -> Unit) {
        binding.tvSuperHeroName.text = superHeroItem.superHeroName
        binding.tvSuperHeroPublisher.text = superHeroItem.superHeroBiography.publisher

        Picasso.get()
            .load(superHeroItem.superHeroImage.superHeroImageUrl)
            .into(binding.ivSuperHeroImage);

        binding.root.setOnClickListener { onItemSelected(superHeroItem.superHeroId) }
    }
}