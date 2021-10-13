package com.shared.mainViewModel

import com.shared.data.MoviesResponse
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import io.ktor.http.*

class ApplicationApi {
    private val AUTHORIZATION_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhOTlhYWNkMjk4ZGZjNTllZGFiZDI5ODI0MmJjZThmMiIsInN1YiI6IjYwNzAyYTUxOTI0Y2U1MDAyOWIzNjI0OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cMi-RBvqoL7k7Ll9BgS5ahh60_yJ3RDw2hL8q_9-Vro"

    private val client = HttpClient {
        defaultRequest {
            header("Authorization", "Bearer $AUTHORIZATION_TOKEN")
        }

        install(JsonFeature)
    }


    private val address = Url("https://cors-test.appspot.com/test")

    private val popularMovies =
        Url("https://api.themoviedb.org/3/movie/popular")

    suspend fun about(): MutableLiveData<String> {
        val aboutViewModel = MutableLiveData("")
        val result: String = client.get {
            url(address.toString())
        }

        aboutViewModel.postValue(result)
        return aboutViewModel
    }

    suspend fun getMovies(): MutableLiveData<MoviesResponse> {
        val aboutViewModel = MutableLiveData<MoviesResponse>(MoviesResponse())
        val result: MoviesResponse = client.get(popularMovies.toString()) {
            parameter("language", "en-US")
            parameter("page", 1)
        }

        aboutViewModel.postValue(result)
        return aboutViewModel
    }
}
