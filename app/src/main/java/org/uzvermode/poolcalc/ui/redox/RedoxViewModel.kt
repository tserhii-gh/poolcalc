package org.uzvermode.poolcalc.ui.redox

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RedoxViewModel : ViewModel() {

    private val TAG = "RedoxViewModel"



    private val chlorine = doubleArrayOf(
        0.2,
        0.3,
        0.4,
        0.5,
        0.6,
        0.65,
        0.67,
        0.75,
        0.8,
        0.85,
        0.9,
        0.95,
        1.0,
        1.1,
        1.2,
        1.3,
        1.4,
        1.5,
        1.7,
        1.8,
        2.0,
        2.2,
        2.5
    )
//    private val phLevel = doubleArrayOf(6.9, 7.0, 7.2, 7.3, 7.4, 7.5, 7.6, 7.7, 7.8, 7.9, 8.0, 8.1, 8.2, 8.3)
    private val phLv = arrayOf("6.9", "7.0", "7.2", "7.3", "7.4", "7.5", "7.6", "7.7", "7.8", "7.9", "8.0", "8.1", "8.2", "8.3")

    private val table: Array<IntArray> = arrayOf(
        intArrayOf(507,561,599,629,652,663,673,682,690,698,706,713,720,733,744,755,765,774,790,798,812,824,841), // 6.9
        intArrayOf(505,558,596,625,648,658,669,677,686,694,702,708,715,727,739,749,759,768,784,792,805,818,834), // 7.0
        intArrayOf(502,553,590,618,640,650,660,668,677,684,692,698,705,717,728,738,747,756,771,779,792,804,826), // 7.2
        intArrayOf(500,550,586,615,637,646,656,664,672,680,687,694,700,712,722,732,742,750,765,773,785,797,813), // 7.3
        intArrayOf(499,548,583,611,632,642,651,660,668,675,682,689,695,707,717,727,736,744,759,766,779,791,806), // 7.4
        intArrayOf(497,546,580,607,629,638,647,655,663,670,677,684,690,701,712,721,730,738,753,760,773,784,800), // 7.5
        intArrayOf(496,544,577,604,625,634,643,651,659,666,673,679,685,696,706,716,724,732,747,754,766,777,792), // 7.6
        intArrayOf(494,541,574,600,621,630,639,647,654,661,668,674,680,691,701,710,719,727,741,748,760,771,785), // 7.7
        intArrayOf(493,539,571,597,617,626,635,642,650,657,663,669,675,696,695,705,713,721,735,741,753,764,778), // 7.8
        intArrayOf(491,536,568,593,613,622,630,638,645,652,658,664,670,680,690,699,707,715,728,735,747,757,771), // 7.9
        intArrayOf(490,534,565,590,610,618,626,634,641,647,654,659,665,675,685,694,702,709,722,729,740,751,764), // 8.0
        intArrayOf(488,532,562,586,605,614,622,629,636,643,649,654,660,670,679,688,696,703,716,722,734,744,757), // 8.1
        intArrayOf(487,529,559,583,602,610,618,625,632,638,644,650,655,665,674,682,690,697,710,716,727,737,751), // 8.2
        intArrayOf(486,527,556,580,598,606,614,620,627,633,639,645,650,660,669,677,684,691,704,710,721,730,744) // 8.3
    )

    private val resultPh = MutableLiveData<String>()
    private val resultRedoxIdx = MutableLiveData<Int>()
    private val resultRedoxStr = MutableLiveData<String>()
    private val resultChlorine = MutableLiveData<String>()
//    private val maxRedox = MutableLiveData<Int>()
//    private var lastValue = "---"


    fun getActiveChlorine(): LiveData<String> {
        val REDOX = resultRedoxIdx.value
        resultChlorine.value = chlorine[REDOX!!].toString()
        return resultChlorine
    }

    fun getPh(): String? {
        return resultPh.value
    }


    fun setPh(intVal: Int):LiveData<String> {
        resultPh.value = phLv[intVal]
        return resultPh
    }


    fun setRedox(intVal: Int): LiveData<String> {
        val PH = resultPh.value
        val redoxRow = table[phLv.indexOf(PH)]
        resultRedoxIdx.value = intVal
        resultRedoxStr.value = redoxRow[resultRedoxIdx.value!!].toString()
        return resultRedoxStr
    }


    fun getRedox(): String? {
        val PH = resultPh.value
        val redoxRow = table[phLv.indexOf(PH)]
        resultRedoxStr.value = redoxRow[resultRedoxIdx.value!!].toString()
        return resultRedoxStr.value
    }
}