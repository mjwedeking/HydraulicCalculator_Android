package com.mjwedeking.navHydraulicCalc.ui.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {

    val flow = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val depth = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val diameter = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val velocity = MutableLiveData<Double>().apply {
        value =0.0
    }
    val slope = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val nvalue = MutableLiveData<Double>().apply {
        value = 0.013
    }
    val dD = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val qCap = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val cap = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val velHead = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val hydRadius = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val wP = MutableLiveData<Double>().apply {
        value = 0.0
    }
    val wA = MutableLiveData<Double>().apply {
        value = 0.0
    }

    val flowUnits = MutableLiveData<String>().apply {
        value = "mgd"
    }
    val depthUnits = MutableLiveData<String>().apply {
        value = "inch"
    }
    val diameterUnits = MutableLiveData<String>().apply {
        value = "inch"
    }
    val velocityUnits = MutableLiveData<String>().apply {
        value = "fps"
    }
    val slopeUnits = MutableLiveData<String>().apply {
        value = "ft/ft"
    }
    val dDUnits = MutableLiveData<Double>()
    val qCapUnits = MutableLiveData<Double>()
    val velHeadUnits = MutableLiveData<Double>()
    val wPUnits = MutableLiveData<Double>()
    val wAUnits = MutableLiveData<Double>()

    val calculate = MutableLiveData<String>().apply {
        value = "flow"
    }
    val rdoFlow = MutableLiveData<Boolean>()
    val rdoQVA = MutableLiveData<Boolean>()
    val rdoVQA = MutableLiveData<Boolean>()
    val rdoDepth = MutableLiveData<Boolean>()
}