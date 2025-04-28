package com.example.movies_rx.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movies_rx.apiCall.MovieViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel,
    navController: NavController
){

    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    val onTextChange : (TextFieldValue) -> Unit = {searchText = it}

    val movies = viewModel.movies.value
//    val filterMovies = movies.filter { movie ->
//        movie.originalTitle.contains(searchText.text, ignoreCase = true)
//    }

    Box (
        modifier =   Modifier.background(Color.Gray)
    ) {
        Column (
            modifier = Modifier.padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(15.dp))
                    .padding(horizontal = 12.dp)
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = searchText,
                    onValueChange = onTextChange,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 18.dp, bottom = 18.dp)
                        .wrapContentHeight(),
                    textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(start = 15.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (searchText.text.isEmpty()) {
                                Text(
                                    text = "Search...",
                                    fontSize = 18.sp,
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(30.dp)
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                content = {
                    items(movies) { movie ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                                .height(180.dp)
                                .padding(5.dp),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                AsyncImage(
                                    model = movie.posterPath,
                                    modifier = Modifier.fillMaxSize()
                                        .clickable {
                                            navController.navigate("details/${movie.movieId}")
                                        },
                                    contentDescription = "movie.originalTitle",
                                    contentScale = ContentScale.Crop

                                )
                            }
                        }
                    }
                }
            )
        }
    }
}