package com.example.retrofitexample.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {

    /**
     * https://rickandmortyapi.com/api/character/?page=1
     * The retrofit builder will need a base url so we extract that from
     * our link and create the base url variable of type string
     */

        private val BASE_URL="https://rickandmortyapi.com/api/"

    /**
     * next we create a variable for the moshi builder,
     * add a converter to it
     */

    private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /**
     * then we create an instance of retrofit by lazy so it can initialized only when it is
     * needed pass the url and the moshi variable created above
     */
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    val apiService:ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

// An interface called ApiService
interface  ApiService{
    @GET("character")
    fun fetchCharacters(@Query("page") page:String):Call<CharacterResponse>
}