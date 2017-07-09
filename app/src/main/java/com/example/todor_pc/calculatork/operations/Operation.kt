package com.example.todor_pc.calculatork.operations

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 03.01.12
 * Time: 14:40
 */
interface Operation {

    fun calculate(a: Double, b: Double): Double

    val priority: Double
}
