package com.example.currencyconverter.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.R

class ApprovalDialog : DialogFragment() {

    private val args: ApprovalDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_approval, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
    }

    private fun setupViews(view: View) {
        val btnApprove = view.findViewById<Button>(R.id.btn_approve)

        btnApprove?.setOnClickListener {
            val action = ApprovalDialogDirections.actionApprovalDialogToSuccessFragment(
                targetValue = args.targetValue, conversionRate = args.conversionRate
            )
            findNavController().navigate(action)
        }

        val tvApprovalMessage = view.findViewById<TextView>(R.id.tv_approval_message)
        tvApprovalMessage.text =
            getString(R.string.approval_message, args.targetValue, args.currentValue)
    }
}