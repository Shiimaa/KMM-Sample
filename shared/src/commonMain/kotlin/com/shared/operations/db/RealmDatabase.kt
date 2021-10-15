package com.shared.operations.db

import com.shared.data.Sample
import io.realm.Realm
import io.realm.RealmConfiguration

object RealmDatabase {
    private val realm: Realm by lazy {
        val configuration = RealmConfiguration.Builder(
            schema = setOf(Sample::class)
        ).build()
        Realm.open(configuration)
    }

    fun getRealmInstance(): Realm {
        return realm
    }

    suspend fun addItemInRealm(sample: Sample) {
        realm.write {
            copyToRealm(sample)
        }
    }

}