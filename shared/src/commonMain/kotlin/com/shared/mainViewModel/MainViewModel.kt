package com.shared.mainViewModel

import com.shared.data.MoviesResponse
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class MainViewModel : ViewModel() {

    suspend fun about(): LiveData<String> {
        return ApplicationApi().about()
    }
//    suspend fun getCategories(): LiveData<List<CategoryItem>> {
//        return ApplicationApi().getCategories()
//    }

    suspend fun getMovies(): MutableLiveData<MoviesResponse> {
        return ApplicationApi().getMovies()
    }
}