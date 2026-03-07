package org.dnajd.universalmediatracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Universal Media Tracker",
    ) {
        App()
    }
}