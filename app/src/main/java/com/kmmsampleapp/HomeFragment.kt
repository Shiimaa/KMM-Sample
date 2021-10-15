package com.kmmsampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.kmmsampleapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.homeData = this

        return binding.root
    }

    fun openNetwork(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_networkFragment)
    }

    fun openDb(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_dbFragment)
    }
}