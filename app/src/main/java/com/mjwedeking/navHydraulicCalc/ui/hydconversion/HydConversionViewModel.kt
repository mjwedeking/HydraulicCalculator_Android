package com.mjwedeking.navHydraulicCalc.ui.hydconversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HydConversionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is conversion Fragment"
    }
    val text: LiveData<String> = _text

    val flowrate = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val timelapse = MutableLiveData<Double>().apply {
        value = 0.0
    }
}