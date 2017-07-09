package com.example.todor_pc.calculatork.variables

import java.util.HashMap

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 05.01.12
 * Time: 17:32
 */
class Variables {

    private val variables = HashMap<String, Variable>()

    fun parseVariable(variable: String): Variable {
        if ("PI" == variable) {
            return PI
        } else if ("e" == variable) {
            return E
        } else {
            if (variables.containsKey(variable)) {
                return variables[variable]!!
            } else {
                throw IllegalArgumentException()
            }
        }
    }

    fun setVariable(variableName: String, variable: Variable) {
        variables.put(variableName, variable)
    }

    fun setDoubleVariable(variableName: String, variableValue: Double) {
        variables.put(variableName, DoubleVariable(variableValue))
    }

    fun setX(variableValue: Double) {
        variables.put("x", DoubleVariable(variableValue))
    }

    override fun toString(): String {
        return variables.toString()
    }

    companion object {

        private val PI = Pi()
        private val E = E()
    }
}
