package com.dst.basiccalculator

import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.dst.basiccalculator.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var operator: String = ""
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var reset = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.buttonOne.setOnClickListener(this)
        binding.buttonTwo.setOnClickListener(this)
        binding.buttonThree.setOnClickListener(this)
        binding.buttonFour.setOnClickListener(this)
        binding.buttonFive.setOnClickListener(this)
        binding.buttonSix.setOnClickListener(this)
        binding.buttonSeven.setOnClickListener(this)
        binding.buttonEight.setOnClickListener(this)
        binding.buttonNine.setOnClickListener(this)
        binding.buttonZero.setOnClickListener(this)
        binding.buttonAdd.setOnClickListener(this)
        binding.buttonSubtract.setOnClickListener(this)
        binding.buttonMultiply.setOnClickListener(this)
        binding.buttonDivide.setOnClickListener(this)
        binding.buttonDot.setOnClickListener(this)
        binding.buttonEquals.setOnClickListener(this)
        binding.buttonClear.setOnClickListener(this)
        binding.buttonBackspace.setOnClickListener(this)
        binding.buttonChangeSign.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_one -> addDigit(1)
            R.id.button_two -> addDigit(2)
            R.id.button_three -> addDigit(3)
            R.id.button_four -> addDigit(4)
            R.id.button_five -> addDigit(5)
            R.id.button_six -> addDigit(6)
            R.id.button_seven -> addDigit(7)
            R.id.button_eight -> addDigit(8)
            R.id.button_nine -> addDigit(9)
            R.id.button_zero -> addDigit(0)
            R.id.button_add -> setOperation("+")
            R.id.button_subtract -> setOperation("-")
            R.id.button_multiply -> setOperation("*")
            R.id.button_divide -> setOperation("/")
            R.id.button_dot -> addDotSymbol()
            R.id.button_equals -> calculateResult()
            R.id.button_clear -> clear()
            R.id.button_backspace -> backspace()
            R.id.button_change_sign -> changeNumberSign()
        }
    }

    private fun changeNumberSign() {
        val number = StringBuilder(binding.result.text)

        if (number.contains("-")) {
            number.deleteCharAt(0)
        } else {
            number.insert(0, "-")
        }
        binding.result.text = number
    }

    private fun calculateResult() {
        if (binding.result.text.equals("Error") || binding.result.text.equals("Division by zero")) clear()
        var result = 0.0
        secondNumber = binding.result.text.toString().toDouble()
        binding.operator.text = ""
        when (operator) {
            "*" -> result = firstNumber * secondNumber
            "/" -> {
                if (firstNumber == 0.0 && secondNumber == 0.0){
                    binding.result.text = getString(R.string.error)
                    return
                }
                if (secondNumber == 0.0) {
                    binding.result.text = getString(R.string.division_by_zero)
                    return
                }
                result = firstNumber / secondNumber
            }
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
        }
        val df = DecimalFormat("#.##########")
        binding.result.text = getString(R.string.result, df.format(result))
    }

    //Reset first number when operation button is clicked
    private fun reset(initialDigit: String) {
        reset = false
        binding.result.text = ""
        binding.result.text = initialDigit
    }

    private fun clear() {
        firstNumber = 0.00
        secondNumber = 0.00
        binding.operator.text = ""
        binding.result.text = "0"
    }

    private fun addDigit(digit: Int) {
        if (binding.result.text.equals("Error") || binding.result.text.equals("Division by zero")) clear()
        val result = StringBuilder(binding.result.text)
        if (binding.result.text.toString().toDouble() == 0.0 && !binding.result.text.contains(".")) result.clear()
        result.append(digit.toString())
        if (reset) {
            reset(digit.toString())
        } else {
            binding.result.text = result.toString()
        }
    }

    private fun addDotSymbol() {
        val result = StringBuilder(binding.result.text)
        if (!binding.result.text.contains(".")) {
            result.append(".")
            binding.result.text = result.toString()
        }
    }

    private fun setOperation(newOperator: String) {
        binding.operator.text = newOperator
        operator = newOperator
        firstNumber = binding.result.text.toString().toDouble()
        reset = true
    }

    private fun backspace() {
        if (binding.result.text.length == 1) binding.result.text = "0"
        else if (binding.result.text.toString().toDouble() != 0.0 && binding.result.text.isNotEmpty()) {
            val number = StringBuilder(binding.result.text)
            number.deleteCharAt(number.length - 1)
            binding.result.text = number
        }
    }
}