package com.example.currencyconverter.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R

class ApprovalDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_approval, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        val btnApprove = view.findViewById<Button>(R.id.btn_approve)

        btnApprove?.setOnClickListener {
            val action = ApprovalDialogDirections.actionApprovalDialogToSuccessFragment()
            findNavController().navigate(action)
        }
    }
}