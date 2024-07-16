package com.finappproyect.superheroapp.view

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.finappproyect.superheroapp.api.ApiService
import com.finappproyect.superheroapp.databinding.ActivityMainBinding
import com.finappproyect.superheroapp.view.DetailSuperHeroActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()
        initListener()

    }

    private fun initUI() {

        binding.svBusqueda.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        adapter = SuperHeroAdapter{navigateToDetail(it)}
        binding.rvSuperHeroList.setHasFixedSize(true)
        binding.rvSuperHeroList.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroList.adapter = adapter
    }

    private fun initListener() {

    }

    private fun searchByName(query: String) {
        binding.pbCargando.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheros(query)
            if (myResponse.isSuccessful) {
                Log.i("puebacone", "My response con exito")
                val response = myResponse.body()
                if (response != null) {
                    Log.i("puebacone", "cuerpo de la respuesta obtenido con exito")
                    runOnUiThread {
                        binding.pbCargando.isVisible = false
                        adapter.updateList(response.results)
                    }
                } else {
                    Log.i("puebacone", "Error al obtener el cuerpo de la respuesta")
                }
            } else {
                Log.i("puebacone", "Error al obtener my response")
            }
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id: String){
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)

    }

}


