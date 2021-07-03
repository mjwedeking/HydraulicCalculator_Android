package com.mjwedeking.navHydraulicCalc.ui.conversion

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
import kotlinx.android.synthetic.main.fragment_conversion.*

class ConversionFragment : Fragment() {

    private lateinit var galleryViewModel: ConversionViewModel
    val conversion = UnitConversion()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(ConversionViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_conversion, container, false)

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        inputConvFlowEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.flow.postValue(input)
                            val output =  input * conversion.GetFactor(
                                inputConvFlowUnitSpinner.selectedItem.toString(),
                                outputConvFlowUnitSpinner.selectedItem.toString()
                            )
                            outputConvFlowEditText.setText(String.format("%.4f",output))
                        }
                        else {
                            outputConvFlowEditText.setText("0.0")
                        }
                    }
                }
            }
        })
        inputConvLengthEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.length.postValue(input)
                            val output = input * conversion.GetFactor(
                                inputConvLengthUnitSpinner.selectedItem.toString(),
                                outputConvLengthUnitSpinner.selectedItem.toString()
                            )
                            outputConvLengthEditText.setText(String.format("%.4f",output))
                        } else {
                            outputConvLengthEditText.setText("0.0")
                        }
                    }
                }
            }
        })

        inputConvVelocityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.velocity.postValue(input)
                            val output = input * conversion.GetFactor(
                                inputConvVelocityUnitSpinner.selectedItem.toString(),
                                outputConvVelocityUnitSpinner.selectedItem.toString()
                            )
                            outputConvVelocityEditText.setText(String.format("%.4f",output))
                        } else {
                            outputConvVelocityEditText.setText("0.0")
                        }
                    }
                }
            }
        })
        inputConvAreaEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        if (txt.toString().toDouble() > 0) {
                            input = txt.toString().toDouble()
                            galleryViewModel.area.postValue(input)
                            val output = input * conversion.GetFactor(
                                inputConvAreaUnitSpinner.selectedItem.toString(),
                                outputConvAreaUnitSpinner.selectedItem.toString()
                            )
                            outputConvAreaEditText.setText(String.format("%.4f",output))
                        } else {
                            outputConvAreaEditText.setText("0.0")
                        }
                    }
                }
            }
        })

        inputConvFlowUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.flow.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvFlowUnitSpinner.selectedItem.toString(),
                            outputConvFlowUnitSpinner.selectedItem.toString()
                        )
                        outputConvFlowEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvFlowEditText.setText("0.0")
                    }
                }
            }
        }
        outputConvFlowUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.flow.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvFlowUnitSpinner.selectedItem.toString(),
                            outputConvFlowUnitSpinner.selectedItem.toString()
                        )
                        outputConvFlowEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvFlowEditText.setText("0.0")
                    }
                }
            }
        }
        inputConvLengthUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.length.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvLengthUnitSpinner.selectedItem.toString(),
                            outputConvLengthUnitSpinner.selectedItem.toString()
                        )
                        outputConvLengthEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvLengthEditText.setText("0.0")
                    }
                }
            }
        }
        outputConvLengthUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.length.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvLengthUnitSpinner.selectedItem.toString(),
                            outputConvLengthUnitSpinner.selectedItem.toString()
                        )
                        outputConvLengthEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvLengthEditText.setText("0.0")
                    }
                }
            }
        }
        inputConvAreaUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.area.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvAreaUnitSpinner.selectedItem.toString(),
                            outputConvAreaUnitSpinner.selectedItem.toString()
                        )
                        outputConvAreaEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvAreaEditText.setText("0.0")
                    }
                }
            }
        }
        outputConvAreaUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.area.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvAreaUnitSpinner.selectedItem.toString(),
                            outputConvAreaUnitSpinner.selectedItem.toString()
                        )
                        outputConvAreaEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvAreaEditText.setText("0.0")
                    }
                }
            }
        }
        inputConvVelocityUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.velocity.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvVelocityUnitSpinner.selectedItem.toString(),
                            outputConvVelocityUnitSpinner.selectedItem.toString()
                        )
                        outputConvVelocityEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvVelocityEditText.setText("0.0")
                    }
                }
            }
        }
        outputConvVelocityUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.selectedItem.toString()
                selectedItem.let {
                    var input = galleryViewModel.velocity.value.toString().toDouble()
                    if (input > 0) {
                        val output =  input * conversion.GetFactor(
                            inputConvVelocityUnitSpinner.selectedItem.toString(),
                            outputConvVelocityUnitSpinner.selectedItem.toString()
                        )
                        outputConvVelocityEditText.setText(String.format("%.4f",output))
                    }
                    else {
                        outputConvVelocityEditText.setText("0.0")
                    }
                }
            }
        }
    }
}