package by.heroes.heroesofdisney.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataBase @Inject constructor(
    @ApplicationContext context: Context
) {
    var myHeroesDao:MyHeroesDao? = null
    private var appDataBase:AppDataBase? = null


    init{
        if (appDataBase == null) {
            appDataBase = Room.databaseBuilder(context, AppDataBase::class.java, "MyHeroesDataBase").build()

            myHeroesDao = appDataBase?.myHeroesDao()
        }
    }

}