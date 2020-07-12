package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation)}

    //Variables to hold operands and type of calculation
    private var operand1: String = ""
    private var operand2: String = ""
    private var pendingOperation = "="
    private var dec: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.newNumber)

        //Data input numbers
        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDec: Button = findViewById(R.id.buttonDec)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        //Operation buttons
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonPlus: Button = findViewById(R.id.buttonPlus)
        val buttonMinus: Button = findViewById(R.id.buttonMinus)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)

        //Listener
        val listener = View.OnClickListener { v ->

            //TODO: Clean up button creation/make function to make code more reusable
            val b = v as Button
            if(b.text == "C"){
                newNumber.text.clear()
                result.text = ""
                operand1 = ""
                operand2 = ""
                if(dec == true){
                    dec = false
                }
            }
            else if(b.text == ".") {
                if (dec == false) {
                    dec = true
                    newNumber.append(b.text)
                }
                else{
                    return@OnClickListener
                }
            }
            else {
                newNumber.append(b.text)
            }

        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDec.setOnClickListener(listener)
        buttonClear.setOnClickListener(listener)

        val opListener = View.OnClickListener { v ->
            val op = (v as Button).text.toString()
            val value = newNumber.text.toString()


            if (value.isNotEmpty()){
                performOperation(value, op)
            }

            pendingOperation = op
            displayOperation.text = pendingOperation
        }

        buttonEquals.setOnClickListener(opListener)
        buttonDivide.setOnClickListener(opListener)
        buttonMinus.setOnClickListener(opListener)
        buttonMultiply.setOnClickListener(opListener)
        buttonPlus.setOnClickListener(opListener)
    }

    private fun performOperation(value: String, operation: String){
        newNumber.text.clear()

        if(operand1.isEmpty()) {
            operand1 = value
            result.text = operand1
            if(operation == "="){
                operand1 = ""
                operand2 = ""
                pendingOperation = ""

                //TODO: Fix to clear, to improve looks not fucntionality
                //displayOperation.text.clear()
            }

        }
        else{
            operand2 = value

            when(pendingOperation){
                "+" -> {
                    result.text = (operand1.toDouble() + operand2.toDouble()).toString()
                    operand1 = (operand1.toDouble() + operand2.toDouble()).toString()
                    operand2 = ""
                }
                "-" -> {
                    result.text = (operand1.toDouble() - operand2.toDouble()).toString()
                    operand1 = (operand1.toDouble() - operand2.toDouble()).toString()
                    operand2 = ""
                }
                "*" -> {
                    result.text = (operand1.toDouble() * operand2.toDouble()).toString()
                    operand1 = (operand1.toDouble() * operand2.toDouble()).toString()
                    operand2 = ""
                }
                else -> {
                    if(operand2.toDouble() != 0.0){
                        result.text = (operand1.toDouble() / operand2.toDouble()).toString()
                        operand1 = (operand1.toDouble() / operand2.toDouble()).toString()
                        operand2 = ""
                    }
                    else{
                        result.text = "Error"
                        operand1 = ""
                        operand2 = ""
                        pendingOperation = ""
                    }
                }
            }


            if(operation == "="){
                operand1 = ""
                operand2 = ""
                pendingOperation = ""
            }
        }
    }
}