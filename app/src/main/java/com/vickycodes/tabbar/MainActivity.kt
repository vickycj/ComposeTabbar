package com.vickycodes.tabbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vickycodes.tabbar.ui.theme.TabbarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabbarTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        TabBarView1()
                    }
                }
            }
        }
    }
}

@Composable
fun TabBarView1() {
    var tabState by remember {
        mutableStateOf(TabBar.Home)
    }
    Box(modifier = Modifier
        .padding(start = 24.dp, end = 24.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .shadow(elevation = 16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colors.surface)

    ) {
        Box() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                TabBar1Image(
                    res = R.drawable.ic_baseline_home_24,
                    type = TabBar.Home,
                    isSelected = tabState == TabBar.Home,
                    onClick = {
                        tabState = it
                    })
                TabBar1Image(
                    res = R.drawable.ic_baseline_videogame_asset_24,
                    type = TabBar.Game,
                    isSelected = tabState == TabBar.Game,
                    onClick = {
                        tabState = it
                    })
                TabBar1Image(
                    res = R.drawable.ic_baseline_layers_24,
                    type = TabBar.Screen,
                    isSelected = tabState == TabBar.Screen,
                    onClick = {
                        tabState = it
                    })
                TabBar1Image(
                    res = R.drawable.ic_baseline_ondemand_video_24,
                    type = TabBar.Video,
                    isSelected = tabState == TabBar.Video,
                    onClick = {
                        tabState = it
                    })

            }

            TabCircleInflated()

        }
    }
}

@Composable
fun TabBar1Image(
    @DrawableRes res: Int,
    isInflated: Boolean = false,
    isSelected: Boolean = false,
    type: TabBar = TabBar.Home,
    onClick: (type: TabBar) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Icon(modifier = modifier
        .alpha(if (isSelected) 0f else 1f)
        .clickable {
            onClick(type)
        },
        painter = painterResource(id = res),
        tint = if (isInflated) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        contentDescription = "tab")

}

@Composable
fun TabCircleInflated() {
    Box(modifier = Modifier
        .size(50.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.background)
        .padding(4.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .background(MaterialTheme.colors.onSurface),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabBar1Image(R.drawable.ic_baseline_home_24, isInflated = true)
        }
    }
}

enum class TabBar {
    Home, Game, Screen, Video
}

@Preview(showBackground = true)
@Composable
fun TabCircleInflatedPreview() {
    TabbarTheme(darkTheme = false) {
        TabCircleInflated()
    }
}


@Preview(showBackground = true)
@Composable
fun TabBarView1Preview() {
    TabbarTheme(darkTheme = false) {
        TabBarView1()
    }
}

