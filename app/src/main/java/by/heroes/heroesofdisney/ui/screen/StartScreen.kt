package by.heroes.heroesofdisney.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import by.heroes.heroesofdisney.R
import by.heroes.heroesofdisney.navigation.HERO_LIST_ROUTE

@Composable
fun ShowView(
    navController: NavHostController
) {

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .background(colorResource(id = R.color.purple_700))
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_background),
            contentDescription = "background image",
            contentScale = ContentScale.Fit
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .padding(top = 45.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.welcome),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontFamily = FontFamily.SansSerif
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 25.dp),
                onClick = {
                    navController.navigate(HERO_LIST_ROUTE)
                }, shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))
            ) {
                Text(text = stringResource(R.string.start), fontSize = 20.sp)
            }
        }
    }

}