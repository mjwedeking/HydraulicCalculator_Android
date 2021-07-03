package com.mjwedeking.navHydraulicCalc.ui.hydconversion

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mjwedeking.navHydraulicCalc.UnitConversion
import com.mjwedeking.navHydraulicCalc.R
import kotlinx.android.synthetic.main.fragment_hydconversion.*

class HydConversionFragment : Fragment() {

    private lateinit var galleryViewModel: HydConversionViewModel
    val conversion = UnitConversion()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(HydConversionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_hydconversion, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        inputConvCalcFlowEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    var time = galleryViewModel.timelapse.value.toString().toDouble()
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.flowrate.postValue(input)
                            var output =  input * conversion.GetFactor(
                                inputConvCalcFlowUnitSpinner.selectedItem.toString(),
                                "gpm") * time * conversion.GetFactor(inputConvCalcTimeUnitSpinner.selectedItem.toString(),"min")
                            output = output * conversion.GetFactor("gal",outputConvCalcVolumeUnitSpinner.selectedItem.toString())
                            outputConvCalcVolumeEditText.setText(String.format("%.4f",output))
                        }
                        else {
                            outputConvCalcVolumeEditText.setText("0.0")
                        }
                    }
                }
            }
        })
        inputConvCalcTimeEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    var flowrate = galleryViewModel.flowrate.value.toString().toDouble()
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.timelapse.postValue(input)
                            var output =  flowrate * conversion.GetFactor(
                                inputConvCalcFlowUnitSpinner.selectedItem.toString(),
                                "gpm") * input * conversion.GetFactor(inputConvCalcTimeUnitSpinner.selectedItem.toString(),"min")
                            output = output * conversion.GetFactor("gal",outputConvCalcVolumeUnitSpinner.selectedItem.toString())
                            outputConvCalcVolumeEditText.setText(String.format("%.4f",output))
                        }
                        else {
                            outputConvCalcVolumeEditText.setText("0.0")
                        }
                    }
                }
            }
        })
        inputConvCalcFlowUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var timelapse = galleryViewModel.timelapse.value.toString().toDouble()
                    var flowrate = galleryViewModel.flowrate.value.toString().toDouble()
                    var output =  flowrate * conversion.GetFactor(
                        inputConvCalcFlowUnitSpinner.selectedItem.toString(),
                        "gpm") * timelapse * conversion.GetFactor(inputConvCalcTimeUnitSpinner.selectedItem.toString(),"min")
                    output = output * conversion.GetFactor("gal",outputConvCalcVolumeUnitSpinner.selectedItem.toString())
                    outputConvCalcVolumeEditText.setText(String.format("%.4f",output))
                }
            }
        }
        inputConvCalcTimeUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var timelapse = galleryViewModel.timelapse.value.toString().toDouble()
                    var flowrate = galleryViewModel.flowrate.value.toString().toDouble()
                    var output =  flowrate * conversion.GetFactor(
                        inputConvCalcFlowUnitSpinner.selectedItem.toString(),
                        "gpm") * timelapse * conversion.GetFactor(inputConvCalcTimeUnitSpinner.selectedItem.toString(),"min")
                    output = output * conversion.GetFactor("gal",outputConvCalcVolumeUnitSpinner.selectedItem.toString())
                    outputConvCalcVolumeEditText.setText(String.format("%.4f",output))
                }
            }
        }
        outputConvCalcVolumeUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var timelapse = galleryViewModel.timelapse.value.toString().toDouble()
                    var flowrate = galleryViewModel.flowrate.value.toString().toDouble()
                    var output =  flowrate * conversion.GetFactor(
                        inputConvCalcFlowUnitSpinner.selectedItem.toString(),
                        "gpm") * timelapse * conversion.GetFactor(inputConvCalcTimeUnitSpinner.selectedItem.toString(),"min")
                    output = output * conversion.GetFactor("gal",outputConvCalcVolumeUnitSpinner.selectedItem.toString())
                    outputConvCalcVolumeEditText.setText(String.format("%.4f",output))
                }
            }
        }
    }
}