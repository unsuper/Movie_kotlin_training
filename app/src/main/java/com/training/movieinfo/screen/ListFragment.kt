package com.training.movieinfo.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.movieinfo.adapter.AdapterCustom
import com.training.movieinfo.model.Movie
import com.training.movieinfo.databinding.FragmentMovieListBinding
import com.training.movieinfo.api.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class ListFragment : Fragment() {

    private val TAG = ListFragment::class.java.simpleName
    private lateinit var _binding : FragmentMovieListBinding
    private val binding get() = _binding
    private lateinit var adapter: AdapterCustom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        adapter = AdapterCustom(listOf()) {
            movieId -> findNavController().navigate(ListFragmentDirections.actionListFragmentToWatchFragment(movieId))
        }
        binding.recycleView.adapter = adapter

        crawlDataFromServer()

    }

    private fun crawlDataFromServer(){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Execute web request through coroutine call adapter & retrofit
                val response = Service.retrofitService.getAllMovieAsync().await()

                if (response.isSuccessful) {
                    val list: List<Movie.Item> = response.body()?.items as List<Movie.Item>
                    adapter.list = list
                    adapter.notifyDataSetChanged()
                } else {
                    // Print error information to the console
                    Log.d(TAG, "Error ${response.code()}")
                }
            } catch (e: IOException) {
                // Error with network request
                Log.e(TAG, "Exception " + e.printStackTrace())
            }
        }
    }
}