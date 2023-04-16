package by.heroes.heroesofdisney.repository

import by.heroes.heroesofdisney.model.HeroesData
import by.heroes.heroesofdisney.model.ItemHero
import by.heroes.heroesofdisney.network.HeroesApi
import retrofit2.Response
import javax.inject.Inject

class HeroesRepository @Inject constructor(
    private val api:HeroesApi
) {
    suspend fun getHeroes(page:Int) : Response<HeroesData> = api.getHeroes(page)
    suspend fun getHero(id: Int): Response<ItemHero> = api.getHero(id)
}