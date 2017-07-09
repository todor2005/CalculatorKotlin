package com.example.todor_pc.calculatork

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var isRavnoPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = lastCustomNonConfigurationInstance;

        //some comment

        if (lastCustomNonConfigurationInstance != null) {
            val data = lastCustomNonConfigurationInstance.toString()
            txtArea.text = data
        }

        btnD.setOnTouchListener(RepeatListener(400, 150, View.OnClickListener { btnD(btnD) }))

    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        val data = txtArea.text.toString();
        return data;
    }

    fun btnC(view: View) {
        txtArea.text = "0"
    }

    fun btn1(view: View) {
        addDigit(1)
    }

    fun btn2(view: View) {
        addDigit(2)
    }

    fun btn3(view: View) {
        addDigit(3)
    }

    fun btn4(view: View) {
        addDigit(4)
    }

    fun btn5(view: View) {
        addDigit(5)
    }

    fun btn6(view: View) {
        addDigit(6)
    }

    fun btn7(view: View) {
        addDigit(7)
    }

    fun btn8(view: View) {
        addDigit(8)
    }

    fun btn9(view: View) {
        addDigit(9)
    }

    fun btn0(view: View) {
        addDigit(0)
    }

    fun btnPi(view: View) {
        addSymbol("PI")
    }

    fun btnE(view: View) {
        addSymbol("e")
    }

    fun btnPlus(view: View) {
        addSumb("+")
    }

    fun btnMinus(view: View) {
        addSumb("-")
    }

    fun btnPo(view: View) {
        addSumb("*")
    }

    fun btnDel(view: View) {
        addSumb("/")
    }

    fun btnProcent(view: View) {
        if (isRavnoPressed) {
            txtArea.append("%")
            isRavnoPressed = false
            return
        }
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            return
        }
        val c = getLastChar()
        if (c == "0" || c == "1" || c == "2" || c == "3" || c == "4" || c == "5" || c == "6" || c == "7" || c == "8" || c == "9") {
            txtArea.append("%")
        }
    }

    fun btnSkoba(view: View) {
        if (isRavnoPressed) {
            txtArea.text = "("
            isRavnoPressed = false
            return
        }
        val c = getLastChar()
        if (c == "" || c == "0") {
            txtArea.text = "("
            return
        }

        if (c == ".") {
            return
        }

        if (c == "(") {
            txtArea.append("(")
            return
        }
        var opening = 0
        var closing = 0

        val chars = txtArea.text.toString().toCharArray()
        for (i in chars.indices) {
            if (chars[i] == '(') {
                opening++
            }
            if (chars[i] == ')') {
                closing++
            }
        }

        if (opening > closing && c != "(" && c != "+" && c != "-" && c != "*" && c != "/") {
            txtArea.append(")")
            return
        }

        if (c == "*" || c == "/" || c == "+" || c == "-" || c == "^") {
            txtArea.append("(")
            return
        }

        if (c == "0" || c == "1" || c == "2" || c == "3" || c == "4" || c == "5" || c == "6" || c == "7" || c == "8" || c == "9") {
            txtArea.append("*(")
            return
        }
    }

    fun btnZnak(view: View) {

        if (isRavnoPressed) {
            txtArea.text = "(-"
            isRavnoPressed = false
            return
        }

        val c = getLastChar()
        if (c == "") {
            txtArea.text = "(-"
            return
        }

        val str = txtArea.text.toString()

        val length = getLastNumberLenght(str)

        if (str.length >= length + 2) {
            val znak = str.substring(str.length - (length + 2), str.length - length)

            if (znak == "(-") {
                val newOne = str.substring(0, str.length - (length + 2)) + str.substring(str.length - length, str.length)
                txtArea.text = newOne
            } else {
                val newOne = str.substring(0, str.length - length) + "(-" + str.substring(str.length - length, str.length)
                txtArea.text = newOne
            }
        } else {
            if (length == str.length) {
                val newOne = "(-" + str
                txtArea.text = newOne
            }
        }
    }

    private fun getLastNumberLenght(str: String): Int {
        if (str.isEmpty()) {
            return 0
        }
        if (str.endsWith("PI")) {
            return 2
        }
        var counter = 0
        for (i in str.length - 1 downTo 0) {
            val c = str[i]
            if (c == 'e' || c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.') {
                counter++
                continue
            } else {
                break
            }
        }
        return counter
    }

    fun btnSin(view: View) {
        addFunction("sin")
    }

    fun btnCos(view: View) {
        addFunction("cos")
    }

    fun btnTan(view: View) {
        addFunction("tan")
    }

    fun btnLn(view: View) {
        addFunction("ln")
    }

    fun btnLog(view: View) {
        addFunction("log")
    }

    fun btnExp(view: View) {
        addFunction("exp")
    }

    fun btnSQRT(view: View) {
        addFunction("sqrt")
    }

    private fun addFunction(function: String) {

        if (isRavnoPressed) {
            txtArea.text = function + "("
            isRavnoPressed = false
            return
        }

        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            txtArea.text = function + "("
            return
        }

        if (text == "0") {
            txtArea.text = function + "("
        }

        val c = getLastChar()


        if (c == "0" || c == "1" || c == "2"
                || c == "3" || c == "4" || c == "5"
                || c == "6" || c == "7" || c == "8"
                || c == "9" || c == ")" || c == "e"
                || c == "I") {
            txtArea.append("*$function(")
        }


        if (c == "*" || c == "/" || c == "+" || c == "-" || c == "^" || c == "%") {
            txtArea.append(function + "(")
        }
    }

    fun btnD(view: View) {
        if (txtArea.text.toString() == "0") {
            return
        }
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            return
        }
        if (text.length == 1) {
            txtArea.text = "0"
            return
        }
        val c = getLastChar()
        if (c == "I") {
            if (text.length == 2) {
                txtArea.text = "0"
                return
            }
            txtArea.text = text.substring(0, text.length - 2)
            return
        }
        if (c == "(") {
            if (text.endsWith("sqrt(")) {
                if (text.length == 5) {
                    txtArea.text = "0"
                    return
                }
                txtArea.text = text.substring(0, text.length - 5)
                return
            }
            if (text.endsWith("sin(") || text.endsWith("cos(") || text.endsWith("tan(") || text.endsWith("log(") || text.endsWith("exp(")) {
                if (text.length == 4) {
                    txtArea.text = "0"
                    return
                }
                txtArea.text = text.substring(0, text.length - 4)
                return
            }
            if (text.endsWith("ln(")) {
                if (text.length == 3) {
                    txtArea.text = "0"
                    return
                }
                txtArea.text = text.substring(0, text.length - 3)
                return
            }
        }
        txtArea.text = text.substring(0, text.length - 1)
    }

    fun btnDrob(view: View) {
        if (isRavnoPressed) {
            txtArea.text = "1/"
            isRavnoPressed = false
            return
        }
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            txtArea.text = "1/"
        }

        val c = getLastChar()

        if (c == "0" || c == "1" || c == "2"
                || c == "3" || c == "4" || c == "5"
                || c == "6" || c == "7" || c == "8"
                || c == "9" || c == ")" || c == "e"
                || c == "I") {
            txtArea.append("*1/")
        }

        if (c == "*" || c == "/" || c == "+" || c == "-" || c == "^" || c == "%") {
            txtArea.append("1/")
        }
    }

    private fun addDigit(digit: Int) {
        if (isRavnoPressed) {
            txtArea.text = digit.toString() + ""
            isRavnoPressed = false
            return
        }
        if (txtArea.text.toString() == "0") {
            txtArea.text = ""
        }
        val c = getLastChar()
        if (c == "e" || c == "I" || c == "P" || c == ")") {
            return
        }
        txtArea.append(digit.toString() + "")
    }

    private fun addSymbol(symbol: String) {

        if (isRavnoPressed) {
            txtArea.text = symbol
            isRavnoPressed = false
            return
        }
        val text = txtArea.text.toString()
        if (text.isEmpty() || text == "0") {
            txtArea.text = symbol
            return
        }
        val c = getLastChar()
        if (c == "" || c == "0" || c == "1"
                || c == "2" || c == "3" || c == "4"
                || c == "5" || c == "6" || c == "7"
                || c == "8" || c == "9" || c == "e"
                || c == "I" || c == "P") {
            return
        }
        txtArea.append(symbol)
    }

    fun btnStepen(view: View) {
        addSumb("^")
    }

    private fun addSumb(symbol: String) {
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            return
        }
        val c = getLastChar()
        if (c == "0" || c == "1" || c == "2"
                || c == "3" || c == "4" || c == "5"
                || c == "6" || c == "7" || c == "8"
                || c == "9" || c == "e" || c == "I"
                || c == ")") {
            txtArea.append(symbol)
            isRavnoPressed = false
        }
    }

    private fun getLastChar(): String {
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            return ""
        } else {
            return text[text.length - 1] + ""
        }
    }

    fun btnPoint(view: View) {
        if (isRavnoPressed) {
            txtArea.append(".")
            isRavnoPressed = false
            return
        }
        val text = txtArea.text.toString()
        if (text.isEmpty()) {
            return
        }
        val c = getLastChar()
        if (c == "0" || c == "1" || c == "2"
                || c == "3" || c == "4" || c == "5"
                || c == "6" || c == "7" || c == "8" || c == "9") {
            txtArea.append(".")
        }
    }

    fun btnRavno(view: View) {
        try {
            val text = txtArea.text.toString()
            if (text == "0" || text.isEmpty()) {
                return
            }
            isRavnoPressed = true

            val c = Calculator()
            c.expression = text

            val d = c.result

            val result = d.toString() + ""

            if (result.substring(result.length - 2, result.length) == ".0") {
                txtArea.text = result.substring(0, result.length - 2)
            } else {
                txtArea.text = result
            }


        } catch (e: Exception) {
            isRavnoPressed = false


            val helloToast = Toast.makeText(this, "The equation is wrong!", Toast.LENGTH_SHORT)
            helloToast.setGravity(Gravity.CENTER, 0, 0)
            helloToast.show()

            return
        }

    }
}
