package org.uzvermode.poolcalc.ui.redox

import android.util.Log
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RedoxViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Redox Fragment"
    }
    val text: LiveData<String> = _text


    private fun getConvertedValue(intVal :Int ): Float {
        var floatValue: Float = 0.0F;
        floatValue = .5F * intVal;
        return floatValue;
    }
}