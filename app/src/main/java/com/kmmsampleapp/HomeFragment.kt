package com.kmmsampleapp

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.kmmsampleapp.databinding.FragmentHomeBinding
import com.shared.mainViewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var popularMoviesAdapter: PopularMoviesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.popularFragment = this

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        popularMoviesAdapter = PopularMoviesRecyclerAdapter()
        binding.popularItemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.popularItemsRecyclerView.adapter = popularMoviesAdapter
        binding.popularItemsRecyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                if (parent.getChildAdapterPosition(view) == 1) {
                    outRect.top = 20
                }
            }
        })

        loadData()
    }

    fun loadData() {
        binding.mainProgress.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.Main) {
            mainViewModel.getMovies().addObserver {
                it.results?.let { movies ->
                    binding.mainProgress.visibility = View.GONE
                    popularMoviesAdapter.setMovieItemList(movies)
                }
            }
        }
    }

    fun showMapScreen(view: View) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mapFragment)
    }
}