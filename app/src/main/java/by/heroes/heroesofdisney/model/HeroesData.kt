package by.heroes.heroesofdisney.model

data class HeroesData(
    val count: Int,
    val data: ArrayList<Hero>,
    val nextPage: String,
    val totalPages: Int
)