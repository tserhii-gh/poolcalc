package org.uzvermode.poolcalc.ui.chlorine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChlorineViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Chlorine Fragment not implemented!"
    }
    val text: LiveData<String> = _text
}