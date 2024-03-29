package org.uzvermode.poolcalc.ui.redox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.uzvermode.poolcalc.R

class RedoxFragment : Fragment() {
    private lateinit var redoxViewModel: RedoxViewModel
    private val PH = 0 // 6.9
    private val REDOX = 0 // 507

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
        val phSeekBar: SeekBar = root.findViewById(R.id.ph_level)
        val rxSeekBar: SeekBar = root.findViewById(R.id.rx_level)

        initUnits(textPh, textRedox, textChlorine, phSeekBar, rxSeekBar)

        phSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                redoxViewModel.setPh(progress).observe(viewLifecycleOwner, Observer {
                                        textPh.text = it
                })
                redoxViewModel.setRedox(0)
                rxSeekBar.progress = 0
                redoxViewModel.getActiveChlorine().observe(viewLifecycleOwner, Observer {
                    textChlorine.text = it
                })
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        rxSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                redoxViewModel.setRedox(progress).observe(viewLifecycleOwner, Observer {
                                        textRedox.text = it.toString()
                })
                redoxViewModel.getActiveChlorine().observe(viewLifecycleOwner, Observer {
                    textChlorine.text = it
                })
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        return root
    }

    // Init app with default values
    private fun initUnits(tph: TextView, trx: TextView, tcl: TextView, phsb: SeekBar, rxsb: SeekBar){
        redoxViewModel.setPh(PH)
        redoxViewModel.setRedox(REDOX)
        tph.text = redoxViewModel.getPh()
        trx.text = redoxViewModel.getRedox()

        redoxViewModel.getActiveChlorine().observe(this, Observer {
            tcl.text = it
        })
        phsb.progress = PH
        rxsb.progress = REDOX
    }
}