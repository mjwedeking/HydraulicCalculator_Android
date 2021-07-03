package com.mjwedeking.navHydraulicCalc.ui.calculator

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProviders
import android.view.View
import android.view.ViewGroup
import com.mjwedeking.navHydraulicCalc.R
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         *  create view model in activity scope
         */
        activity?.let {
            val sharedViewModel = ViewModelProviders.of(it).get(SharedViewModel::class.java)

            observeInput(sharedViewModel)
        }

    }
    private fun observeInput(sharedViewModel: SharedViewModel) {
        sharedViewModel.dD.observe(this, Observer {
            it?.let {
                inputdDEditText.setText(it.toString())
            }
        })
        sharedViewModel.qCap.observe(this, Observer {
            it?.let {
                inputQCapEditText.setText(it.toString())
            }
        })
        sharedViewModel.cap.observe(this, Observer {
            it?.let {
                inputCapacityEditText.setText(it.toString())
            }
        })
        sharedViewModel.velHead.observe(this, Observer {
            it?.let {
                inputVelHeadEditText.setText(it.toString())
            }
        })
        sharedViewModel.wA.observe(this, Observer {
            it?.let {
                inputFlowAreaEditText.setText(it.toString())
            }
        })
        sharedViewModel.wP.observe(this, Observer {
            it?.let {
                inputWetPerimiterEditText.setText(it.toString())
            }
        })
        sharedViewModel.hydRadius.observe(this, Observer {
            it?.let {
                inputHydRadiusEditText.setText(it.toString())
            }
        })
    }

}