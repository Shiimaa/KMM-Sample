package com.shared.dbRepo

import com.shared.data.Sample
import com.shared.operations.db.DatabaseInstance
import com.shared.operations.db.RealmDatabase
import com.shared.utils.ContextArgs
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import io.github.aakira.napier.Napier
import io.realm.objects

object TestDbRepo {
    val TAG = "TestDbRepo"

//    suspend fun getItems(contextArgs: ContextArgs?): MutableLiveData<List<String>> {
//        val result: List<String> = DatabaseInstance.getDatabaseInstance(contextArgs).getAllUser()
//
//        return MutableLiveData(result)
//    }
//
//    suspend fun addItem(name: String, contextArgs: ContextArgs?) {
////        Napier.v("[addItem] called with name: $name", tag = TAG)
//        DatabaseInstance.getDatabaseInstance(contextArgs).insertUser(name)
//    }

    suspend fun getItemsFromRealm(): MutableLiveData<List<Sample>> {
        val result = RealmDatabase.getRealmInstance().objects<Sample>()

        return MutableLiveData(result)
    }

    suspend fun addItemInRealm(sample: Sample) {
//        Napier.v("[addItem] called with name: $name", tag = TAG)
        RealmDatabase.getRealmInstance().writeBlocking {
            copyToRealm(sample)
        }
    }

//    fun addItemWithoutSuspend(name: String, contextArgs: ContextArgs?) {
//        Napier.v("[addItem] called with name: $name", tag = TAG)
//
//        DatabaseInstance.getDatabaseInstance(contextArgs).insertUser(name)
//    }
}