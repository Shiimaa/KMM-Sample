package com.shared.dbRepo

import com.shared.operations.db.DatabaseInstance
import com.shared.utils.ContextArgs
import comkmmsampleappshareddb.Test
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import io.github.aakira.napier.Napier

object TestDbRepo {
    val TAG = "TestDbRepo"

    suspend fun getItems(contextArgs: ContextArgs?): MutableLiveData<List<String>> {
        val result: List<String> = DatabaseInstance.getDatabaseInstance(contextArgs).getAllUser()

        return MutableLiveData(result)
    }

    suspend fun addItem(name: String, contextArgs: ContextArgs?) {
        Napier.v("[addItem] called with name: $name", tag = TAG)

        DatabaseInstance.getDatabaseInstance(contextArgs).insertUser(name)
    }

    fun addItemWithoutSuspend(name: String, contextArgs: ContextArgs?) {
        Napier.v("[addItem] called with name: $name", tag = TAG)

        DatabaseInstance.getDatabaseInstance(contextArgs).insertUser(name)
    }
}