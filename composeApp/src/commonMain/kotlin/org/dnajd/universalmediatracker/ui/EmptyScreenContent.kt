package org.dnajd.universalmediatracker.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.dnajd.universalmediatracker.util.JavascriptParser
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import universalmediatracker.composeapp.generated.resources.Res
import universalmediatracker.composeapp.generated.resources.no_data_available

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmptyScreenContent(
    modifier: Modifier = Modifier,
) {
    CoroutineScope(Dispatchers.Main).launch {
        println("TEST HELLOOOO")
        val parser = JavascriptParser()

        parser.evaluate<Unit>(
            """
            function getResult() {
                return 1
            }
            """
        )

        /*
        val test = parser.evaluate<Int>(
            """
            function getResult() {
                return 1
            }
        """)
         */


        println("HELLOOO")
        val result = parser.evaluate<Int>("getResult()")
        println("Result $result")
        println("HELLOOO")
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(stringResource(Res.string.no_data_available))
    }
}
