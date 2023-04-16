package by.heroes.heroesofdisney.repository

import by.heroes.heroesofdisney.db.DataBase
import by.heroes.heroesofdisney.model.MyHero
import javax.inject.Inject

class MyHeroesRepository @Inject constructor(
    private val dataBase: DataBase
) {
    suspend fun addMyHero(myHero: MyHero) = dataBase.myHeroesDao?.addMyHero(myHero)
    suspend fun deleteMyHero(idHero: Int) = dataBase.myHeroesDao?.deleteHero(idHero)
    suspend fun findMyHero(idHero: Int?) = dataBase.myHeroesDao?.findMyHero(idHero)
    suspend fun getAllMyHeroes() = dataBase.myHeroesDao?.getAllMyHeroes()
}