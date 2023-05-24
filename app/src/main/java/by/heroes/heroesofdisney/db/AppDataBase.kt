package by.heroes.heroesofdisney.db

import androidx.room.Database
import androidx.room.RoomDatabase
import by.heroes.heroesofdisney.model.MyHero

@Database(entities = [MyHero::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun myHeroesDao(): MyHeroesDao
}