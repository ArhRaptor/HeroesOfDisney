package by.heroes.heroesofdisney.arh

import by.heroes.heroesofdisney.model.Hero
import by.heroes.heroesofdisney.model.ItemHero

sealed class State {
    class LoadedHeroesList(val heroList: ArrayList<Hero>) : State()
    class InitHeroesScreen(val hero: ItemHero) : State()
}