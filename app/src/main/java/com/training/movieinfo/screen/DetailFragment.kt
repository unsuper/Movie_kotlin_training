package com.training.movieinfo.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.training.movieinfo.R
import com.training.movieinfo.model.Movie
import com.training.movieinfo.databinding.FragmentDetailBinding
import com.training.movieinfo.api.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding?=null
    private val binding get() = _binding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var movieId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        movieId = args.movieId
        _binding = FragmentDetailBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        runProgressBar()
        getMovieById(movieId)

        binding?.buttonPlay?.setOnClickListener{

            findNavController().navigate(DetailFragmentDirections.actionWatchFragmentToListFragment())
        }

    }

    private fun getMovieById(movieId: String){
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Execute web request through coroutine call adapter & retrofit
                val response = Service.retrofitService.getMovieByIdAsync(movieId).await()
                if (response.isSuccessful) {
                    applyDataToUI(response.body()?.items)
                } else {
                    // Print error information to the console
                    Log.d(tag, "Error ${response.code()}")
                }
            } catch (e: IOException) {
                // Error with network request
                Log.e(tag, "Exception " + e.printStackTrace())
            }
        }
    }

    private fun applyDataToUI(items: Movie.Item?) {
        binding?.textViewName?.text = items?.name
        binding?.textViewImdb?.text = getString(R.string.detail_imdb, items?.score.toString())
        Picasso.get().load("${items?.coverImg}").into(binding?.imageViewBackground)
        Picasso.get().load("${items?.coverImg}").into(binding?.imageViewProfile)
        runProgressBar()
    }

    private fun runProgressBar(){
        val isVisible = if(binding?.progressAction?.visibility == View.GONE) View.VISIBLE else View.GONE
        binding?.progressAction?.visibility = isVisible
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}