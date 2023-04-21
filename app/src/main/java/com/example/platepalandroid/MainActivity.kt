package com.example.platepalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import api.RecipeApi
import model.Recipe
import model.RecipeResponse
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = RetrofitClient.instance!!.create(RecipeApi::class.java)
        val call = apiService.getRecipes("omelette")

        call!!.enqueue(object : Callback<RecipeResponse?> {
            override fun onResponse(call: Call<RecipeResponse?>, response: Response<RecipeResponse?>) {
                if (response.isSuccessful) {
                    val recipes: List<Recipe>? = response.body()?.results
                    // Do something with the list of recipes
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecipeResponse?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
