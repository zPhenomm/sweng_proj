package com.example.software_engineering_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import android.util.Log

lateinit var equation : String
lateinit var view: TextView

class CalcActivity : AppCompatActivity(), OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calc_activity)

        view = findViewById(R.id.eqView)
        view.movementMethod = ScrollingMovementMethod()
        equation = ""

        // create listeners for buttons
        var btn: Button = findViewById(R.id.calcBtn1)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn2)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn3)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn4)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn5)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn5)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn6)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn7)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn8)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn9)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtn0)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnEq)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnClear)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnPlus)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnMinus)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnFactor)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnDivide)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnSin)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnCos)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnTan)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnRoot)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnExp)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnDot)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnOpenBracket)
        btn.setOnClickListener(this)
        btn = findViewById(R.id.calcBtnCloseBracket)
        btn.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when(v.getId()){
            R.id.calcBtn1 -> manageEq("1")
            R.id.calcBtn2 -> manageEq("2")
            R.id.calcBtn3 -> manageEq("3")
            R.id.calcBtn4 -> manageEq("4")
            R.id.calcBtn5 -> manageEq("5")
            R.id.calcBtn6 -> manageEq("6")
            R.id.calcBtn7 -> manageEq("7")
            R.id.calcBtn8 -> manageEq("8")
            R.id.calcBtn9 -> manageEq("9")
            R.id.calcBtn0 -> manageEq("0")
            R.id.calcBtnPlus -> manageEq("+")
            R.id.calcBtnMinus -> manageEq("-")
            R.id.calcBtnFactor -> manageEq("*")
            R.id.calcBtnDivide -> manageEq("/")
            R.id.calcBtnSin -> manageEq("sin")
            R.id.calcBtnCos -> manageEq("cos")
            R.id.calcBtnTan -> manageEq("tan")
            R.id.calcBtnRoot -> manageEq("sqrt")
            R.id.calcBtnExp -> manageEq("^")
            R.id.calcBtnDot -> manageEq(".")
            R.id.calcBtnOpenBracket -> manageEq("(")
            R.id.calcBtnCloseBracket -> manageEq(")")

            R.id.calcBtnClear -> manageEq("cls")
            R.id.calcBtnEq -> manageEq("=")
        }
    }


    fun manageEq(s: String){
        if(s == "=") {
            try{
                var e: Expression = ExpressionBuilder(equation).build()
                var result = e.evaluate()
                if(result % 1 == 0.0){  // if double decimal place is 0, remove it
                    view.text = result.toLong().toString()
                }
                else{
                    view.text = result.toString()
                }
            }
            catch(e: java.lang.IllegalArgumentException){
                view.text = "Syntax Error"
                return
            }
            catch(e: java.lang.ArithmeticException){
                view.text = "Math Error"
                return
            }
            catch(e: java.lang.NumberFormatException){
                view.text = "Syntax Error"
                return
            }
            finally {
                equation = ""
            }
        }
        else if(s == "cls") {
            equation = ""
            view.text = equation
        }
        else{
            equation += s
            view.text = equation
        }
    }
}