package com.kemalurekli.percentagecalculator.viewmodel

import androidx.lifecycle.ViewModel

class HomeFragmentViewModel : ViewModel() {

    fun calculatePercentage(inputNumber : Double, inputSeekBar : Int ) : Double {
        return (inputSeekBar*inputNumber)/100
    }
    fun calculateResult(inputNumber : Double, inputSeekBar : Int ) : Double {
        return inputNumber + (inputSeekBar*inputNumber)/100
    }
}