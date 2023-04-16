package by.heroes.heroesofdisney.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import by.heroes.heroesofdisney.arh.State
import by.heroes.heroesofdisney.model.Hero
import by.heroes.heroesofdisney.model.ItemHero
import by.heroes.heroesofdisney.model.MyHero
import by.heroes.heroesofdisney.network.HeroSource
import by.heroes.heroesofdisney.repository.HeroesRepository
import by.heroes.heroesofdisney.repository.MyHeroesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val repository: HeroesRepository,
    private val myHeroRepo: MyHeroesRepository,
    private val dataSource: HeroSource
) : ViewModel() {

    val state = MutableLiveData<State>()
    val isMyHero = MutableLiveData<Boolean>()

    val flow = Pager(
        PagingConfig(pageSize = 50)
    ){
        dataSource
    }.flow.cachedIn(viewModelScope)

    fun getHero(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHero(id)
            if (response.isSuccessful) {
                state.postValue(State.InitHeroesScreen(response.body() as ItemHero))
            }
        }
    }

    fun addMyHero(myHero: MyHero) {
        viewModelScope.launch(Dispatchers.IO) {
            myHeroRepo.addMyHero(myHero)
        }
    }

    fun deleteHero(heroId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            myHeroRepo.deleteMyHero(heroId)
        }
    }

    fun isMyFavoriteHero(heroId: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            myHeroRepo.findMyHero(heroId).let {
                if (it != null) {
                    isMyHero.postValue(true)
                } else {
                    isMyHero.postValue(false)
                }
            }
        }
    }

    fun getAllMyHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            getMyFavoriteHeroes(myHeroRepo.getAllMyHeroes())
        }
    }

    private suspend fun getMyFavoriteHeroes(list: List<Int>?) {

        val myHeroesList: ArrayList<Hero> = ArrayList()

        withContext(Dispatchers.IO) {
            val coroutines: List<Deferred<ItemHero?>> = List(list?.size ?: 0) {
                viewModelScope.async {
                    getHeroById(list?.get(it) ?: 0)
                }
            }
            coroutines.forEach {itemHero ->
                itemHero.await()?.item?.let {hero ->
                    myHeroesList.add(hero)
                }
            }

            state.postValue(State.LoadedHeroesList(myHeroesList))
        }

    }

    private suspend fun getHeroById(heroId: Int): ItemHero? {
        val response = repository.getHero(heroId)
        if (response.isSuccessful) {
            return response.body() as ItemHero
        }
        return null
    }
}