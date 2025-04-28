package com.example.movies_rx.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies_rx.apiCall.Movie

@Composable
fun MovieDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.posterPath,
                contentDescription = "Title",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movie.originalTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = "Id            : ${movie.movieId}",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Rating        : ${movie.voteAverage}",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Production    : ${movie.createdAt}",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Certificate   : ${movie.adult}",
                    fontSize = 20.sp,
                )
                Text(
                    text = "Language      : ${movie.originalLanguage}",
                    fontSize = 20.sp,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            LazyRow(
                modifier = Modifier.padding(5.dp)
            ) {
                items(movie.casts) { cast->
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(130.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(

                        ){
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .weight(0.8f)
                            ){
                                AsyncImage(
                                    model = cast.profilePath,
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Crop
                                )
                            }

                            Box(
                                modifier = Modifier.fillMaxWidth().
                                weight(0.2f),
                                contentAlignment = Alignment.Center
                            ){
                                Text(
                                    text = cast.name
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}