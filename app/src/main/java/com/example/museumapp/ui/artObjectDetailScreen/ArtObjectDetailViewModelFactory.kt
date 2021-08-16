package com.example.museumapp.ui.artObjectDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtObjectDetailViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtObjectDetailViewModel::class.java)) {
            return ArtObjectDetailViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}