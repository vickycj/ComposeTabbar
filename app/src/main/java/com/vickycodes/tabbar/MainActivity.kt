package com.vickycodes.tabbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
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
    Box(modifier = Modifier
        .padding(start = 24.dp, end = 24.dp)
        .fillMaxWidth()
        .height(60.dp)
        .shadow(elevation = 16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colors.surface)

    ) {
        Row(modifier = Modifier.matchParentSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,) {

            TabBar1Image(R.drawable.ic_baseline_home_24)
            TabBar1Image(R.drawable.ic_baseline_videogame_asset_24)
            TabBar1Image(R.drawable.ic_baseline_layers_24)
            TabBar1Image(R.drawable.ic_baseline_ondemand_video_24)

        }
    }
}

@Composable
fun TabBar1Image(
    @DrawableRes res: Int,
    isInflated: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Icon(modifier = modifier.padding(16.dp),
        painter = painterResource(id = res),
        tint = if (isInflated) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary,
        contentDescription = "tab")
}

@Composable
fun TabCircleInflated() {

}

@Preview(showBackground = true)
@Composable
fun TabBarView1Preview() {
    TabbarTheme(darkTheme = false) {
        TabBarView1()
    }
}

