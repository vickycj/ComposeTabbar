package com.vickycodes.tabbar.ui.component

data class TabType(
    val type: TabBar,
    val res: Int,
)

enum class TabBar {
    Home, Game, Screen, Video
}