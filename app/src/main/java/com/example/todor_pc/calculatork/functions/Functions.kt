package com.example.todor_pc.calculatork.functions

/**
 * Created by IntelliJ IDEA.
 * User: evgeniy
 * Date: 05.01.12
 * Time: 17:27
 */
object Functions {


    private val SIN = Sin()
    private val COS = Cos()
    private val TAN = Tan()
    private val ASIN = Asin()
    private val ACOS = Acos()
    private val ATAN = Atan()
    private val SINH = Sinh()
    private val COSH = Cosh()
    private val TANH = Tanh()
    private val EXP = Exp()
    private val LN = Ln()
    private val LOG10 = Log10()
    private val SQRT = Sqrt()

    fun parseFunction(function: String): Function {
        if ("sin" == function) {
            return SIN
        } else if ("cos" == function) {
            return COS
        } else if ("tan" == function) {
            return TAN
        } else if ("asin" == function) {
            return ASIN
        } else if ("acos" == function) {
            return ACOS
        } else if ("atan" == function) {
            return ATAN
        } else if ("sinh" == function) {
            return SINH
        } else if ("cosh" == function) {
            return COSH
        } else if ("tanh" == function) {
            return TANH
        } else if ("exp" == function) {
            return EXP
        } else if ("ln" == function) {
            return LN
        } else if ("log" == function) {
            return LOG10
        } else if ("sqrt" == function) {
            return SQRT
        } else {
            throw IllegalArgumentException()
        }
    }
}
