package com.example.todor_pc.calculatork.operations

class Subtraction : Operation {

    override fun calculate(a: Double, b: Double): Double {
        return a - b
    }

    override val priority: Double
        get() = 0.0

    override fun toString(): String {
        return "-"
    }
}
