package com.example.todor_pc.calculatork.functions


import com.example.todor_pc.calculatork.Calculator

class SingleVariableFunction : Function {

    private val calculator = Calculator()
    var variableName: String? = null

    constructor() {}

    constructor(expression: String, variableName: String) {
        this.variableName = variableName
        calculator.expression = expression
    }

    var expression: String
        get() = calculator.expression
        set(expression) {
            calculator.expression = expression
        }

    override fun perform(a: Double): Double {
        calculator.setDoubleVariable(variableName!!, a)
        return calculator.result
    }
}
