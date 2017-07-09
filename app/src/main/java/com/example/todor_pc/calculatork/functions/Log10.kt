package com.example.todor_pc.calculatork.functions

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 06.01.12
 * Time: 15:04
 */
class Log10 : Function {

    override fun perform(a: Double): Double {
        return Math.log10(a)
    }
}
