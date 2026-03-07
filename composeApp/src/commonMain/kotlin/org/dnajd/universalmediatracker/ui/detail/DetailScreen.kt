package org.dnajd.universalmediatracker.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.dnajd.universalmediatracker.domain.MuseumObject
import org.dnajd.universalmediatracker.ui.EmptyScreenContent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import universalmediatracker.composeapp.generated.resources.Res
import universalmediatracker.composeapp.generated.resources.back
import universalmediatracker.composeapp.generated.resources.label_artist
import universalmediatracker.composeapp.generated.resources.label_credits
import universalmediatracker.composeapp.generated.resources.label_date
import universalmediatracker.composeapp.generated.resources.label_department
import universalmediatracker.composeapp.generated.resources.label_dimensions
import universalmediatracker.composeapp.generated.resources.label_medium
import universalmediatracker.composeapp.generated.resources.label_repository
import universalmediatracker.composeapp.generated.resources.label_title

@Composable
fun DetailScreen(
    objectId: Int,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel>()

    val obj by viewModel.getObject(objectId).collectAsStateWithLifecycle(initialValue = null)
    AnimatedContent(obj != null) { objectAvailable ->
        if (objectAvailable) {
            ObjectDetails(obj!!, onBackClick = navigateBack)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                }
            )
        },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            AsyncImage(
                model = obj.primaryImageSmall,
                contentDescription = obj.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            SelectionContainer {
                Column(Modifier.padding(12.dp)) {
                    Text(obj.title, style = MaterialTheme.typography.headlineMedium)
                    Spacer(Modifier.height(6.dp))
                    LabeledInfo(stringResource(Res.string.label_title), obj.title)
                    LabeledInfo(stringResource(Res.string.label_artist), obj.artistDisplayName)
                    LabeledInfo(stringResource(Res.string.label_date), obj.objectDate)
                    LabeledInfo(stringResource(Res.string.label_dimensions), obj.dimensions)
                    LabeledInfo(stringResource(Res.string.label_medium), obj.medium)
                    LabeledInfo(stringResource(Res.string.label_department), obj.department)
                    LabeledInfo(stringResource(Res.string.label_repository), obj.repository)
                    LabeledInfo(stringResource(Res.string.label_credits), obj.creditLine)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Spacer(Modifier.height(6.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            }
        )
    }
}
