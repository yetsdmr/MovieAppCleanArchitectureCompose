package com.yetsdmr.movieappcleanarchitecturecompose.presentation.search_sc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.LocalMovies
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yetsdmr.movieappcleanarchitecturecompose.presentation.MainViewModel
import com.yetsdmr.movieappcleanarchitecturecompose.presentation.search_sc.components.MovieList
import com.yetsdmr.movieappcleanarchitecturecompose.util.MySpacer
import com.yetsdmr.movieappcleanarchitecturecompose.util.Result
import com.yetsdmr.movieappcleanarchitecturecompose.util.trimStartEnd

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SeacrhScreen(
    onItemClick: (String) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    var title by rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val searchResponse =
        viewModel.searchResponse.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Open Movie Database",
                        fontFamily = FontFamily.Serif
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Rounded.Movie,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MySpacer(size = 16.dp)
            Icon(
                imageVector = Icons.Rounded.LocalMovies,
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
            )
            MySpacer(size = 16.dp)
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth(.9f),
                shape = RectangleShape,
                placeholder = {
                    Text(
                        text = "Enter Title",
                        fontFamily = FontFamily.Monospace
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (title.isNotBlank()) {
                            viewModel.getSearchResult(title.trimStartEnd())
                            keyboardController?.hide()
                        }
                    }
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp
                ),
                trailingIcon = {
                    IconButton(onClick = { title = "" }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = null
                        )
                    }
                }
            )
            MySpacer(size = 16.dp)
            Button(onClick = {
                if (title.isNotBlank()) {
                    viewModel.getSearchResult(title.trimStartEnd())
                    keyboardController?.hide()
                }
            }) {
                Text(
                    text = "Search Database",
                    fontFamily = FontFamily.Monospace,
                    fontSize = 18.sp
                )
            }
            MySpacer(size = 16.dp)
            when (searchResponse) {
                is Result.Success -> {
                    val searchResult = searchResponse.data
                    MovieList(
                        searchResult = searchResult,
                        onItemClick = onItemClick
                    )
                }

                is Result.Loading -> {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                    )
                }

                is Result.Error -> {
                    Text(
                        text = searchResponse.error.localizedMessage ?: "Something went wrong!",
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                else -> Unit
            }
        }
    }
}