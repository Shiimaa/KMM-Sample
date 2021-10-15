package com.shared.dbRepo

import com.shared.utils.ContextArgs
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class TestDbViewModel : ViewModel() {

    suspend fun getItems(contextArgs: ContextArgs?): LiveData<List<String>> {
        return TestDbRepo.getItems(contextArgs)
    }

    suspend fun addItem(name: String, contextArgs: ContextArgs?) {
        TestDbRepo.addItem(name, contextArgs)
    }

    fun addItemWithoutSuspend(name: String, contextArgs: ContextArgs?) {
        TestDbRepo.addItemWithoutSuspend(name, contextArgs)
    }
}