package com.example.caculatoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calculatorappkotlin.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private var isNewOp = true
    private var oldNum = EMPTY
    private var newNum = ""
    private var op = ""
    private var result = 0.0
    private var input = ""
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun clear(view: View) {
        binding.editTextDisplay.setText(EMPTY)
    }

    fun clickNumber(view: View) {
        if (isNewOp) {
            binding.editTextDisplay.setText(EMPTY)
        }
        isNewOp = false
        if (view is MaterialButton)
            displayToInput(view.text.toString())
    }

    fun nagativeEvent(view: View) {
        input = binding.editTextDisplay.text.toString()
        if (view is MaterialButton) {
            if (view == binding.buttonNegativeinteger) {
                input = "-$input"
            }
            binding.editTextDisplay.setText(input)
        }
    }

    fun percentEvent(view: View) {
        var percent = binding.editTextDisplay.text.toString().toDouble() / 100
        binding.editTextDisplay.setText(percent.toString())
        isNewOp = true
    }

    fun operatorEvent(view: View) {
        isNewOp = true
        oldNum = binding.editTextDisplay.text.toString()
        if (view is MaterialButton) {
            binding.apply {
                op = when (view) {
                    buttonAddition -> ADD
                    buttonMultiply -> MUL
                    buttonSubtraction -> SUB
                    buttonDivision -> DIV
                    else -> op
                }
            }
        }
    }

    fun displayToInput(displayInput: String) {
        binding.editTextDisplay.text.append(displayInput)
    }

    fun showResult(view: View) {
        newNum = binding.editTextDisplay.text.toString()
        result = when (op) {
            ADD -> oldNum.toDouble() + newNum.toDouble()
            SUB -> oldNum.toDouble() - newNum.toDouble()
            MUL -> oldNum.toDouble() * newNum.toDouble()
            DIV -> oldNum.toDouble() / newNum.toDouble()
            else -> result
        }
        binding.editTextDisplay.setText(result.toString())
    }

    companion object {
        const val EMPTY = ""
        const val ADD = "+"
        const val SUB = "-"
        const val MUL = "*"
        const val DIV = "/"
    }

}
