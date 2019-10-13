package org.uzvermode.poolcalc.ui.ph

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PhViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is pH Fragment"
    }
    val text: LiveData<String> = _text
}