package com.dst.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.dst.basiccalculator.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private var operator : String = ""
    private var firstNumber : Double = 0.0
    private var secondNumber : Double = 0.0
    private val TAG = "MainActivity"
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
    }

    override fun onClick(view: View) {
        val result = StringBuilder(binding.result.text)
        when (view.id) {
            R.id.button_one -> {
                result.append("1")
                binding.result.text = result.toString()
            }
            R.id.button_two -> {
                result.append("2")
                binding.result.text = result.toString()
            }
            R.id.button_three -> {
                result.append("3")
                binding.result.text = result.toString()
            }
            R.id.button_four -> {
                result.append("4")
                binding.result.text = result.toString()
            }
            R.id.button_five -> {
                result.append("5")
                binding.result.text = result.toString()
            }
            R.id.button_six -> {
                result.append("6")
                binding.result.text = result.toString()
            }
            R.id.button_seven -> {
                result.append("7")
                binding.result.text = result.toString()
            }
            R.id.button_eight -> {
                result.append("8")
                binding.result.text = result.toString()
            }
            R.id.button_nine -> {
                result.append("9")
                binding.result.text = result.toString()
            }
            R.id.button_zero -> {
                result.append("0")
                binding.result.text = result.toString()
            }
            R.id.button_add -> {
                operator = "+"
                firstNumber = binding.result.text.toString().toDouble()
                binding.result.text = ""
            }
            R.id.button_subtract -> {
                operator = "-"
                firstNumber = binding.result.text.toString().toDouble()
                binding.result.text = ""
            }
            R.id.button_multiply -> {
                operator = "*"
                firstNumber = binding.result.text.toString().toDouble()
                binding.result.text = ""
            }
            R.id.button_divide -> {
                operator = "/"
                firstNumber = binding.result.text.toString().toDouble()
                binding.result.text = ""
            }
            R.id.button_dot -> {
                if (!binding.result.text.contains(".")) {
                    result.append(".")
                    binding.result.text = result.toString()
                }
            }
            R.id.button_equals -> {
                secondNumber = binding.result.text.toString().toDouble()
                calculateResult()
            }
        }
    }

    private fun calculateResult() {
        when(operator) {
            "*" -> {
                val result = NumberFormat.getCurrencyInstance().format(firstNumber * secondNumber)
                binding.result.text = getString(R.string.result, NumberFormat.getInstance().format(firstNumber * secondNumber))
            }
            "/" -> {
                binding.result.text = getString(R.string.result, NumberFormat.getInstance().format(firstNumber / secondNumber))
            }
            "+" -> {
                binding.result.text = getString(R.string.result, NumberFormat.getInstance().format(firstNumber + secondNumber))
            }
            "-" -> {
                binding.result.text = getString(R.string.result, NumberFormat.getInstance().format(firstNumber - secondNumber))
            }
        }
    }


}