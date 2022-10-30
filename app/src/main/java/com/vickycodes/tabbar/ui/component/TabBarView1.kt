package com.vickycodes.tabbar.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import com.vickycodes.tabbar.R
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.vickycodes.tabbar.ui.theme.TabbarTheme

@Composable
fun TabBarView1() {

    val tabType = remember {
        getTabTypes()
    }

    var tabState by remember {
        mutableStateOf(tabType[0].type)
    }

    var index by remember {
        mutableStateOf(0)
    }

    var parentOffset by remember {
        mutableStateOf(IntSize.Zero)
    }

    val offsetAnimation: Dp by animateDpAsState(
        ((parentOffset.width * (index + 1)) - (parentOffset.width / 2)).dp
    )

    Box(modifier = Modifier
        .padding(start = 24.dp, end = 24.dp)
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        Box(modifier = Modifier.wrapContentSize()) {

            LazyRow(modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(60.dp)
                .shadow(elevation = 16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colors.surface),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    items(tabType.size) { i ->
                        TabBar1Image(
                            modifier = Modifier.onGloballyPositioned {
                                parentOffset = it.size
                            },
                            res = tabType[i].res,
                            type = tabType[i].type,
                            isSelected = tabState == tabType[i].type,
                            onClick = {
                                index = i
                                tabState = it

                            })
                    }
                })

            Box(modifier = Modifier
                .wrapContentSize()
                .absoluteOffset(x = offsetAnimation)) {
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
    Box(modifier = Modifier.size(24.dp)) {
        AnimatedVisibility(visible = isSelected.not(),
            enter = slideInVertically(),
            exit = slideOutVertically()) {
            Icon(modifier = modifier
                .clickable {
                    onClick(type)
                },
                painter = painterResource(id = res),
                tint = if (isInflated) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
                contentDescription = "tab")
        }
    }

}

@Composable
fun TabCircleInflated(tabType: TabType = getTabTypes()[0]) {
    Box(modifier = Modifier
        .size(64.dp)
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
            Box(modifier = Modifier) {
                TabBar1Image(tabType.res, isInflated = true)
            }
        }
    }
}

private fun getTabTypes() = listOf(
    TabType(TabBar.Home, R.drawable.ic_baseline_home_24),
    TabType(TabBar.Game, R.drawable.ic_baseline_videogame_asset_24),
    TabType(TabBar.Screen, R.drawable.ic_baseline_layers_24),
    TabType(TabBar.Video, R.drawable.ic_baseline_ondemand_video_24)
)


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