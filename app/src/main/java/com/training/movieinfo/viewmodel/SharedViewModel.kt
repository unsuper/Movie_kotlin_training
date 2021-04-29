package com.training.movieinfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.training.movieinfo.model.Movie

class SharedViewModel : ViewModel() {

    val selected = MutableLiveData<Movie.Item>()
    
}