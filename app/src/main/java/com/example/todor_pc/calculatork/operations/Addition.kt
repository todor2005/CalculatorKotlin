package com.example.todor_pc.calculatork.operations

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 03.01.12
 * Time: 14:42
 */
class Addition : Operation {

    override fun calculate(a: Double, b: Double): Double {
        return a + b
    }

    override val priority: Double
        get() = 0.0

    override fun toString(): String {
        return "+"
    }
}
