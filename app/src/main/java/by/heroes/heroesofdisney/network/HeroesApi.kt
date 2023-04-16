package by.heroes.heroesofdisney.network

import by.heroes.heroesofdisney.model.HeroesData
import by.heroes.heroesofdisney.model.ItemHero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HeroesApi {

    @GET("/characters")
    suspend fun getHeroes(@Query("page") page: Int): Response<HeroesData>

    @GET("/characters/{id}")
    suspend fun getHero(@Path("id") id: Int): Response<ItemHero>
}