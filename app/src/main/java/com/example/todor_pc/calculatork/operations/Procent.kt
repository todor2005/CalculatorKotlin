package com.example.todor_pc.calculatork.operations

/**
 * Created by Todor on 7/27/13.
 */
class Procent : Operation {
    override fun calculate(a: Double, b: Double): Double {
        return a % b
    }

    override val priority: Double
        get() = 2.0


    override fun toString(): String {
        return "%"
    }
}
