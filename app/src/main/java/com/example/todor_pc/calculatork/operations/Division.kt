package com.example.todor_pc.calculatork.operations

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 03.01.12
 * Time: 14:45
 */
class Division : Operation {

    override fun calculate(a: Double, b: Double): Double {
        return a / b
    }

    override val priority: Double
        get() = 1.0

    override fun toString(): String {
        return "/"
    }
}
