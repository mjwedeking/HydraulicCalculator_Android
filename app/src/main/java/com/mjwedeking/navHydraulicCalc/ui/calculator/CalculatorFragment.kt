package com.mjwedeking.navHydraulicCalc.ui.calculator

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mjwedeking.navHydraulicCalc.R

class CalculatorFragment : Fragment() {

    lateinit var toolbar: ActionBar

    private lateinit var homeViewModel: CalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

       // homeViewModel =
       //     ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calculator, container, false)


        return root
    }
}