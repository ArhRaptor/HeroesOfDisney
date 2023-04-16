package by.heroes.heroesofdisney.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import by.heroes.heroesofdisney.R
import by.heroes.heroesofdisney.arh.State
import by.heroes.heroesofdisney.model.Hero
import by.heroes.heroesofdisney.navigation.ABOUT_HERO_ROUTE
import by.heroes.heroesofdisney.ui.StartViewModel
import coil.compose.AsyncImage


@Composable
fun ListHeroes(
    viewModel: StartViewModel,
    navController: NavHostController
) {
    val state = viewModel.state.observeAsState()

    val loadAllHeroes = MutableLiveData<Boolean>()
    val loadState = loadAllHeroes.observeAsState(true)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.purple_700))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_head),
                contentDescription = stringResource(R.string.img_head),
                contentScale = ContentScale.FillHeight
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.size(65.dp),
                    onClick = {
                        loadAllHeroes.value = true
                    }, shape = RoundedCornerShape(20.dp)
                ) {
                    Column {
                        if (loadState.value == true) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_all),
                                contentDescription = stringResource(R.string.all)
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.icon_all_anselect),
                                contentDescription = stringResource(R.string.all)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    modifier = Modifier.size(65.dp),
                    onClick = {
                        loadAllHeroes.value = false
                    },
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column {
                        if (loadState.value == false) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_my),
                                contentDescription = stringResource(R.string.my),
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.icon_my_anselect),
                                contentDescription = stringResource(R.string.my),
                            )
                        }
                    }
                }
            }
        }

        Row(modifier = Modifier.fillMaxHeight()) {
            if (loadState.value == true) {
                val list = viewModel.flow.collectAsLazyPagingItems()

                LazyColumn(content = {

                    items(list.itemSnapshotList) {
                        HeroesItem(hero = it!!) { idHero ->
                            navController.navigate("$ABOUT_HERO_ROUTE/${idHero}")
                        }
                    }
                })
            } else {
                viewModel.getAllMyHeroes()
                LazyColumn(content = {

                    (state.value as? State.LoadedHeroesList)?.heroList?.let { list ->

                        items(list) {
                            HeroesItem(hero = it) { idHero ->
                                navController.navigate("$ABOUT_HERO_ROUTE/${idHero}")
                            }
                        }
                    }

                })

            }
        }
    }
}

@Composable
fun HeroesItem(hero: Hero, onClick: (id: Int) -> Unit) {
    Card(elevation = CardDefaults.cardElevation(10.dp), modifier = Modifier.padding(10.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .clickable {
                    onClick(hero._id)
                }
        ) {
            AsyncImage(
                model = hero.imageUrl,
                contentDescription = "Hero ${hero.name}"
            )
            Spacer(modifier = Modifier.padding(3.dp))

            Text(text = hero.name)
        }
    }
}