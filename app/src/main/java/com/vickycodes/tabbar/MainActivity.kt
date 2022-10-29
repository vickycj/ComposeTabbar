package com.vickycodes.tabbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
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

    val tabType = remember {
        getTabTypes()
    }

    var index by remember {
        mutableStateOf(0)
    }

    var parentPosition by remember {
        mutableStateOf(IntSize.Zero)
    }

    var homeOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    var gameOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    var screenOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    var videoOffSet by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    val currentOffset by remember(index) {
        derivedStateOf {
            when(tabState) {
                TabBar.Home -> homeOffSet
                TabBar.Game -> gameOffSet
                TabBar.Screen -> screenOffSet
                TabBar.Video -> videoOffSet
            }
        }
    }

    Box(modifier = Modifier
        .padding(start = 24.dp, end = 24.dp)
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(elevation = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colors.surface)
                    .onGloballyPositioned {
                        parentPosition = it.size
                    },
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                TabBar1Image(
                    modifier = Modifier.onGloballyPositioned {
                        homeOffSet = it.positionInParent()
                    },
                    res = tabType[0].res,
                    type = tabType[0].type,
                    isSelected = tabState == tabType[0].type,
                    onClick = {
                        index = 0
                        tabState = it
                    })
                TabBar1Image(
                    modifier = Modifier.onGloballyPositioned {
                        gameOffSet = it.positionInParent()
                    },
                    res = tabType[1].res,
                    type = tabType[1].type,
                    isSelected = tabState == tabType[1].type,
                    onClick = {
                        index = 1
                        tabState = it
                    })
                TabBar1Image(
                    modifier = Modifier.onGloballyPositioned {
                        screenOffSet = it.positionInParent()
                    },
                    res = tabType[2].res,
                    type = tabType[2].type,
                    isSelected = tabState == tabType[2].type,
                    onClick = {
                        index = 2
                        tabState = it
                    })
                TabBar1Image(
                    modifier = Modifier.onGloballyPositioned {
                        videoOffSet = it.positionInParent()
                    },
                    res = tabType[3].res,
                    type = tabType[3].type,
                    isSelected = tabState == tabType[3].type,
                    onClick = {
                        index = 3
                        tabState = it

                    })

            }

            Box(modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth()
                .absoluteOffset(x = currentOffset.x.dp - ((index+ 1) * 100).dp)) {
                TabCircleInflated(tabType = tabType[index])
            }

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
fun TabCircleInflated(tabType: TabType = getTabTypes()[0]) {
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
            TabBar1Image(tabType.res, isInflated = true)
        }
    }
}

private fun getTabTypes() = listOf(
    TabType(TabBar.Home, R.drawable.ic_baseline_home_24),
    TabType(TabBar.Game, R.drawable.ic_baseline_videogame_asset_24),
    TabType(TabBar.Screen, R.drawable.ic_baseline_layers_24),
    TabType(TabBar.Video, R.drawable.ic_baseline_ondemand_video_24)
)

data class TabType (
    val type : TabBar,
    val res: Int,
)

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

