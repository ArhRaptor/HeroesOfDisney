package by.heroes.heroesofdisney.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.heroes.heroesofdisney.model.MyHero

@Dao
interface MyHeroesDao {

    @Insert
    suspend fun addMyHero(hero:MyHero)

    @Query("DELETE FROM my_heroes WHERE heroID = :heroId")
    suspend fun deleteHero(heroId: Int)

    @Query("SELECT * FROM my_heroes WHERE heroID = :heroId")
    suspend fun findMyHero(heroId: Int?): MyHero

    @Query("SELECT heroID FROM my_heroes")
    suspend fun getAllMyHeroes(): List<Int>
}