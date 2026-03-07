package org.dnajd.universalmediatracker

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.dnajd.universalmediatracker.di.initKoin

fun main() = application {
    initKoin()

    Window(
        onCloseRequest = ::exitApplication,
        title = "Universal Media Tracker",
    ) {
        App()
    }
}