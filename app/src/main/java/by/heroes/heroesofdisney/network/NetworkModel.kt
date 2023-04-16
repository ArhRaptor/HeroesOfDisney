package by.heroes.heroesofdisney.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModel {

    @Provides
    fun init(): HeroesApi {
        return Retrofit.Builder()
            .baseUrl("https://api.disneyapi.dev")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroesApi::class.java)
    }
}