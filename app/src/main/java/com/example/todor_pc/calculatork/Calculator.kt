package com.example.todor_pc.calculatork


import com.example.todor_pc.calculatork.functions.Functions
import com.example.todor_pc.calculatork.operations.Operation
import com.example.todor_pc.calculatork.operations.Operations
import com.example.todor_pc.calculatork.variables.Variable
import com.example.todor_pc.calculatork.variables.Variables

import java.util.LinkedList


class Calculator {

    var expression: String = ""
    private val variables = Variables()
    private val operationList = LinkedList<Operation>()
    private val numberList = LinkedList<Double>()

    constructor()

    constructor(expression: String) {
        this.expression = expression
    }

    //Parse expression
    //If token is identifier and current character is '(' then function found
    //If identifier starts with number or contains dot then throw exception
    //If unknown character found throw exception
    //Add operands and operator when operator found  or only last operand when last character found
    //If subtraction operation found but there is no operand, push operand 0.0
    val result: Double
        get() {
            var token = StringBuilder()
            var i = 0
            var isIdentifier = false
            while (i < expression.length) {
                val character = expression[i].toString()

                if ("(" == character) {
                    var openBracketsCount = 0
                    var closeBracketsCount = 0

                    for (j in i + 1..expression.length - 1) {
                        when (expression[j]) {
                            '(' -> openBracketsCount++
                            ')' -> closeBracketsCount++
                        }
                        if (closeBracketsCount > openBracketsCount) {
                            val result = Calculator(expression.substring(i + 1, j)).result
                            if (isIdentifier && token.isNotEmpty()) {
                                numberList.add(Functions.parseFunction(token.toString()).perform(result))
                                token = StringBuilder()
                                isIdentifier = false
                            } else {
                                numberList.add(result)
                            }
                            i = j
                            break
                        }
                    }
                } else if (NUMBER_CHARACTERS.contains(character) || "." == character) {
                    token.append(character)
                } else if (LETTER_CHARACTERS.contains(character)) {
                    if (token.isNotEmpty() && (NUMBER_CHARACTERS.contains(token.substring(0, 1)) || token.toString().contains("."))) {
                        throw IllegalArgumentException()
                    } else {
                        token.append(character)
                        isIdentifier = true
                    }
                } else if (!IGNORE_CHARACTERS.contains(character) && !OPERATION_CHARACTERS.contains(character)) {
                    throw IllegalArgumentException()
                }
                if (OPERATION_CHARACTERS.contains(character) || i == expression.length - 1) {
                    if (token.isNotEmpty()) {
                        if (!isIdentifier) {
                            numberList.add(java.lang.Double.parseDouble(token.toString()))
                        } else {
                            numberList.add(variables.parseVariable(token.toString()).value)
                            isIdentifier = false
                        }
                        token = StringBuilder()
                    } else if ("-" == character && numberList.isEmpty()) {
                        numberList.add(0.0)
                    }
                    if (i != expression.length - 1) {
                        operationList.add(Operations.parseOperation(character))
                    }
                }

                i++
            }
            for (priority in Operations.MAX_PRIORITY downTo Operations.MIN_PRIORITY) {
                i = 0
                while (i < operationList.size) {
                    val operation = operationList[i]
                    if (operation.priority == priority.toDouble()) {
                        val operand1 = numberList[i]
                        val operand2 = numberList[i + 1]
                        val result = operation.calculate(operand1, operand2)

                        operationList.removeAt(i)
                        numberList.removeAt(i)
                        numberList.removeAt(i)
                        numberList.add(i, result)
                        continue
                    }
                    i++
                }
            }

            return numberList[0]
        }

    fun getVariable(variable: String): Double {
        return variables.parseVariable(variable).value
    }

    fun setVariable(variable: String, value: Variable) {
        variables.setVariable(variable, value)
    }

    fun setDoubleVariable(variable: String, variableValue: Double) {
        variables.setDoubleVariable(variable, variableValue)
    }

    fun setX(variableValue: Double) {
        variables.setX(variableValue)
    }

    companion object {

        private val OPERATION_CHARACTERS = "+-*/^%"
        private val NUMBER_CHARACTERS = "0123456789"
        private val LETTER_CHARACTERS = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ_"
        private val IGNORE_CHARACTERS = " "
    }
}