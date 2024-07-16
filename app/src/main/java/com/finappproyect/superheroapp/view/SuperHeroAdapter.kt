package com.finappproyect.superheroapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finappproyect.superheroapp.R
import com.finappproyect.superheroapp.model.SuperHeroItemDataResponse
import com.finappproyect.superheroapp.viewmodel.SuperHeroViewModel

class SuperHeroAdapter(
    var superHeroList: List<SuperHeroItemDataResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewModel>() {

    fun updateList(list: List<SuperHeroItemDataResponse>) {
        this.superHeroList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewModel {
        return SuperHeroViewModel(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_superherolist, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewModel, position: Int) {
        viewHolder.bind(superHeroList[position], onItemSelected)
    }

    override fun getItemCount() = superHeroList.size
}