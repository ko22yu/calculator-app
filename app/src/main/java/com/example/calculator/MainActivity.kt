package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btnZero = findViewById<Button>(R.id.btnZero)
        val btnOne = findViewById<Button>(R.id.btnOne)
        val btnTwo = findViewById<Button>(R.id.btnTwo)
        val btnThree = findViewById<Button>(R.id.btnThree)
        val btnFour = findViewById<Button>(R.id.btnFour)
        val btnFive = findViewById<Button>(R.id.btnFive)
        val btnSix = findViewById<Button>(R.id.btnSix)
        val btnSeven = findViewById<Button>(R.id.btnSeven)
        val btnEight = findViewById<Button>(R.id.btnEight)
        val btnNine = findViewById<Button>(R.id.btnNine)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnTimes = findViewById<Button>(R.id.btnTimes)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnEqual = findViewById<Button>(R.id.btnEqual)
        val btnAc = findViewById<Button>(R.id.btnAc)

        val listener = ButtonClickListener()
        btnZero.setOnClickListener(listener)
        btnOne.setOnClickListener(listener)
        btnTwo.setOnClickListener(listener)
        btnThree.setOnClickListener(listener)
        btnFour.setOnClickListener(listener)
        btnFive.setOnClickListener(listener)
        btnSix.setOnClickListener(listener)
        btnSeven.setOnClickListener(listener)
        btnEight.setOnClickListener(listener)
        btnNine.setOnClickListener(listener)
        btnPlus.setOnClickListener(listener)
        btnMinus.setOnClickListener(listener)
        btnTimes.setOnClickListener(listener)
        btnDivide.setOnClickListener(listener)
        btnEqual.setOnClickListener(listener)
        btnAc.setOnClickListener(listener)
    }

    enum class State {
        LEFT, RIGHT
    }

    private inner class ButtonClickListener: View.OnClickListener {

        var operator = ""
        var leftNum = 0
        var rightNum = 0
        var state = State.LEFT

        // クリックされたら行う処理
        override fun onClick(view: View) {
            when(view.id) {
                R.id.btnZero -> appendNum(0)
                R.id.btnOne -> appendNum(1)
                R.id.btnTwo -> appendNum(2)
                R.id.btnThree -> appendNum(3)
                R.id.btnFour -> appendNum(4)
                R.id.btnFive -> appendNum(5)
                R.id.btnSix -> appendNum(6)
                R.id.btnSeven -> appendNum(7)
                R.id.btnEight -> appendNum(8)
                R.id.btnNine -> appendNum(9)
                R.id.btnPlus -> displayOperator("+")
                R.id.btnMinus -> displayOperator("-")
                R.id.btnTimes -> displayOperator("*")
                R.id.btnDivide -> displayOperator("/")
                R.id.btnEqual -> compute()
                R.id.btnAc -> clear()
            }
        }

        private fun appendNum(n: Int) {
            val result = findViewById<TextView>(R.id.result)
            if (state == State.LEFT) {
                leftNum = leftNum * 10 + n
                result.text = leftNum.toString()
            }
            else if (state == State.RIGHT) {
                rightNum = rightNum * 10 + n
                result.text = rightNum.toString()
            }
        }
        private fun displayOperator(op: String) {
            val result = findViewById<TextView>(R.id.result)
            operator = op
            when (op) {
                "+" -> result.text = "+"
                "-" -> result.text = "-"
                "*" -> result.text = "×"
                "/" -> result.text = "÷"
            }
            state = State.RIGHT
        }
        private fun compute() {
            if (state == State.RIGHT) {
                val result = findViewById<TextView>(R.id.result)
                when (operator) {
                    "+" -> leftNum = leftNum + rightNum
                    "-" -> leftNum = leftNum - rightNum
                    "*" -> leftNum = leftNum * rightNum
                    "/" -> leftNum = leftNum / rightNum
                }
                result.text = leftNum.toString()
                state = State.LEFT
                rightNum = 0
                operator = ""
            }
        }
        private fun clear() {
            leftNum = 0
            rightNum = 0
            operator = ""
            state = State.LEFT
            val result = findViewById<TextView>(R.id.result)
            result.text = "0"
        }
    }
}