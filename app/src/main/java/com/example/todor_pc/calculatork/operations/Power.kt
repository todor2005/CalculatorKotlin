package com.example.todor_pc.calculatork.operations

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 04.01.12
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
class Power : Operation {

    override fun calculate(a: Double, b: Double): Double {
        return Math.pow(a, b)
    }

    override val priority: Double
        get() = 2.0

    override fun toString(): String {
        return "^"
    }
}
