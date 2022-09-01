package com.example.dog_breeds

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.dog_breeds.domain.util.InternetConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(@ApplicationContext context: Context) :
    ViewModel() {
    val connectionLiveData = InternetConnectivity(context)
}