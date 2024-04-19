package by.heroes.heroesofdisney.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import by.heroes.heroesofdisney.R
import by.heroes.heroesofdisney.arh.State
import by.heroes.heroesofdisney.model.MyHero
import by.heroes.heroesofdisney.ui.StartViewModel
import coil.compose.AsyncImage


@Composable
fun AboutHero(
    viewModel: StartViewModel,
    savedStateHandle: NavBackStackEntry
) {

    val isAddButton = viewModel.isMyHero.observeAsState()
    val state = viewModel.state.observeAsState()

    val id = checkNotNull(savedStateHandle.arguments).getInt("id")
    viewModel.getHero(id)

    (state.value as? State.InitHeroesScreen)?.hero.let { itemHero ->

        viewModel.isMyFavoriteHero(itemHero?.item?._id)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.purple_700))
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(color = colorResource(id = R.color.purple_700))
            ) {
                AsyncImage(
                    model = itemHero?.item?.imageUrl,
                    contentDescription = "Hero ${itemHero?.item?.name}",
                    contentScale = ContentScale.Inside
                )
            }

            Row(modifier = Modifier.padding(top = 16.dp)) {
                Text(
                    text = itemHero?.item?.name.toString(),
                    color = Color.White,
                    fontSize = 26.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

                isAddButton.value.let {
                    Button(
                        modifier = Modifier.size(50.dp),
                        onClick = {

                            if (it == true) {
                                viewModel.deleteHero(itemHero?.item?._id!!)
                            } else {
                                viewModel.addMyHero(MyHero(null, itemHero?.item?._id!!))
                            }

                        },
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        if (it == true){
                            Image(
                                painter = painterResource(id = R.drawable.icon_my),
                                contentDescription = stringResource(R.string.my),
                                contentScale = ContentScale.FillHeight
                            )
                        }else{
                            Image(
                                painter = painterResource(id = R.drawable.icon_my_anselect),
                                contentDescription = stringResource(R.string.my),
                                contentScale = ContentScale.FillHeight
                            )
                        }
                    }
                }

            }

            Row(
                modifier = Modifier.fillMaxSize()
            ) {

                LazyColumn(content = {

                    if (itemHero?.item?.allies?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.alies),
                                textContent = itemHero.item.allies
                            )
                        }
                    }
                    if (itemHero?.item?.enemies?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.enemies),
                                textContent = itemHero.item.enemies
                            )
                        }
                    }
                    if (itemHero?.item?.films?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.films),
                                textContent = itemHero.item.films
                            )
                        }
                    }
                    if (itemHero?.item?.parkAttractions?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.parks),
                                textContent = itemHero.item.parkAttractions
                            )
                        }
                    }
                    if (itemHero?.item?.shortFilms?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.short_films),
                                textContent = itemHero.item.shortFilms
                            )
                        }
                    }
                    if (itemHero?.item?.tvShows?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.tv_shows),
                                textContent = itemHero.item.tvShows
                            )
                        }
                    }
                    if (itemHero?.item?.videoGames?.isNotEmpty() == true) {
                        item {
                            Content(
                                head = stringResource(R.string.video_games),
                                textContent = itemHero.item.videoGames
                            )
                        }
                    }
                })
            }

        }

    }
}

@Composable
fun Content(head: String, textContent: List<String>) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = head, color = Color.White, fontSize = 26.sp, textAlign = TextAlign.Start)
    }

    LazyRow(modifier = Modifier.fillMaxWidth(),
        content = {
            itemsIndexed(textContent) { _, item ->
                Card(
                    elevation = CardDefaults.cardElevation(10.dp),
                    modifier = Modifier.padding(10.dp),
                    colors = CardDefaults.cardColors(colorResource(id = R.color.purple_200))
                ) {
                    Text(text = item, color = Color.White, modifier = Modifier.padding(3.dp))
                }
            }
        })

}