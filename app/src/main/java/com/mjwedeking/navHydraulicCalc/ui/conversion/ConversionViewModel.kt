package com.mjwedeking.navHydraulicCalc.ui.conversion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConversionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is conversion Fragment"
    }
    val text: LiveData<String> = _text
    val flow = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val length = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val area = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val velocity = MutableLiveData<Double>().apply {
        value =0.0
    }
}