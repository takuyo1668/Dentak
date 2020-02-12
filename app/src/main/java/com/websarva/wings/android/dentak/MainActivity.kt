package com.websarva.wings.android.dentak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.sign

class MainActivity : AppCompatActivity() {
    var nStr : String = ""
    val nList = ArrayList<Double>() // arraylist to store numbers
    val oList = ArrayList<Char>() // arraylist to store operations

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt0.setOnClickListener {
            formula.text = "${formula.text}0"
            nStr += "0"
        }
        bt1.setOnClickListener {
            formula.text = "${formula.text}1"
            nStr += "1"
        }
        bt2.setOnClickListener {
            formula.text = "${formula.text}2"
            nStr += "2"
        }
        bt3.setOnClickListener {
            formula.text = "${formula.text}3"
            nStr += "3"
        }
        bt4.setOnClickListener {
            formula.text = "${formula.text}4"
            nStr += "4"
        }
        bt5.setOnClickListener {
            formula.text = "${formula.text}5"
            nStr += "5"
        }
        bt6.setOnClickListener {
            formula.text = "${formula.text}6"
            nStr += "6"
        }
        bt7.setOnClickListener {
            formula.text = "${formula.text}7"
            nStr += "7"
        }
        bt8.setOnClickListener {
            formula.text = "${formula.text}8"
            nStr += "8"
        }
        bt9.setOnClickListener {
            formula.text = "${formula.text}9"
            nStr += "9"
        }
        period.setOnClickListener {
            formula.text = "${formula.text}."
            nStr += "."
        }
        equal.setOnClickListener {
            formula.text = "${formula.text}="
            addList(nStr)
            var result = calcualte().toString()
            formula.text = result
            nStr = result
            nList.clear()
            oList.clear()
        }
        plus.setOnClickListener {
            formula.text = "${formula.text}+"
            addList(nStr,'+')
            nStr = ""
        }
        minus.setOnClickListener {
            formula.text = "${formula.text}-"
            addList(nStr,'-')
            nStr = ""
        }
        Multiplication.setOnClickListener {
            formula.text = "${formula.text}*"
            addList(nStr,'*')
            nStr = ""
        }
        division.setOnClickListener {
            formula.text = "${formula.text}/"
            addList(nStr,'/')
            nStr = ""
        }
        delete.setOnClickListener {
            var formulaStr = formula.text.toString()
            if (!formulaStr.isEmpty()) {
                formula.text = formulaStr.subSequence(0,formulaStr.lastIndex)
            }
            if (!nStr.isEmpty()) {
                nStr = nStr.substring(0, nStr.lastIndex)
            }
        }

        clear.setOnClickListener {
            formula.text = ""
            nStr = ""
            nList.clear()
            oList.clear()
        }

    } // end fun onCreate

    fun addList(str : String, ope : Char) {
        try {
            var num = str.toDouble()
            nList.add(num)
            oList.add(ope)
        }catch(e:Exception){
            formula.text = "Numeric error"
        }
    }

    fun addList(str : String) {
        try {
            var num = str.toDouble()
            nList.add(num)
        }catch(e:Exception){
            formula.text = "Numeric error"
        }
    }

    fun calcualte() : Double {

        var i = 0
        while (i < oList.size) {
            //do multiplication and division first
            if(oList.get(i) == '*' || oList.get(i) == '/') {
                var result = if (oList.get(i) == '*') nList.get(i) * nList.get(i+1) else nList.get(i) / nList.get(i+1)
                nList.set(i,result)
                nList.removeAt(i+1)
                oList.removeAt(i)
                i--
            }
            // change subtraction to addition
            else if(oList.get(i) == '-'){
                oList.set(i,'+')
                nList.set(i+1,nList.get(i+1) * -1)
            }
            i++
        }

        // get sum
        var result = 0.0
        for (i in nList){
            result += i
        }

        return result
    }// end fun calcualte

} // end class