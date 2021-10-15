package com.kmmsampleapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kmmsampleapp.databinding.FragmentDbBinding
import com.shared.data.Sample
import com.shared.dbRepo.TestDbViewModel
import com.shared.operations.db.RealmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DbFragment : Fragment() {
    private lateinit var binding: FragmentDbBinding
    private lateinit var testDbViewModel: TestDbViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_db, container, false)
        binding.dbData = this

        testDbViewModel = ViewModelProvider(this).get(TestDbViewModel::class.java)

        return binding.root
    }

    fun insetItems(view: View) {
        Log.d(DbFragment::class.java.name, "[insetItems] called started to add records")

        for (i in 0..10000000) {
            GlobalScope.launch(Dispatchers.Default) {
//                Log.d(DbFragment::class.java.name, "[insetItems] called to add item$i")
                val sample = Sample()
                sample.name = "item$i"
                RealmDatabase.addItemInRealm(sample)
            }
        }

        Log.d(DbFragment::class.java.name, "[insetItems] called finished from adding records")

    }

//    fun insetItems(view: View) {
//        for (i in 0..10000) {
//            AppQueues.postToDbThreadPool {
//                Log.d(DbFragment::class.java.name, "[insetItems] called to add item$i")
//                testDbViewModel.addItem("item$i", ContextArgs(requireContext()))
//            }
//        }
//
//    }

    fun retrieveItems(view: View) {
        GlobalScope.launch(Dispatchers.Main)
        {
            testDbViewModel.getItemsFromRealm().addObserver {
                Log.d(DbFragment::class.java.name, "[retrieveItems] db size: ${it.size}")

            }

        }

    }
}