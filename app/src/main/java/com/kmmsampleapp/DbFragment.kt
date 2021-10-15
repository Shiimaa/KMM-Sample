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
import com.shared.dbRepo.TestDbViewModel
import com.shared.utils.ContextArgs
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
        for (i in 0..2000) {
            GlobalScope.launch(Dispatchers.IO)
            {
                Log.d(DbFragment::class.java.name, "[insetItems] called to add item$i")
                testDbViewModel.addItem("item$i", ContextArgs(requireContext()))
            }
        }
    }

//    fun insetItems(view: View) {
//        for (i in 0..10000) {
//            AppQueues.postToDbThreadPool {
//                Log.d(DbFragment::class.java.name, "[insetItems] called to add item$i")
//                testDbViewModel.addItemWithoutSuspend("item$i", ContextArgs(requireContext()))
//            }
//        }
//
//    }

    fun retrieveItems(view: View) {
        GlobalScope.launch(Dispatchers.Main)
        {
            testDbViewModel.getItems(ContextArgs(requireContext())).addObserver {
                Log.d(DbFragment::class.java.name, "[retrieveItems] db size: ${it.size}")

            }

        }

    }

}