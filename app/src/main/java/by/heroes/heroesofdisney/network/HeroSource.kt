package by.heroes.heroesofdisney.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import by.heroes.heroesofdisney.model.Hero
import by.heroes.heroesofdisney.repository.HeroesRepository
import javax.inject.Inject

class HeroSource @Inject constructor(
    private val repository: HeroesRepository
) : PagingSource<Int, Hero>() {


    override fun getRefreshKey(state: PagingState<Int, Hero>): Int {
        return 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        val page = params.key?:1
        val response = repository.getHeroes(page)
        return if (response.isSuccessful){
            LoadResult.Page(
                response.body()?.data ?: arrayListOf(),
                null,
                page + 1
            )
        }else{
            LoadResult.Error(Exception())
        }
    }
}