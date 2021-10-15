package com.shared.dbRepo

import com.shared.data.Sample
import com.shared.utils.ContextArgs
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class TestDbViewModel : ViewModel() {

//    suspend fun getItems(contextArgs: ContextArgs?): LiveData<List<String>> {
//        return TestDbRepo.getItems(contextArgs)
//    }
//
    suspend fun addItemInRealm(name: String) {
        val sample = Sample()
        sample.name = name
        TestDbRepo.addItemInRealm(sample)
    }

    suspend fun getItemsFromRealm(): LiveData<List<Sample>> {
        return TestDbRepo.getItemsFromRealm()
    }

//    suspend fun addItem(name: String, contextArgs: ContextArgs?) {
//        TestDbRepo.addItem(name, contextArgs)
//    }

//    fun addItemWithoutSuspend(name: String, contextArgs: ContextArgs?) {
//        TestDbRepo.addItemWithoutSuspend(name, contextArgs)
//    }
}