package com.mjwedeking.navHydraulicCalc.ui.calculator


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mjwedeking.navHydraulicCalc.R
import com.mjwedeking.navHydraulicCalc.UnitConversion
import kotlinx.android.synthetic.main.fragment_calculator_input.*


class InputFragment : Fragment() {
    private val thetag = "Calculator"

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator_input, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            /**
             *  create view model in activity scope
             */
            sharedViewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)
        }

        calculateButton.setOnClickListener(View.OnClickListener {
            calculate()
        })

        calculateRadioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                checkedId?.let {
                    when (checkedId){
                        R.id.radio_flow -> {
                            sharedViewModel.calculate.postValue("flow")
                            inputFlowEditText.setText("")
                            inputFlowEditText.isEnabled = false
                            inputVelocityEditText.setText("")
                            inputVelocityEditText.isEnabled = false
                            inputDepthEditText.isEnabled = true
                            inputDiameterEditText.isEnabled = true
                            inputSlopeEditText.isEnabled = true
                            inputNValueEditText.isEnabled = true
                        }
                        R.id.radio_depth -> {
                            sharedViewModel.calculate.postValue("depth")
                            inputDepthEditText.setText("")
                            inputVelocityEditText.setText("")
                            inputFlowEditText.isEnabled = true
                            inputVelocityEditText.isEnabled = false
                            inputDepthEditText.isEnabled = false
                            inputDiameterEditText.isEnabled = true
                            inputSlopeEditText.isEnabled = true
                            inputNValueEditText.isEnabled = true
                        }
                        R.id.radio_qva -> {
                            sharedViewModel.calculate.postValue("qva")
                            inputFlowEditText.setText("")
                            inputSlopeEditText.setText("")
                            inputFlowEditText.isEnabled = false
                            inputVelocityEditText.isEnabled = true
                            inputDepthEditText.isEnabled = true
                            inputDiameterEditText.isEnabled = true
                            inputSlopeEditText.isEnabled = false
                            inputNValueEditText.isEnabled = false
                        }
                        R.id.radio_vqa -> {
                            sharedViewModel.calculate.postValue("vqa")
                            inputSlopeEditText.setText("")
                            inputVelocityEditText.setText("")
                            inputFlowEditText.isEnabled = true
                            inputVelocityEditText.isEnabled = false
                            inputDepthEditText.isEnabled = true
                            inputDiameterEditText.isEnabled = true
                            inputSlopeEditText.isEnabled = false
                            inputNValueEditText.isEnabled = false
                        }
                        else -> {
                            sharedViewModel.calculate.postValue("flow")
                            inputFlowEditText.setText("")
                            inputFlowEditText.isEnabled = false
                            inputVelocityEditText.setText("")
                            inputVelocityEditText.isEnabled = false
                            inputDepthEditText.isEnabled = true
                            inputDiameterEditText.isEnabled = true
                            inputSlopeEditText.isEnabled = true
                            inputNValueEditText.isEnabled = true
                        }
                    }
                }
                calculate()
                Log.i(thetag, "radio changed to $checkedId")
            }
        })

        inputFlowEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "vqa")
                calculate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }
                    Log.i(thetag, "flow changed")
                    sharedViewModel.flow.postValue(input)
                }
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "vqa")
                    calculate()
            }
        })
        inputDepthEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="flow" || calc == "qva")
                    calculate()
                 }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }
                    Log.i(thetag, "depth changed $txt $input")
                    sharedViewModel.depth.postValue(input)
                    Log.i(thetag, "sharemodel depth updated $input")
                }
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="flow" || calc == "qva")
                    calculate()
            }
        })
        inputDiameterEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) { calculate() }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }
                    Log.i(thetag, "diam changed")
                    sharedViewModel.diameter.postValue(input)
                }
                calculate()
            }
        })
        inputVelocityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="qva")
                    calculate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }

                    sharedViewModel.velocity.postValue(input)
                }
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="qva")
                    calculate()
            }
        })
        inputSlopeEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "flow")
                    calculate()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }

                    sharedViewModel.slope.postValue(input)
                }
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "flow")
                    calculate()
            }
        })
        inputNValueEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "flow")
                    calculate()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(txt: CharSequence?, p1: Int, p2: Int, p3: Int) {
                txt?.let {
                    var input = 0.0
                    if (txt.toString().isNotEmpty()) {
                        input = txt.toString().toDouble()
                    }
                    when (input){
                        0.011 -> inputNValueListSpinner.setSelection(1)
                        0.013 -> inputNValueListSpinner.setSelection(2)
                        0.014 -> inputNValueListSpinner.setSelection(3)
                        else -> inputNValueListSpinner.setSelection(0)
                    }

                    sharedViewModel.nvalue.postValue(input)
                }
                val calc = sharedViewModel.calculate.value.toString()
                if (calc =="depth" || calc == "flow")
                    calculate()
            }
        })

        inputFlowUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { calculate() }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    var input = ""
                    if (selectedItem.isNotEmpty()) {
                        input = selectedItem
                    }
                    Log.i(thetag, "flow unit is $input")
                    sharedViewModel.flowUnits.postValue(input)
                }
                calculate()
            }
        }
        inputDepthUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { calculate() }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    var input = ""
                    if (selectedItem.isNotEmpty()) {
                        input = selectedItem
                    }
                    sharedViewModel.depthUnits.postValue(input)
                }
                calculate()
            }
        }
        inputVelocityUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { calculate() }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    var input = ""
                    if (selectedItem.isNotEmpty()) {
                        input = selectedItem
                    }
                    sharedViewModel.velocityUnits.postValue(input)
                }
                calculate()
            }
        }
        inputDiameterUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { calculate() }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    var input = ""
                    if (selectedItem.isNotEmpty()) {
                        input = selectedItem
                    }
                    sharedViewModel.diameterUnits.postValue(input)
                }
                calculate()
            }
        }
        inputSlopeUnitSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    var input = ""
                    if (selectedItem.isNotEmpty()) {
                        input = selectedItem
                    }
                    sharedViewModel.slopeUnits.postValue(input)
                }
                calculate()
            }
        }
        inputNValueListSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                selectedItem.let {
                    val input:String
                    if (selectedItem.isNotEmpty()) {
                        Log.i(thetag, "nvalue select $selectedItem")
                        input = selectedItem
                        when (input) {
                            "Concrete" -> inputNValueEditText.setText("0.013")
                            "PVC" -> inputNValueEditText.setText("0.011")
                            "Vitrified Clay" -> inputNValueEditText.setText("0.014")
                        }
                    }
                }
            }
        }
    }
    private fun calculate() {
        val newPipe = Pipe()
        val conversion = UnitConversion()

        //Get data from View
        val dblFlow = sharedViewModel.flow.value.toString().toDouble()
        val depth = sharedViewModel.depth.value.toString().toDouble()
        val diameter = sharedViewModel.diameter.value.toString().toDouble()
        val velocity = sharedViewModel.velocity.value.toString().toDouble()
        val slope = sharedViewModel.slope.value.toString().toDouble()
        val nValue = sharedViewModel.nvalue.value.toString().toDouble()

        val flowUnit = sharedViewModel.flowUnits.value.toString()
        val diameterUnit = sharedViewModel.diameterUnits.value.toString()
        val velocityUnit = sharedViewModel.velocityUnits.value.toString()
        val depthUnit = sharedViewModel.depthUnits.value.toString()
        val slopeUnit = sharedViewModel.slopeUnits.value.toString()

        val calcType = sharedViewModel.calculate.value.toString()
        Log.i(thetag, "got values")
        try {
            //Determine which function to run and convert units as needed
            if (calcType == "flow")
            //Calculate Flow
            {
                Log.i(thetag, "calculate flow $depth $diameter $slope $nValue")
                if (depth == 0.0 || diameter == 0.0 || slope == 0.0 || nValue == 0.0) {
                    return
                }
                newPipe.setDepth(depth)
                newPipe.setDiameter(diameter)
                newPipe.setSlope(slope)
                newPipe.setNValue(nValue)

                //Convert units to cfs, feet, fps, and ft/ft as needed
                newPipe.setDepth(newPipe.getDepth() * conversion.GetFactor(depthUnit, "ft"))
                newPipe.setDiameter(
                    newPipe.getDiameter() * conversion.GetFactor(
                        diameterUnit,
                        "ft"
                    )
                )
                newPipe.setSlope(newPipe.getSlope() * conversion.GetFactor(slopeUnit, "ft/ft"))

                newPipe.QManning()

                //Convert units as needed
                newPipe.setFlow(newPipe.getFlow() * conversion.GetFactor("cfs", flowUnit))
                newPipe.setVelocity(
                    newPipe.getVelocity() * conversion.GetFactor(
                        "fps",
                        velocityUnit
                    )
                )
                Log.i(thetag, "now update value ${newPipe.getFlow()}")
                //Update form
                inputFlowEditText.setText(String.format("%.4f",newPipe.getFlow()))
                inputVelocityEditText.setText(String.format("%.4f",newPipe.getVelocity()))
                sharedViewModel.flow.postValue(newPipe.getFlow())
                sharedViewModel.velocity.postValue(newPipe.getVelocity())
                //Update Details
                sharedViewModel.dD.postValue(newPipe.getdD())
                sharedViewModel.qCap.postValue(newPipe.getqCAP())
                sharedViewModel.cap.postValue(newPipe.getCap())
                sharedViewModel.velHead.postValue(newPipe.getVelHead())
                sharedViewModel.wA.postValue(newPipe.getWetArea())
                sharedViewModel.wP.postValue(newPipe.getWetPerimiter())
                sharedViewModel.hydRadius.postValue(newPipe.getHydRadius())
            } else if (calcType === "qva") {
                if (depth == 0.0 || diameter == 0.0 || velocity == 0.0)
                    return

                newPipe.setDepth(depth)
                newPipe.setDiameter(diameter)
                newPipe.setVelocity(velocity)

                //Convert units to cfs, feet, fps, and ft/ft as needed
                newPipe.setDepth(newPipe.getDepth() * conversion.GetFactor(depthUnit, "ft"))
                newPipe.setDiameter(
                    newPipe.getDiameter() * conversion.GetFactor(
                        diameterUnit,
                        "ft"
                    )
                )
                newPipe.setVelocity(
                    newPipe.getVelocity() * conversion.GetFactor(
                        velocityUnit,
                        "fps"
                    )
                )

                newPipe.QVA()

                //Convert units as needed
                newPipe.setFlow(newPipe.getFlow() * conversion.GetFactor("cfs", flowUnit))

                //Update view
                inputFlowEditText.setText(String.format("%.4f",newPipe.getFlow()))
                sharedViewModel.flow.postValue(newPipe.getFlow())
                //Update Details
                sharedViewModel.dD.postValue(newPipe.getdD())
                sharedViewModel.qCap.postValue(newPipe.getqCAP())
                sharedViewModel.cap.postValue(newPipe.getCap())
                sharedViewModel.velHead.postValue(newPipe.getVelHead())
                sharedViewModel.wA.postValue(newPipe.getWetArea())
                sharedViewModel.wP.postValue(newPipe.getWetPerimiter())
            } else if (calcType == "depth")
            //Calculate Depth
            {
                if (dblFlow == 0.0 || diameter == 0.0 || slope == 0.0 || nValue == 0.0)
                    return

                newPipe.setFlow(dblFlow)
                newPipe.setDiameter(diameter)
                newPipe.setSlope(slope)
                newPipe.setNValue(nValue)

                //Convert units to cfs, feet, fps, and ft/ft as needed
                newPipe.setFlow(newPipe.getFlow() * conversion.GetFactor(flowUnit, "cfs"))
                newPipe.setDiameter(
                    newPipe.getDiameter() * conversion.GetFactor(
                        diameterUnit,
                        "ft"
                    )
                )
                newPipe.setSlope(newPipe.getSlope() * conversion.GetFactor(slopeUnit, "ft/ft"))

                newPipe.DManning()

                //Convert units as needed
                newPipe.setDepth(newPipe.getDepth() * conversion.GetFactor("ft", depthUnit))
                newPipe.setVelocity(
                    newPipe.getVelocity() * conversion.GetFactor(
                        "fps",
                        velocityUnit
                    )
                )

                //Update view
                inputDepthEditText.setText(String.format("%.4f",newPipe.getDepth()))
                inputVelocityEditText.setText(String.format("%.4f",newPipe.getVelocity()))
                sharedViewModel.depth.postValue(newPipe.getDepth())
                sharedViewModel.velocity.postValue(newPipe.getVelocity())
                //Update Details
                sharedViewModel.dD.postValue(newPipe.getdD())
                sharedViewModel.qCap.postValue(newPipe.getqCAP())
                sharedViewModel.cap.postValue(newPipe.getCap())
                sharedViewModel.velHead.postValue(newPipe.getVelHead())
                sharedViewModel.wA.postValue(newPipe.getWetArea())
                sharedViewModel.wP.postValue(newPipe.getWetPerimiter())
            } else if (calcType == "vqa")
            //Calculate velocity
            {
                if (depth == 0.0 || diameter == 0.0 || dblFlow == 0.0)
                    return

                newPipe.setFlow(dblFlow)
                newPipe.setDepth(depth)
                newPipe.setDiameter(diameter)

                //Convert units to cfs, feet, fps, and ft/ft as needed
                newPipe.setFlow(newPipe.getFlow() * conversion.GetFactor(flowUnit, "cfs"))
                newPipe.setDepth(newPipe.getDepth() * conversion.GetFactor(depthUnit, "ft"))
                newPipe.setDiameter(
                    newPipe.getDiameter() * conversion.GetFactor(
                        diameterUnit,
                        "ft"
                    )
                )

                newPipe.VQA()

                //Convert units as needed
                newPipe.setVelocity(
                    newPipe.getVelocity() * conversion.GetFactor(
                        "fps",
                        velocityUnit
                    )
                )

                //Update view
                inputVelocityEditText.setText(String.format("%.4f",newPipe.getVelocity()))
                sharedViewModel.velocity.postValue(newPipe.getVelocity())
                //Update Details
                sharedViewModel.dD.postValue(newPipe.getdD())
                sharedViewModel.qCap.postValue(newPipe.getqCAP())
                sharedViewModel.cap.postValue(newPipe.getCap())
                sharedViewModel.velHead.postValue(newPipe.getVelHead())
                sharedViewModel.wA.postValue(newPipe.getWetArea())
                sharedViewModel.wP.postValue(newPipe.getWetPerimiter())
            }

            //mCallback.onPipeUpdated(newPipe)
        } finally {

        }
    }
}