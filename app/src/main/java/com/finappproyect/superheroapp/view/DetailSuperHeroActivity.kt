package com.finappproyect.superheroapp.view

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.finappproyect.superheroapp.R
import com.finappproyect.superheroapp.api.ApiService
import com.finappproyect.superheroapp.databinding.ActivityDetailSuperHeroBinding
import com.finappproyect.superheroapp.model.DetailPowerStats
import com.finappproyect.superheroapp.model.SuperHeroDetail
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object {
        val EXTRA_ID = "extra_id"
    }

    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityDetailSuperHeroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = getRetrofit()

        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getDetail(id)


    }

    private fun getDetail(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val shDetail = retrofit
                .create(ApiService::class.java)
                .getSuperHeroID(id)
            if (shDetail.body() != null) {
                Log.i("puebacone", "Conexion exitosa")
                Log.i("puebacone", shDetail.body().toString())
                runOnUiThread {
                    createUI(shDetail.body()!!)
                }

            } else {
                Log.i("pruebaConexion", "Error en la obtencion de datos")
            }
        }
    }

    private fun createUI(superHeroDetail: SuperHeroDetail) {
        Picasso.get().load(superHeroDetail.image.url).into(binding.ivImageSuperhero)
        binding.tvName.text = superHeroDetail.name
        binding.tvnNameReal.text = superHeroDetail.biography.fullName
        binding.tvAlignement.text = superHeroDetail.biography.alignment

        updateStats(superHeroDetail.powerStats)
    }

    private fun updateStats(powerStats: DetailPowerStats) {

        setStats(binding.vInteligence, powerStats.intelligence)
        setStats(binding.vStrength, powerStats.strength)
        setStats(binding.vSpeed, powerStats.speed)
        setStats(binding.vDurability, powerStats.durability)
        setStats(binding.vPower, powerStats.power)
        setStats(binding.vCombat, powerStats.combat)

    }

    private fun setStats(view: View, stats: String){
        val params = view.layoutParams
        params.height = pxToDp(stats.toFloat())
        view.layoutParams = params

    }

    private fun pxToDp(px: Float): Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}