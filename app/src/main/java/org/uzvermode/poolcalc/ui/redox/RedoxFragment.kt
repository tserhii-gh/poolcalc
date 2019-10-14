package org.uzvermode.poolcalc.ui.redox

import android.content.Context
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_redox.*
import org.uzvermode.poolcalc.R
import org.uzvermode.poolcalc.data.ClPhTable

class RedoxFragment : Fragment() {
    private lateinit var redoxViewModel: RedoxViewModel
    private var REDOX = 660
    private var PH: Double = 7.4
    private var clPhTable = ClPhTable()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        redoxViewModel =
            ViewModelProviders.of(this).get(RedoxViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_redox, container, false)
        val textPh: TextView = root.findViewById(R.id.text_ph)
        val textRedox: TextView = root.findViewById(R.id.text_redox)
        val textChlorine: TextView = root.findViewById(R.id.text_chlorine)
        textChlorine.text = clPhTable.CLlevel(REDOX, PH)
        val phSeekBar: SeekBar = root.findViewById(R.id.ph_level)
        val rxSeekBar: SeekBar = root.findViewById(R.id.rx_level)

        phSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textPh.text = getConvertedPhValue(progress).toString()
                textChlorine.text = clPhTable.CLlevel(REDOX, PH)

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        rxSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textRedox.text = getConvertedRxValue(progress).toString()
                textChlorine.text = clPhTable.CLlevel(REDOX, PH)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        textPh.text = getConvertedPhValue(phSeekBar.progress)
//        context?.let {
//            textPh.setShadowLayer(9.0f,0.0f,0.0f, ContextCompat.getColor(it ,R.color.colorPh))
//            textRedox.setShadowLayer(9.0f,0.0f,0.0f, ContextCompat.getColor(it ,R.color.colorRx))
//            textChlorine.setShadowLayer(9.0f,0.0f,0.0f, ContextCompat.getColor(it ,R.color.colorCl))
//            textPh.setShadowLayer(9.0f,0.0f,0.0f, ContextCompat.getColor(it ,R.color.colorCl))
//        }
//        textPh.setShadowLayer(10.0f,5.0f,5.0f, Color.BLUE)
        textRedox.text = getConvertedRxValue(rxSeekBar.progress)

        redoxViewModel.text.observe(this, Observer {
//            textView.text = it
            Log.e("ViewModel", it)
        })

        return root
    }

    private fun getConvertedPhValue(intVal: Int): String {
        val f: DecimalFormat = DecimalFormat("#.#")
        val pHValue: Double = 6.9 + 0.1 * intVal;
//        Log.e("pH Value", f.format(pHValue))
        if (pHValue == 7.1) {
            PH = 7.2
            return "7.2"

        } else {
            PH = f.format(pHValue).toDouble()
            return f.format(pHValue)
        }
    }

    private fun getConvertedRxValue(intVal: Int): String {
        REDOX = 480 + intVal
        return REDOX.toString()
    }
}