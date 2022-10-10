package com.example.currencyconverter.manager

import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FirebaseManager {
    private val database = Firebase.database
    private var onUpdateCountDownTimerListener: ((timer: Long?) -> Unit)? = null
    private var onUpdateCurrenciesListener: ((currencies: List<String>?) -> Unit)? = null

    init {
        getCountDownTimer()
        getCurrencies()
    }

    private fun getCountDownTimer() {
        database.getReference(KEY_TIMER).get().addOnSuccessListener {
            onUpdateCountDownTimerListener?.invoke(it.value as? Long)
        }.addOnFailureListener {
            onUpdateCountDownTimerListener?.invoke(null)
            Log.e(TAG, "Error getting count down timer", it)
        }
    }

    private fun getCurrencies() {
        database.getReference(KEY_CURRENCIES).get().addOnSuccessListener {
            onUpdateCurrenciesListener?.invoke(it.value as? List<String>)
        }.addOnFailureListener {
            onUpdateCurrenciesListener?.invoke(null)
            Log.e(TAG, "Error getting currencies", it)
        }
    }

    fun setOnUpdateCountDownTimerListener(listener: ((timer: Long?) -> Unit)) {
        onUpdateCountDownTimerListener = listener
    }

    fun setOnUpdateCurrenciesListener(listener: ((currencies: List<String>?) -> Unit)) {
        onUpdateCurrenciesListener = listener
    }

    companion object {
        const val KEY_TIMER = "timer"
        const val KEY_CURRENCIES = "currencies"
        val TAG: String = FirebaseManager::class.java.simpleName
    }
}